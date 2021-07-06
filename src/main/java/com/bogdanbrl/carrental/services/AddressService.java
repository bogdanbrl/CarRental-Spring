package com.bogdanbrl.carrental.services;

import com.bogdanbrl.carrental.models.Address;
import com.bogdanbrl.carrental.models.User;
import com.bogdanbrl.carrental.repository.AddressRepository;
import com.bogdanbrl.carrental.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public Address getAddressById(Long id){
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public List<Address> getAddresses(){
        List<Address> addresses = addressRepository.findAll();
        return Collections.unmodifiableList(addresses);
    }

    public void add(Address address, User principalUser){
        try {
            principalUser.addAddress(address);
            addressRepository.save(address);
        } catch (Exception e) {
            throw new RuntimeException("Cannot add address " + e.getMessage());
        }
    }

    public void edit(Address address){
        try {
            Address addressDB = getAddressById(address.getId());
            addressDB.setStreet(address.getStreet());
            addressDB.setStreetNumber(address.getStreetNumber());
            addressDB.setCity(address.getCity());
            addressDB.setCountry(address.getCountry());
            addressRepository.save(addressDB);
        } catch (Exception e) {
            throw new RuntimeException("Address cannot be updated " + e.getMessage());
        }
    }

    public void delete(Address address, User principalUser){
        try {
            address.removeUser(principalUser);
            principalUser.removeAddress(address);

            addressRepository.delete(address);

        } catch (Exception e) {
            throw new RuntimeException("Cannot delete address " + e.getMessage());
        }
    }
}
