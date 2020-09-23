package com.spring.rest.guru.mapper;

import com.spring.rest.guru.domain.Currency;
import com.spring.rest.guru.model.CurrencyDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CurrencyMapper currencyMapper = CurrencyMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        //given
        Currency currency = new Currency();
        currency.setName(NAME);
        currency.setId(ID);

        //when
        CurrencyDTO currencyDTO = currencyMapper.currencyToCurrencyDTO(currency);

        //then
        assertEquals(Long.valueOf(ID), currencyDTO.getId());
        assertEquals(NAME, currencyDTO.getName());
    }

}