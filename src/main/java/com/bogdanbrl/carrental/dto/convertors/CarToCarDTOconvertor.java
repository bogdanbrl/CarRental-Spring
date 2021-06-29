package com.bogdanbrl.carrental.dto.convertors;

import com.bogdanbrl.carrental.dto.CarDTO;
import com.bogdanbrl.carrental.models.Car;

public class CarToCarDTOconvertor {

    public static CarDTO convertCartoCarDTO(Car car){

        CarDTO carDTO = new CarDTO();

        carDTO.setId(car.getId());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setYear(car.getYear());
        carDTO.setRentPrice(car.getRentPrice());
        carDTO.setCarClass(car.getCarClass().getName());
        carDTO.setCarBody(car.getCarBody().getName());
        carDTO.setEngine(car.getEngine().getName());
        carDTO.setImage(car.getImage());
        carDTO.setCarOptions(car.getCarOptions());
        carDTO.setRents(car.getRents());

        return carDTO;
    }
}
