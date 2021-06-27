package com.belong.api.Service;

import com.belong.api.Entity.Customer;
import com.belong.api.Entity.PhoneNumber;
import com.belong.api.Repository.CustomerRepository;
import com.belong.api.Repository.PhoneRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BasicCustomerService.class)
@SpringBootTest
public class BasicCustomerServiceTest {

    @Autowired
    BasicCustomerService basicCustomerService;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    PhoneRepository phoneRepository;

    private Customer customer1;
    private Customer customer2;
    private PhoneNumber phoneNumber1;
    private PhoneNumber phoneNumber2;
    private PhoneNumber requestBody;
    private List<PhoneNumber> allPhoneNumbers;
    private List<Customer> allCustomerDetails;


    @Before
    public void setUp() {
        allPhoneNumbers = new ArrayList<>();
        allCustomerDetails = new ArrayList<>();
        customer1 = new Customer("1", "100","0400000000");
        customer2 = new Customer("2", "100","0400000001");
        phoneNumber1 = new PhoneNumber("1","0400000000",true);
        phoneNumber2 = new PhoneNumber("2","0400000001",false);
        requestBody = new PhoneNumber("","",true);
        allPhoneNumbers.add(phoneNumber1);
        allPhoneNumbers.add(phoneNumber2);
        allCustomerDetails.add(customer1);
        allCustomerDetails.add(customer2);
    }

    @After
    public void tearDown() {
        phoneNumber1 = null;
        phoneNumber2 = null;
        allPhoneNumbers.clear();
    }

    @Test
    public void testGetFindAllPhoneNumbers(){
        Mockito.when(phoneRepository.findAll()).thenReturn(allPhoneNumbers);
        assertEquals(allPhoneNumbers,basicCustomerService.getAllPhoneNumbers());
    }

    @Test
    public void testGetAllPhoneNumbersForTheCustomer(){
        Mockito.when(customerRepository.findAll()).thenReturn(allCustomerDetails);
        Mockito.when(phoneRepository.findByPhoneNumber(phoneNumber1.getPhoneNumber())).thenReturn(phoneNumber1);
        Mockito.when(phoneRepository.findByPhoneNumber(phoneNumber2.getPhoneNumber())).thenReturn(phoneNumber2);
        assertEquals(allPhoneNumbers,basicCustomerService.getAllPhoneNumbersForTheCustomer(customer1.getCustomerId()));
    }

    @Test
    public void testFindPhoneNumber() {
        Mockito.when(phoneRepository.findByPhoneNumber(Mockito.anyString())).thenReturn(phoneNumber1);
        assertEquals(phoneNumber1, basicCustomerService.findPhoneNumber(phoneNumber1.getPhoneNumber()));
    }

    @Test
    public void testCreateResponseBody() {
        Mockito.when(phoneRepository.save(Mockito.any(PhoneNumber.class))).thenReturn(phoneNumber2);
        phoneNumber2.setActive(true);
        assertEquals(phoneNumber2, basicCustomerService.createResponseBody(phoneNumber2, requestBody));
    }
}
