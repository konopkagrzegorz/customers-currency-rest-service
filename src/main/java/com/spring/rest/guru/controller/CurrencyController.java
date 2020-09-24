package com.spring.rest.guru.controller;

import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }
    @GetMapping("/currency")
    public List<CurrencyDTO> getAllCustomers() {
        return currencyService.getAllCurrency();
    }

    @GetMapping("/currency/{name}")
    public CurrencyDTO getCurrencyByName(@PathVariable String name) {
        return currencyService.getCurrencyByName(name);
    }

    @PostMapping("/currency/add-new")
    public CurrencyDTO addNewCurrency(@RequestBody CurrencyDTO currencyDTO) {
        return currencyService.save(currencyDTO);
    }

    @DeleteMapping("/currency/delete/{name}")
    public void deleteCurrency(@PathVariable String name) {
        currencyService.deleteCurrency(name);
    }
}
