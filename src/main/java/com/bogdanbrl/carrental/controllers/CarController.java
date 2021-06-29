package com.bogdanbrl.carrental.controllers;

import com.bogdanbrl.carrental.dto.CarDTO;
import com.bogdanbrl.carrental.dto.FilterCriteriaDTO;
import com.bogdanbrl.carrental.services.CarService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin("http://localhost:4200")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("cars")
    public ResponseEntity getCars(@RequestBody FilterCriteriaDTO filterCriteriaDTO) {
        try {
            return ResponseEntity.ok(carService.getCars(filterCriteriaDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("searchCars")
    public ResponseEntity searchCarsByBrandOrModel(@Param(value = "searchValue") String searchValue) {
        try {
            return ResponseEntity.ok(carService.searchCarsByBrandOrModel(searchValue, searchValue));
        } catch (RuntimeException e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("car")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addCar(@RequestBody CarDTO carDTO) {
        try {
            carService.add(carDTO);
            return ResponseEntity.ok("Car added!");
        } catch (RuntimeException e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("car")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity editCar(@RequestBody CarDTO carDTO) {
        try {
            carService.edit(carDTO);
            return ResponseEntity.ok("Car details have been updated!");
        } catch (RuntimeException e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("car")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteCar(@RequestBody CarDTO carDTO) {
        try {
            carService.delete(carDTO);
            return ResponseEntity.ok("Car deleted!");
        } catch (RuntimeException e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("carDetails/{carId}")
    public ResponseEntity getCarById(@PathVariable Long carId) {
        try {
            return ResponseEntity.ok(carService.getCarById(carId));
        } catch (RuntimeException e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("carBodies")
    public ResponseEntity getCarBodies() {
        try {
            return ResponseEntity.ok(carService.getCarBodies());
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("carClasses")
    public ResponseEntity getCarClasses() {
        try {
            return ResponseEntity.ok(carService.getCarClasses());
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("engines")
    public ResponseEntity getEngines() {
        try {
            return ResponseEntity.ok(carService.getEngines());
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
