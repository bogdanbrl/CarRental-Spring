package com.bogdanbrl.carrental.controllers;

import com.bogdanbrl.carrental.models.Address;
import com.bogdanbrl.carrental.models.User;
import com.bogdanbrl.carrental.repository.RentRepository;
import com.bogdanbrl.carrental.services.AddressService;
import com.bogdanbrl.carrental.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;
    private final RentRepository rentRepository;

    public UserController(UserService userService, AddressService addressService, RentRepository rentRepository) {
        this.userService = userService;
        this.addressService = addressService;
        this.rentRepository = rentRepository;
    }

    @PostMapping("address/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity addAddress(@RequestBody Address address){
        try {
            addressService.add(address, getPrincipalUser());
            return ResponseEntity.ok("Address added!");
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("address/delete/{addressId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity deleteAddress(@PathVariable("addressId") Long addressId){
        try {
            Address address = addressService.getAddressById(addressId);
            addressService.delete(address, getPrincipalUser());
            return ResponseEntity.ok("Address deleted!");
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("address/edit")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity updateAddress(@RequestBody Address address){
        try {
            addressService.edit(address);
            return ResponseEntity.ok("Address updated!");
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("address/getById/{addressID}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getAddressByID(@PathVariable Long addressID){
        try {
            return ResponseEntity.ok(addressService.getAddressById(addressID));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUsers(){
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("user/getById/{userID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUserById(@PathVariable Long userID){
        try {
            return ResponseEntity.ok(userService.getUserById(userID));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("user/edit")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity editUser(@RequestBody User user){
        try {
            userService.edit(user);
            return ResponseEntity.ok("User updated!");
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("user/principal")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getUserByPrincipal(){
        try {
            return ResponseEntity.ok(userService.getUserByUsername(getPrincipalUser().getUsername()));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("user/historyById/{userID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUserHistory(@PathVariable Long userID){
        try {
            return ResponseEntity.ok(userService.getUserHistory(userID));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("user/history")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getUserHistory(){
        try {
            return ResponseEntity.ok(userService.getUserHistory(getPrincipalUser()));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("user/details")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getUserDetails(){
        try {
            return ResponseEntity.ok(getPrincipalUser());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    private User getPrincipalUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByUsername(currentPrincipalName);
        return user;
    }
}
