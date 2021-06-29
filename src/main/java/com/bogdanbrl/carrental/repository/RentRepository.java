package com.bogdanbrl.carrental.repository;

import com.bogdanbrl.carrental.models.Car;
import com.bogdanbrl.carrental.models.Rent;
import com.bogdanbrl.carrental.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query("from Rent rent inner join rent.user where rent.car=:car")
    List<Rent> getCarHistory(Car car);

    @Query("from Rent rent inner join rent.car where rent.user=:user")
    List<Rent> getUserHistory(User user);

    @Query("FROM Rent rent WHERE" +
            "(rent.startRent BETWEEN :start AND :end) OR (rent.endRent BETWEEN :start AND :end)")
    List<Rent> getByPeriod(LocalDate start, LocalDate end);

    @Query("FROM Rent rent WHERE" +
            "(rent.startRent NOT BETWEEN :start AND :end) OR (rent.endRent NOT BETWEEN :start AND :end)")
    List<Rent> getRentsOutsidePeriod(LocalDate start, LocalDate end);

    @Query("FROM Rent rent WHERE :today BETWEEN rent.startRent AND rent.endRent")
    List<Rent> getTodayRents(LocalDate today);

    @Query("FROM Rent rent WHERE rent.car= :car AND " +
            "((:start BETWEEN rent.startRent AND rent.endRent) OR (:end BETWEEN rent.startRent AND rent.endRent))")
    List<Rent> isCarAvailable(Car car, LocalDate start, LocalDate end);
}
