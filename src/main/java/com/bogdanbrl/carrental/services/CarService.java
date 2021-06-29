package com.bogdanbrl.carrental.services;


import com.bogdanbrl.carrental.dto.CarDTO;
import com.bogdanbrl.carrental.dto.FilterCriteriaDTO;
import com.bogdanbrl.carrental.dto.convertors.CarDTOtoCarConvertor;
import com.bogdanbrl.carrental.dto.convertors.CarToCarDTOconvertor;
import com.bogdanbrl.carrental.dto.convertors.FilterCriteriaDTOtoCarPredicates;
import com.bogdanbrl.carrental.models.*;
import com.bogdanbrl.carrental.predicates.CarPredicates;
import com.bogdanbrl.carrental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarOptionsService carOptionsService;

    public CarService(CarRepository carRepository, CarOptionsService carOptionsService) {
        this.carRepository = carRepository;
        this.carOptionsService = carOptionsService;
    }

    public CarDTO getCarById(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);

        if (carOptional.isPresent()){
            CarDTO carDTO = CarToCarDTOconvertor.convertCartoCarDTO(carOptional.get());
            return carDTO;
        } else {
            throw new RuntimeException("Invalid car id! Car not found!");
        }
    }

    public List<CarDTO> getCars(FilterCriteriaDTO fc) {

        LocalDate startRentDate = null;
        LocalDate endRentDate = null;

        List<Car> cars = null;
        List<Predicate<Car>> predicates = null;

        try {
            if ((fc.getStartRent() != null && !fc.getStartRent().trim().equals(""))
                    && (fc.getEndRent() != null && !fc.getEndRent().trim().equals("")) ) {

                startRentDate = LocalDate.parse(fc.getStartRent(), DateTimeFormatter.ISO_LOCAL_DATE);
                endRentDate = LocalDate.parse(fc.getEndRent(), DateTimeFormatter.ISO_LOCAL_DATE);

                cars = carRepository.getAvailableCarsByPeriod(startRentDate, endRentDate);
            } else {
                cars = carRepository.findAll();
            }
            predicates = FilterCriteriaDTOtoCarPredicates.getPredicates(fc);
        } catch (Exception e) {
            throw new RuntimeException("Invalid input");
        }

        if (predicates.isEmpty()){
            return cars.stream().map(CarToCarDTOconvertor::convertCartoCarDTO).collect(Collectors.toList());
        } else {
            List<Car> result = CarPredicates.filterCarsMultiplePredicates(cars, predicates);
            return result.stream().map(CarToCarDTOconvertor::convertCartoCarDTO).collect(Collectors.toList());
        }
    }

    public List<Car> searchCarsByBrandOrModel(String brand, String model) {
        if (brand.trim().equals("")) {
            return carRepository.findAll();
        }
        List<Car> cars = carRepository.getAllByBrandContainingOrModelContaining(brand, model);
        return Collections.unmodifiableList(cars);
    }

    public void add(CarDTO carDTO) {
        try {
            CarOptions carOptions = carDTO.getCarOptions();
            Optional<CarOptions> carOptionsDB = carOptionsService.getOptionsByHeatedSeatsAndNavigation(carOptions.getHasHeatedSeats(), carOptions.getHasNavigation());

            if (carOptionsDB.isEmpty()) {
                carOptionsService.add(carOptions);
                carOptionsDB = carOptionsService.getOptionsByHeatedSeatsAndNavigation(carOptions.getHasHeatedSeats(), carOptions.getHasNavigation());
            }

            Car car = CarDTOtoCarConvertor.convertCarDTOtoCar(carDTO);

            car.setCarOptions(carOptionsDB.get());
            carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException("Car cannot be added: " + e.getMessage());
        }
    }

    public void edit(CarDTO carDTO) {
        try {
            CarOptions carOptions = carDTO.getCarOptions();
            Optional<CarOptions> carOptionsDB = carOptionsService.getOptionsByHeatedSeatsAndNavigation(carOptions.getHasHeatedSeats(), carOptions.getHasNavigation());

            if (carOptionsDB.isEmpty()) {
                carOptions.setId(null);
                carOptionsService.add(carOptions);
                carOptionsDB = carOptionsService.getOptionsByHeatedSeatsAndNavigation(carOptions.getHasHeatedSeats(), carOptions.getHasNavigation());
            }

            Car car = CarDTOtoCarConvertor.convertCarDTOtoCar(carDTO);

            Car carDB = getCarByIdFromDB(car.getId());

            car.setCarOptions(carOptionsDB.get());
            carRepository.save(carDB);
        } catch (Exception e) {
            throw new RuntimeException("Car cannot be updated: " + e.getMessage());
        }
    }

    public Car getCarByIdFromDB(Long id){
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found into database!"));
    }

    public void delete(CarDTO carDTO) {
        try {
            Car car = CarDTOtoCarConvertor.convertCarDTOtoCar(carDTO);
            carRepository.delete(car);
        } catch (Exception e) {
            throw new RuntimeException("Car cannot be deleted: " + e.getMessage());
        }
    }

    public List<String> getCarBodies() {
        List<String> carBodies = new ArrayList<>();
        for (ECarBody carBody : ECarBody.values()) {
            carBodies.add(carBody.getName());
        }
        return carBodies;
    }

    public List<String> getCarClasses() {
        List<String> carClasses = new ArrayList<>();
        for (ECarClass carClass : ECarClass.values()) {
            carClasses.add(carClass.getName());
        }
        return carClasses;
    }

    public List<String> getEngines() {
        List<String> engines = new ArrayList<>();
        for (ECarEngine engine : ECarEngine.values()) {
            engines.add(engine.getName());
        }
        return engines;
    }
}
