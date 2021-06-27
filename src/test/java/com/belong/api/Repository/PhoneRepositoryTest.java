package com.belong.api.Repository;

import com.belong.api.Entity.Customer;
import com.belong.api.Entity.PhoneNumber;
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
public class PhoneRepositoryTest {

    @Autowired
    PhoneRepository phoneRepository;

    PhoneNumber phoneNumber;
    List<PhoneNumber> allPhoneNumbers;

    @Before
    public void setUp() {
        phoneNumber = new PhoneNumber("1","0400000010",true);
    }

    @After
    public void tearDown (){
        phoneNumber = null;
        allPhoneNumbers.clear();
    }

    @Test
    public void testQuery() {
        allPhoneNumbers = phoneRepository.findAll();
        assert allPhoneNumbers != null; }
}
