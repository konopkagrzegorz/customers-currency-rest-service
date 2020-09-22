package com.spring.rest.guru.service;

import com.spring.rest.guru.domain.Customer;
import com.spring.rest.guru.mapper.CustomerMapper;
import com.spring.rest.guru.model.CustomerDTO;
import com.spring.rest.guru.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> {CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            return customerDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customerRepository.findById(id).get());
        return customerDTO;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer saved = customerRepository.save(customer);
        CustomerDTO returnedConsumerDTO = customerMapper.customerToCustomerDTO(saved);
        return returnedConsumerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        Customer saved = customerRepository.save(customer);
        CustomerDTO returnedCustomerDTO = customerMapper.customerToCustomerDTO(saved);
        return returnedCustomerDTO;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
