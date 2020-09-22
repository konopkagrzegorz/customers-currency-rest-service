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
    public List<CurrencyDTO> printAllCustomers() {
        return currencyService.getAllCategories();
    }

    @GetMapping("/currency/{name}")
    public CurrencyDTO printCategoryByName(@PathVariable String name) {
        return currencyService.getCategoryByName(name);
    }
}
