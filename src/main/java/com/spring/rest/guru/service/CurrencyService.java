package com.spring.rest.guru.service;

import com.spring.rest.guru.model.CurrencyDTO;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDTO> getAllCategories();
    CurrencyDTO getCategoryByName(String name);
}
