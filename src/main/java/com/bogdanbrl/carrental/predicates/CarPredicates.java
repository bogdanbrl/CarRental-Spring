package com.bogdanbrl.carrental.predicates;

import com.bogdanbrl.carrental.models.Car;
import com.bogdanbrl.carrental.models.ECarBody;
import com.bogdanbrl.carrental.models.ECarClass;
import com.bogdanbrl.carrental.models.ECarEngine;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CarPredicates {

    public static Predicate<Car> hasBrand(String brand) {
        return p -> p.getBrand().equalsIgnoreCase(brand);
    }

    public static Predicate<Car> hasModel(String model) {
        return p -> p.getModel().equalsIgnoreCase(model);
    }

    public static Predicate<Car> isNewerThan(int year) {
        return p -> p.getYear() >= year;
    }

    public static Predicate<Car> hasRentPriceLowerThan(double price) {
        return p -> Double.compare(p.getRentPrice(), price) <= 0;
    }

    public static Predicate<Car> hasCarBody(String carBody) {
        return p -> p.getCarBody().equals(ECarBody.convertToCarBody(carBody));
    }

    public static Predicate<Car> hasCarClass(String carClass) {
        return p -> p.getCarClass().equals(ECarClass.convertToCarClass(carClass));
    }

    public static Predicate<Car> hasEngine(String engine) {
        return p -> p.getEngine().equals(ECarEngine.convertToEngine(engine));
    }

    public static Predicate<Car> hasHeatedSeats(String heatedSeats) {
        return p -> p.getCarOptions().getHasHeatedSeats() == Boolean.valueOf(heatedSeats);
    }

    public static Predicate<Car> hasNavigation(String navigation) {
        return p -> p.getCarOptions().getHasNavigation() == Boolean.valueOf(navigation);
    }

    public static List<Car> filterCarsOnePredicate(List<Car> cars, Predicate<Car> predicate) {
        return cars.stream()
                .filter(predicate)
                .collect(Collectors.<Car>toList());
    }

    public static List<Car> filterCarsMultiplePredicates(List<Car> cars, List<Predicate<Car>> predicates) {
        return cars.stream().filter(
                predicates.stream().reduce(Predicate::and).orElse(t -> false)
        ).collect(Collectors.<Car>toList());
    }
}
