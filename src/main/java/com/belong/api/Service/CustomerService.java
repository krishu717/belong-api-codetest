package com.belong.api.Service;

import com.belong.api.Entity.PhoneNumber;

import java.util.List;


public interface CustomerService {

    List<PhoneNumber> getAllPhoneNumbers();

    List<PhoneNumber> getAllPhoneNumbersForTheCustomer(String customerId);

    PhoneNumber findPhoneNumber(String number);

}
