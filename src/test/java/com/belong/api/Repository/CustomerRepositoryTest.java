package com.belong.api.Repository;

import com.belong.api.Entity.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    Customer customer;
    List<Customer> allCustomerDetails;

    @Before
    public void setUp() {
        customer = new Customer("1","123","0400000010");
    }

    @After
    public void tearDown (){
        customer = null;
        allCustomerDetails.clear();
    }

    @Test
    public void testQuery() {
        allCustomerDetails = customerRepository.findAll();
        assert allCustomerDetails != null;
    }

}
