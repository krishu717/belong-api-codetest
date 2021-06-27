package com.belong.api.Controller;

import com.belong.api.Entity.PhoneNumber;
import com.belong.api.Service.BasicCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PhoneNumberController {


    @Autowired
    BasicCustomerService basicCustomerService;

    @GetMapping("/allphonenumbers")
    public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbersForAllCustomers() {

        List<PhoneNumber> allPhoneNumbers = basicCustomerService.getAllPhoneNumbers();
        try {
            return new ResponseEntity<>(allPhoneNumbers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value="/phonenumbers")
    public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbersForTheCustomer(@RequestParam("customerId")
                                                                                            String customerId) {

        List<PhoneNumber> allPhoneNumbersForTheCustomer = basicCustomerService.getAllPhoneNumbersForTheCustomer(customerId);

        if(!allPhoneNumbersForTheCustomer.isEmpty()) {
            return new ResponseEntity<>(allPhoneNumbersForTheCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/phonenumber={phonenumber}")
    public ResponseEntity<PhoneNumber> activatePhoneNumber(@PathVariable("phonenumber") String number,
                                                           @RequestBody PhoneNumber phoneNumber) {

        PhoneNumber phoneNumberToBeActivated = basicCustomerService.findPhoneNumber(number);

        PhoneNumber responseBody = basicCustomerService.createResponseBody(phoneNumberToBeActivated, phoneNumber);

        if (responseBody != null) {
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
