package com.spring.rest.guru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.model.CustomerDTO;
import com.spring.rest.guru.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    private static final Long ID = 30L;
    private static final String FIRST_NAME = "Grzegorz";
    private static final String LAST_NAME = "Konopka";

    private static final String FIRST_NAME2 = "Cezary";
    private static final String LAST_NAME2 = "Zak";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName(LAST_NAME);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME2);
        customerDTO2.setFirstName(LAST_NAME2);

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customerDTOS.add(customerDTO1);
        customerDTOS.add(customerDTO2);
        //when
        when(customerService.getAllCustomers()).thenReturn(customerDTOS);
        //then
        mockMvc.perform(get("/api/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        //given
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName(LAST_NAME);
        //when
        when(customerService.getCustomerById(ID)).thenReturn(customerDTO1);
        //then
        mockMvc.perform(get("/api/customers/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void saveCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        //when
        when(customerService.save(ArgumentMatchers.any(CustomerDTO.class))).thenReturn(customerDTO);
        String json = mapper.writeValueAsString(customerDTO);
        //then
        mockMvc.perform(post("/api/customers/add-new")
                .contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void updateCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        //when
        when(customerService.save(customerDTO)).thenReturn(customerDTO);
        customerDTO.setLastName(LAST_NAME2);
        when(customerService.save(customerDTO)).thenReturn(customerDTO);
        String json = mapper.writeValueAsString(customerDTO);
        //then
        mockMvc.perform(put("/api/customers/update/" + ID)
                .contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME2)));
    }

    @Test
    public void deleteCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        //when;
        when(customerService.save(customerDTO)).thenReturn(customerDTO);
        //then
        mockMvc.perform(delete("/api/customers/delete/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}