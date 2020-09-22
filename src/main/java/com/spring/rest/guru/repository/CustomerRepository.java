package com.spring.rest.guru.repository;

import com.spring.rest.guru.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
