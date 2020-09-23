package com.spring.rest.guru.service;

import com.spring.rest.guru.domain.Customer;
import com.spring.rest.guru.mapper.CurrencyMapper;
import com.spring.rest.guru.mapper.CurrencyMapperImpl;
import com.spring.rest.guru.mapper.CustomerMapper;
import com.spring.rest.guru.mapper.CustomerMapperImpl;
import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.model.CustomerDTO;
import com.spring.rest.guru.repository.CustomerRepository;
import org.hibernate.dialect.CUBRIDDialect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private static final Long ID = 2L;
    private static final String FIRST_NAME = "Grzegorz";
    private static final String LAST_NAME = "Konopka";

    CustomerMapper customerMapper;
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerMapper = new CustomerMapperImpl();
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    public void getAllCustomers() {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        //when
        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        //then
        assertEquals(3,customerDTOS.size());
    }

    @Test
    public void getCustomerById() {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        //when
        when(customerRepository.findById(ID)).thenReturn(java.util.Optional.of(customer));
        CustomerDTO customerDTO = customerService.getCustomerById(ID);
        //then
        assertEquals(customerService.getCustomerById(ID),customerDTO);
    }

    @Test
    public void save() {
        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        //when
        when(customerRepository.save(customer)).thenReturn(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        //then
        assertEquals(customerService.save(customerDTO),customerDTO);
    }

    @Test
    public void updateCustomer() {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        //when
        when(customerRepository.findById(ID)).thenReturn(java.util.Optional.of(customer));
        //then
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setFirstName(FIRST_NAME + " TEST");
        Customer customerChanged = customerMapper.customerDTOToCustomer(customerDTO);
        when(customerRepository.findById(ID)).thenReturn(java.util.Optional.of(customerChanged));
        assertEquals(FIRST_NAME + " TEST",customerService.getCustomerById(ID).getFirstName());
    }

    @Test
    public void deleteCustomerById() {
        //given
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
        //when
        when(customerRepository.findById(customer.getId())).thenReturn(java.util.Optional.of(customer));
        //then
        customerRepository.delete(customer);
        verify(customerRepository,times(1)).delete(customer);
    }
}