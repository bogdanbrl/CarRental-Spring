package com.bogdanbrl.carrental.controllers;

import com.bogdanbrl.carrental.dto.CarDTO;
import com.bogdanbrl.carrental.models.Car;
import com.bogdanbrl.carrental.models.User;
import com.bogdanbrl.carrental.services.CarService;
import com.bogdanbrl.carrental.services.RentService;
import com.bogdanbrl.carrental.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class RentController {

    private final RentService rentService;
    private final UserService userService;
    private final CarService carService;

    public RentController(RentService rentService, UserService userService, CarService carService) {
        this.rentService = rentService;
        this.userService = userService;
        this.carService = carService;
    }

    private User getPrincipalUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        return userService.getUserByUsername(principalName);
    }

    @GetMapping("user/history")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity userHistory(){
        try {
            User user = getPrincipalUser();
            return ResponseEntity.ok(rentService.getUserHistory(user));
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("car/history/{carID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity carHistory(@PathVariable Long carID){
        try {
            Car car = carService.getCarByIdFromDB(carID);
            return ResponseEntity.ok(rentService.getCarHistory(car));
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("rents")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity rents (){
        try {
            return ResponseEntity.ok(rentService.getRents());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("currentRents")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity currentRents(){
        try {
            return ResponseEntity.ok(rentService.getCurrentRents());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("rentsByPeriod")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getRentsByPeriod(@Param(value = "startPeriod") String startPeriod,
                                           @Param(value = "endPeriod") String endPeriod){
        try {
            return ResponseEntity.ok(rentService.getByPeriod(startPeriod, endPeriod));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("rentCar")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity rentCar(@Param(value = "startPeriod") String startPeriod,
                                  @Param(value = "endPeriod") String endPeriod,
                                  @Param(value = "carID") Long carID){
        try {
            String message = rentService.rentCar(startPeriod, endPeriod, carID, getPrincipalUser());
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
