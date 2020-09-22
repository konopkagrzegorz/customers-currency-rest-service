package com.spring.rest.guru.service;

import com.spring.rest.guru.domain.Currency;
import com.spring.rest.guru.mapper.CategoryMapper;
import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.repository.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CurrencyServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "John";

    CurrencyService currencyService;

    @Mock
    CurrencyRepository currencyRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        currencyService = new CurrencyServiceImpl(CategoryMapper.INSTANCE, currencyRepository);
    }

    @Test
    public void getAllCategories() throws Exception {
        //given
        List<Currency> categories = Arrays.asList(new Currency(), new Currency(), new Currency());
        when(currencyRepository.findAll()).thenReturn(categories);
        //when
        List<CurrencyDTO> currencyDTOS = currencyService.getAllCategories();
        //then
        assertEquals(3, currencyDTOS.size());
    }

    @Test
    public void getCategoryByName() throws Exception {
        //given
        Currency currency = new Currency();
        currency.setId(ID);
        currency.setName(NAME);
        when(currencyRepository.findByName(anyString())).thenReturn(currency);
        //when
        CurrencyDTO currencyDTO = currencyService.getCategoryByName(NAME);
        //then
        assertEquals(ID, currencyDTO.getId());
        assertEquals(NAME, currencyDTO.getName());
    }
}