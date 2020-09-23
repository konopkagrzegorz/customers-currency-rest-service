package com.spring.rest.guru.service;

import com.spring.rest.guru.domain.Currency;
import com.spring.rest.guru.mapper.CurrencyMapper;
import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyMapper currencyMapper;

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyMapper currencyMapper, CurrencyRepository currencyRepository) {
        this.currencyMapper = currencyMapper;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<CurrencyDTO> getAllCurrency() {
        List<Currency> categories = currencyRepository.findAll();
        return categories.stream().map(category -> {
            CurrencyDTO currencyDTO = currencyMapper.currencyToCurrencyDTO(category);
            return currencyDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public CurrencyDTO getCurrencyByName(String name) {
        Currency currency = currencyRepository.findByName(name);
        return currencyMapper.currencyToCurrencyDTO(currency);
    }

    @Override
    public CurrencyDTO save(CurrencyDTO currencyDTO) {
        Currency currency = currencyMapper.currencyDTOToCurrency(currencyDTO);
        Currency saved = currencyRepository.save(currency);
        CurrencyDTO returnedCurrencyDTO = currencyMapper.currencyToCurrencyDTO(saved);
        return returnedCurrencyDTO;
    }

    @Override
    public void deleteCurrency(String  name)  {
        Currency currency = currencyRepository.findByName(name);
        currencyRepository.deleteById(currency.getId());
    }
}
