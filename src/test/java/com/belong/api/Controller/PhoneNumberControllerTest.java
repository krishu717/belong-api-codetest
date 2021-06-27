package com.belong.api.Controller;

import com.belong.api.Entity.PhoneNumber;
import com.belong.api.Service.BasicCustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PhoneNumberController.class)
@WebMvcTest
public class PhoneNumberControllerTest {

    @Autowired
    PhoneNumberController phoneNumberController;

    @MockBean
    BasicCustomerService basicCustomerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAllPhoneNumbersForAllCustomers() throws Exception {

        List<PhoneNumber> allPhoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber1 = new PhoneNumber("1","040000010",true);
        PhoneNumber phoneNumber2 = new PhoneNumber("2","040000011",false);
        allPhoneNumbers.add(phoneNumber1);
        allPhoneNumbers.add(phoneNumber2);

        Mockito.when(basicCustomerService.getAllPhoneNumbers()).thenReturn(allPhoneNumbers);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/allphonenumbers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "[{\"id\":\"1\",\"phoneNumber\":\"040000010\",\"active\":true}" +
                                ",{\"id\":\"2\",\"phoneNumber\":\"040000011\",\"active\":false}]"
                ));
    }

    @Test
    public void testGetAllPhoneNumbersForTheCustomer() throws Exception {

        List<PhoneNumber> allPhoneNumbers = new ArrayList<>();
        PhoneNumber phoneNumber1 = new PhoneNumber("1","040000010",true);
        PhoneNumber phoneNumber2 = new PhoneNumber("2","040000011",false);
        allPhoneNumbers.add(phoneNumber1);
        allPhoneNumbers.add(phoneNumber2);

        Mockito.when(basicCustomerService.getAllPhoneNumbersForTheCustomer(Mockito.anyString())).thenReturn(allPhoneNumbers);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1//phonenumbers")
                .param("customerId", "123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "[{\"id\":\"1\",\"phoneNumber\":\"040000010\",\"active\":true}" +
                                ",{\"id\":\"2\",\"phoneNumber\":\"040000011\",\"active\":false}]"
                ));
    }

    @Test
    public void testActivatePhoneNumber() throws Exception {

        PhoneNumber phoneNumberToBeActivated = new PhoneNumber("1","040000010",false);
        PhoneNumber activeReuqest = new PhoneNumber("","",true);
        PhoneNumber responseCustomer = new PhoneNumber("1","040000010",true);


        Mockito.when(basicCustomerService.findPhoneNumber(Mockito.anyString())).thenReturn(phoneNumberToBeActivated);
        Mockito.when(basicCustomerService.createResponseBody(Mockito.any(PhoneNumber.class), Mockito.any(PhoneNumber.class)))
        .thenReturn(responseCustomer);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1//phonenumber={phonenumber}","040000010")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(activeReuqest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":\"1\",\"phoneNumber\":\"040000010\",\"active\":true}"));

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
