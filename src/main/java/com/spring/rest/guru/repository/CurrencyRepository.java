package com.spring.rest.guru.repository;

import com.spring.rest.guru.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByName(String name);
}
