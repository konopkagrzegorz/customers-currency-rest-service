package com.spring.rest.guru.mapper;

import com.spring.rest.guru.domain.Currency;
import com.spring.rest.guru.model.CurrencyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {

    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    CurrencyDTO currencyToCurrencyDTO(Currency currency);
    Currency currencyDTOToCurrency(CurrencyDTO currencyDTO);

}
