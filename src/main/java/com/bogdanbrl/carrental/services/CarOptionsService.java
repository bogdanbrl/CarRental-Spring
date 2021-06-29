package com.bogdanbrl.carrental.services;

import com.bogdanbrl.carrental.models.CarOptions;
import com.bogdanbrl.carrental.repository.CarOptionsRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CarOptionsService {

    private final CarOptionsRepository carOptionsRepository;

    public CarOptionsService(CarOptionsRepository carOptionsRepository) {
        this.carOptionsRepository = carOptionsRepository;
    }

    public Optional<CarOptions> getOptionsByHeatedSeatsAndNavigation(Boolean hasHeatedSeats, Boolean hasNavigation) {
        return carOptionsRepository.findCarOptionsByHasHeatedSeatsAndHasNavigation(hasHeatedSeats, hasNavigation);
    }

    public List<CarOptions> getCarOptions() {
        List<CarOptions> carOptions = carOptionsRepository.findAll();
        return Collections.unmodifiableList(carOptions);
    }

    public void add(CarOptions carOptions) {
        try {
            carOptionsRepository.save(carOptions);
        } catch (Exception e) {
            throw new RuntimeException("Car options not saved: " + e.getMessage());
        }
    }

    public void edit(CarOptions carOptions) {
        try {
            carOptionsRepository.save(carOptions);
        } catch (Exception e) {
            throw new RuntimeException("Car options cannot be updated: " + e.getMessage());
        }
    }
}
