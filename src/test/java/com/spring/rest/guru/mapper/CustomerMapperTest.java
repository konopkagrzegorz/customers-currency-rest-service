package com.spring.rest.guru.mapper;

import com.spring.rest.guru.domain.Customer;
import com.spring.rest.guru.model.CustomerDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    private static final Long  ID = 10L;
    private static final String  FIRST_NAME = "Grzegorz";
    private static final String  LAST_NAME = "Konopka";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        //then
        assertEquals(FIRST_NAME,customerDTO.getFirstName());
        assertEquals(LAST_NAME,customerDTO.getLastName());
    }

    @Test
    public void customerDTOToCustomer() {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);
        //when
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        //then
        assertEquals(FIRST_NAME,customer.getFirstName());
        assertEquals(LAST_NAME,customer.getLastName());
    }
}