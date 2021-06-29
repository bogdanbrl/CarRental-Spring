package com.bogdanbrl.carrental.repository;

import com.bogdanbrl.carrental.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> getAllByBrandContainingOrModelContaining(String brand, String model);

    @Query("FROM Car WHERE id NOT IN (SELECT rent.car FROM Rent as rent WHERE rent.startRent BETWEEN :start AND :end OR rent.endRent BETWEEN :start AND :end)")
    List<Car> getAvailableCarsByPeriod(LocalDate start, LocalDate end);
}
