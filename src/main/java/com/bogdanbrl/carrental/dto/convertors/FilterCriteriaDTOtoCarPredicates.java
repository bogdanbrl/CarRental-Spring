package com.bogdanbrl.carrental.dto.convertors;

import com.bogdanbrl.carrental.dto.FilterCriteriaDTO;
import com.bogdanbrl.carrental.models.Car;
import com.bogdanbrl.carrental.predicates.CarPredicates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilterCriteriaDTOtoCarPredicates {

    public static List<Predicate<Car>> getPredicates(FilterCriteriaDTO fc) {
        List<Predicate<Car>> predicates = new ArrayList<>();

        if (fc.getBrand() != null && !fc.getBrand().trim().equals("")) {
            predicates.add(CarPredicates.hasBrand(fc.getBrand()));
        }

        if (fc.getModel() != null && !fc.getModel().trim().equals("")) {
            predicates.add(CarPredicates.hasModel(fc.getModel()));
        }

        if (fc.getYear() != null && !fc.getYear().trim().equals("")) {
            Integer year = Integer.parseInt(fc.getYear());

            if (year >= 1990 && year <= LocalDate.now().getYear()) {
                predicates.add(CarPredicates.isNewerThan(year));
            }
        }

        if (fc.getRentPrice() != null && !fc.getRentPrice().trim().equals("")) {
            Double rentPrice = Double.parseDouble(fc.getRentPrice());

            if (rentPrice >= 0.00) {
                predicates.add(CarPredicates.hasRentPriceLowerThan(rentPrice));
            }
        }

        if (fc.getCarBody() != null && !fc.getCarBody().trim().equals("")) {
            predicates.add(CarPredicates.hasCarBody(fc.getCarBody()));
        }

        if (fc.getCarClass() != null && !fc.getCarClass().trim().equals("")) {
            predicates.add(CarPredicates.hasCarClass(fc.getCarClass()));
        }

        if (fc.getEngine() != null && !fc.getEngine().trim().equals("")) {
            predicates.add(CarPredicates.hasEngine(fc.getEngine()));
        }

        if (fc.getHeatedSeats() != null && !fc.getHeatedSeats().trim().equals("")) {
            predicates.add(CarPredicates.hasHeatedSeats(fc.getHeatedSeats()));
        }

        if (fc.getNavigation() != null && !fc.getNavigation().trim().equals("")) {
            predicates.add(CarPredicates.hasNavigation(fc.getNavigation()));
        }

        return predicates;
    }
}
