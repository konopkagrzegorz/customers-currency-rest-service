package com.spring.rest.guru.service;

import com.spring.rest.guru.model.CurrencyDTO;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDTO> getAllCurrency();
    CurrencyDTO getCurrencyByName(String name);
    CurrencyDTO save(CurrencyDTO currencyDTO);
    void deleteCurrency(String name);
}
