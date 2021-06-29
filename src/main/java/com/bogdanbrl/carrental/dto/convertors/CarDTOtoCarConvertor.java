package com.bogdanbrl.carrental.dto.convertors;

import com.bogdanbrl.carrental.dto.CarDTO;
import com.bogdanbrl.carrental.models.Car;
import com.bogdanbrl.carrental.models.ECarBody;
import com.bogdanbrl.carrental.models.ECarClass;
import com.bogdanbrl.carrental.models.ECarEngine;

public class CarDTOtoCarConvertor {

    public static Car convertCarDTOtoCar(CarDTO carDTO){

        Car car = new Car();

        car.setId(carDTO.getId());
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setRentPrice(carDTO.getRentPrice());
        car.setCarClass(ECarClass.convertToCarClass(carDTO.getCarClass()));
        car.setCarBody(ECarBody.convertToCarBody(carDTO.getCarBody()));
        car.setEngine(ECarEngine.convertToEngine(carDTO.getEngine()));
        car.setImage(carDTO.getImage());
        car.setCarOptions(carDTO.getCarOptions());

        return car;
    }
}
