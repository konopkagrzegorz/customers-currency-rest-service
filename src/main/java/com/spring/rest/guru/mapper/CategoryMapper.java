package com.spring.rest.guru.mapper;

import com.spring.rest.guru.domain.Currency;
import com.spring.rest.guru.model.CurrencyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CurrencyDTO categoryToCategoryDTO(Currency currency);

}
