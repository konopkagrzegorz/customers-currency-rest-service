package com.spring.rest.guru.service;

import com.spring.rest.guru.domain.Currency;
import com.spring.rest.guru.mapper.CurrencyMapper;
import com.spring.rest.guru.mapper.CurrencyMapperImpl;
import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.repository.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "PLN";

    CurrencyMapper currencyMapper;
    CurrencyService currencyService;

    @Mock
    CurrencyRepository currencyRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        currencyService = new CurrencyServiceImpl(CurrencyMapper.INSTANCE, currencyRepository);
        currencyMapper = new CurrencyMapperImpl();
    }

    @Test
    public void getAllCurrencyTest() throws Exception {
        //given
        List<Currency> categories = Arrays.asList(new Currency(), new Currency(), new Currency());
        when(currencyRepository.findAll()).thenReturn(categories);
        //when
        List<CurrencyDTO> currencyDTOS = currencyService.getAllCurrency();
        //then
        assertEquals(3, currencyDTOS.size());
    }

    @Test
    public void getCurrencyByNameTest () throws Exception {
        //given
        Currency currency = new Currency();
        currency.setId(ID);
        currency.setName(NAME);
        when(currencyRepository.findByName(NAME)).thenReturn(currency);
        //when
        CurrencyDTO currencyDTO = currencyService.getCurrencyByName(NAME);
        //then
        assertEquals(NAME, currencyDTO.getName());
    }

    @Test
    public void saveCurrencyTest() {
        //given
        Currency currency = new Currency();
        currency.setName(NAME);
        //when
        when(currencyRepository.save(currency)).thenReturn(currency);
        CurrencyDTO currencyDTO = currencyMapper.currencyToCurrencyDTO(currency);
        //then
        assertEquals(currencyService.save(currencyDTO),currencyDTO);
    }

    @Test
    public void deleteCurrencyTest() {
        //given
        Currency currency = new Currency();
        currency.setId(ID);
        currency.setName(NAME);
        //when
        when(currencyRepository.findByName(NAME)).thenReturn(currency);
        currencyRepository.delete(currency);
        verify(currencyRepository,times(1)).delete(currency);
    }

}