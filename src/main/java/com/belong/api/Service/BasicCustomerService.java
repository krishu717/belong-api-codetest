package com.belong.api.Service;

import com.belong.api.Entity.Customer;
import com.belong.api.Entity.PhoneNumber;
import com.belong.api.Repository.CustomerRepository;
import com.belong.api.Repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasicCustomerService implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Override
    public List<PhoneNumber> getAllPhoneNumbers() {

        List<PhoneNumber> allPhoneNumbers = new ArrayList<>();
        phoneRepository.findAll().forEach(allPhoneNumbers::add);

        return allPhoneNumbers;

    }

    @Override
    public List<PhoneNumber> getAllPhoneNumbersForTheCustomer(String customerId) {

        List<Customer> customerDetails = new ArrayList<>();
        List<PhoneNumber> allPhoneNumbersForTheCustomer = new ArrayList<>();

        customerRepository.findAll().stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .forEach(customerDetails::add);

        customerDetails.stream()
                .forEach(customer ->
                        allPhoneNumbersForTheCustomer.add(phoneRepository.findByPhoneNumber(customer.getPhoneNumber())));

        return allPhoneNumbersForTheCustomer;
    }

    @Override
    public PhoneNumber findPhoneNumber(String number) {
        PhoneNumber phoneNumberToBeActivated = phoneRepository.findByPhoneNumber(number);
        return phoneNumberToBeActivated;
    }

    public PhoneNumber createResponseBody(PhoneNumber phoneNumberToBeActivated, PhoneNumber requestBody){

        if(phoneNumberToBeActivated != null) {
            PhoneNumber _phoneNumber = phoneNumberToBeActivated;
            if (requestBody.getActive()) {
                _phoneNumber.setActive(requestBody.getActive());
            }
            phoneRepository.save(_phoneNumber);
            return _phoneNumber;
        }else {
            return null;
        }
    }
}
