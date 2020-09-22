package com.spring.rest.guru.service;

import com.spring.rest.guru.domain.Currency;
import com.spring.rest.guru.mapper.CategoryMapper;
import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CategoryMapper categoryMapper;

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CategoryMapper categoryMapper, CurrencyRepository currencyRepository) {
        this.categoryMapper = categoryMapper;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<CurrencyDTO> getAllCategories() {
        List<Currency> categories = currencyRepository.findAll();
        return categories.stream().map(category -> {
            CurrencyDTO currencyDTO = categoryMapper.categoryToCategoryDTO(category);
            return currencyDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public CurrencyDTO getCategoryByName(String name) {
        Currency currency = currencyRepository.findByName(name);
        return categoryMapper.categoryToCategoryDTO(currency);
    }
}
