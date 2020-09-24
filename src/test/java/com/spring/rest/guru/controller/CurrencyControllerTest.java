package com.spring.rest.guru.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.guru.model.CurrencyDTO;
import com.spring.rest.guru.service.CurrencyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CurrencyControllerTest {

    public static final String NAME = "PLN";

    @Mock
    CurrencyService currencyService;

    @InjectMocks
    CurrencyController currencyController;

    MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(currencyController).build();
    }

    @Test
    public void getAllCurrencyTest() throws Exception {
        //given
        CurrencyDTO currencyDTO1 = new CurrencyDTO();
        currencyDTO1.setName("PLN");

        CurrencyDTO currencyDTO2 = new CurrencyDTO();
        currencyDTO2.setName("EUR");

        List<CurrencyDTO> categories = new ArrayList<>();
        categories.add(currencyDTO1);
        categories.add(currencyDTO2);
        //when
        when(currencyService.getAllCurrency()).thenReturn(categories);
        //then
        mockMvc.perform(get("/api/currency")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)));
    }

    @Test
    public void getCurrencyByNameTest() throws Exception {
        //given
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setName(NAME);
        //when
        when(currencyService.getCurrencyByName(anyString())).thenReturn(currencyDTO);
        //then
        mockMvc.perform(get("/api/currency/" + NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void addNewCurrencyTest() throws Exception {
        //given
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setName(NAME);
        //when
        when(currencyService.save(ArgumentMatchers.any())).thenReturn(currencyDTO);
        String json = mapper.writeValueAsString(currencyDTO);
        //then
        mockMvc.perform(post("/api/currency/add-new")
                .contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));

    }

    @Test
    public void deleteCurrency() throws Exception {
        //given
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setName(NAME);
        //when;
        when(currencyService.save(currencyDTO)).thenReturn(currencyDTO);
        //then
        mockMvc.perform(delete("/api/currency/delete/" + NAME)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}