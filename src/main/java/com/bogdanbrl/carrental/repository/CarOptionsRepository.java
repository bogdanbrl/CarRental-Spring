package com.bogdanbrl.carrental.repository;

import com.bogdanbrl.carrental.models.CarOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarOptionsRepository extends JpaRepository<CarOptions, Long> {

    Optional<CarOptions> findCarOptionsByHasHeatedSeatsAndHasNavigation(Boolean hasHeatedSeats, Boolean hasNavigation);

}
