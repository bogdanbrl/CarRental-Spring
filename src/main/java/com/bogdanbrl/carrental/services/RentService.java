package com.bogdanbrl.carrental.services;

import com.bogdanbrl.carrental.models.Car;
import com.bogdanbrl.carrental.models.Rent;
import com.bogdanbrl.carrental.models.User;
import com.bogdanbrl.carrental.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final CarService carService;

    public RentService(RentRepository rentRepository, CarService carService) {
        this.rentRepository = rentRepository;
        this.carService = carService;
    }

    public List<Rent> getRents(){
        List<Rent> rents = rentRepository.findAll();

        rents.forEach(r -> {
            r.getUser().setPassword("");
        });

        return Collections.unmodifiableList(rents);
    }

    public void add(Rent rent){
        int rentedDays = (int) ChronoUnit.DAYS.between(rent.getStartRent(), rent.getEndRent());
        rent.setRentedDays(rentedDays);

        double earnings = rentedDays * rent.getRentPricePerDay();
        rent.setEarnings(earnings);

        try {
            rentRepository.save(rent);
        } catch (Exception e) {
            throw new RuntimeException("Rent not saved: " + e.getMessage());
        }
    }

    public void edit(Rent rent){
        int rentedDays = (int) ChronoUnit.DAYS.between(rent.getStartRent(), rent.getEndRent());
        rent.setRentedDays(rentedDays);

        double earnings = rentedDays * rent.getRentPricePerDay();
        rent.setEarnings(earnings);

        try {
            rentRepository.save(rent);
        } catch (Exception e) {
            throw new RuntimeException("Rent not saved: " + e.getMessage());
        }
    }

    public List<Rent> getCurrentRents(){
        LocalDate today = LocalDate.now();
        return rentRepository.getTodayRents(today);
    }

    public List<Rent> getUserHistory(User user) {
        return rentRepository.getUserHistory(user);
    }

    public List<Rent> getCarHistory(Car car) {
        return rentRepository.getCarHistory(car);
    }

    public List<Rent> getByPeriod(String startPeriod, String endPeriod){
        LocalDate startPeriodDate = null;
        LocalDate endPeriodDate = null;

        try {
            startPeriodDate = LocalDate.parse(startPeriod, DateTimeFormatter.ISO_LOCAL_DATE);
            endPeriodDate = LocalDate.parse(endPeriod, DateTimeFormatter.ISO_LOCAL_DATE);

            return rentRepository.getByPeriod(startPeriodDate, endPeriodDate);

        } catch (Exception e) {
            throw new RuntimeException("Invalid date format");
        }
    }

    public String rentCar(String startPeriod, String endPeriod, Long carID, User user) {

        Car carFromDB = carService.getCarByIdFromDB(carID);

        LocalDate startPeriodDate = null;
        LocalDate endPeriodDate = null;

        try {
            startPeriodDate = LocalDate.parse(startPeriod, DateTimeFormatter.ISO_LOCAL_DATE);
            endPeriodDate = LocalDate.parse(endPeriod, DateTimeFormatter.ISO_LOCAL_DATE);

            if (startPeriodDate.isBefore(LocalDate.now()) || endPeriodDate.isBefore(startPeriodDate)){
                throw new RuntimeException("Invalid date period");
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date format " + e.getMessage());
        }

        boolean isAvailable = isCarAvailable(carFromDB, startPeriodDate, endPeriodDate);

        if (isAvailable){

            int rentedDays = (int) ChronoUnit.DAYS.between(startPeriodDate, endPeriodDate);
            double earnings = rentedDays * carFromDB.getRentPrice();

            Rent rent = new Rent();
            rent.setUser(user);
            rent.setCar(carFromDB);
            rent.setStartRent(startPeriodDate);
            rent.setEndRent(endPeriodDate);
            rent.setRentPricePerDay(carFromDB.getRentPrice());
            rent.setRentedDays(rentedDays);
            rent.setEarnings(earnings);

            try {
                rentRepository.save(rent);
                String message = "Car " + carFromDB.getBrand() + " " + carFromDB.getModel() + " rented from "
                        + startPeriod + " to " + endPeriod + " for " + rentedDays + " days, "
                        + "price: " + earnings + " euro, to: " + user.getUsername();
                return message;
            } catch (Exception e) {
                throw new RuntimeException("Rent not saved: " + e.getMessage());
            }
        } else {
            String message = "Car " + carFromDB.getBrand() + " " + carFromDB.getModel()
                    + " not available for period: "
                    + startPeriod + " to " + endPeriod;
            throw new RuntimeException(message);
        }

    }

    private boolean isCarAvailable(Car carFromDB, LocalDate startPeriodDate, LocalDate endPeriodDate) {
        List<Rent> rents = rentRepository.isCarAvailable(carFromDB, startPeriodDate, endPeriodDate);
        return rents.isEmpty();
    }
}
