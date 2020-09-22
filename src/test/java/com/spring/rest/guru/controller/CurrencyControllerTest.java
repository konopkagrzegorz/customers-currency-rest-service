package com.spring.rest.guru.controller;

import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.service.CurrencyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CurrencyControllerTest {

    public static final String NAME = "Jim";

    @Mock
    CurrencyService currencyService;

    @InjectMocks
    CurrencyController currencyController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(currencyController).build();
    }

    @Test
    public void printAllCustomers() throws Exception {
        //given
        CurrencyDTO currencyDTO1 = new CurrencyDTO();
        currencyDTO1.setId(1L);
        currencyDTO1.setName("Junky");

        CurrencyDTO currencyDTO2 = new CurrencyDTO();
        currencyDTO2.setId(2L);
        currencyDTO2.setName("Fresh");

        List<CurrencyDTO> categories = new ArrayList<>();
        categories.add(currencyDTO1);
        categories.add(currencyDTO2);
        //when
        when(currencyService.getAllCategories()).thenReturn(categories);
        //then
        mockMvc.perform(get("/api/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void printCategoryByName() throws Exception {
        CurrencyDTO category1 = new CurrencyDTO();
        category1.setId(1l);
        category1.setName(NAME);

        when(currencyService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get("/api/categories/Jim")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }
}