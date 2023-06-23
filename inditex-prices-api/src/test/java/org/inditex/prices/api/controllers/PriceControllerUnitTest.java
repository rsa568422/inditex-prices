package org.inditex.prices.api.controllers;

import org.inditex.prices.api.services.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static java.util.Calendar.JUNE;
import static org.inditex.prices.api.data.PriceData.PRICES;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
class PriceControllerUnitTest {

    private final MockMvc mvc;

    @MockBean
    private PriceService service;

    @Autowired
    public PriceControllerUnitTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void testFindApplicablePrice1() throws Exception {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-14-10.00.00";
        String url = String.format("/api/prices/%s&%s&%s", brandId, productId, date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 10, 0, 0);
        Date requestDate = calendar.getTime();

        when(service.findApplicablePrice(like(1L), like(35455L), like(requestDate))).thenReturn(Optional.of(PRICES.get(1L)));

        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.price").value(35.5))
                .andExpect(jsonPath("$.startDate").value("2020-06-14-00.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"));

        verify(service, times(1)).findApplicablePrice(any(), any(), any());
    }

    @Test
    void testFindApplicablePrice2() throws Exception {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-14-16.00.00";
        String url = String.format("/api/prices/%s&%s&%s", brandId, productId, date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 16, 0, 0);
        Date requestDate = calendar.getTime();

        when(service.findApplicablePrice(like(1L), like(35455L), like(requestDate))).thenReturn(Optional.of(PRICES.get(2L)));

        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.startDate").value("2020-06-14-15.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14-18.30.00"));

        verify(service, times(1)).findApplicablePrice(any(), any(), any());
    }

    @Test
    void testFindApplicablePrice3() throws Exception {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-14-21.00.00";
        String url = String.format("/api/prices/%s&%s&%s", brandId, productId, date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 21, 0, 0);
        Date requestDate = calendar.getTime();

        when(service.findApplicablePrice(like(1L), like(35455L), like(requestDate))).thenReturn(Optional.of(PRICES.get(1L)));

        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.price").value(35.5))
                .andExpect(jsonPath("$.startDate").value("2020-06-14-00.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"));

        verify(service, times(1)).findApplicablePrice(any(), any(), any());
    }

    @Test
    void testFindApplicablePrice4() throws Exception {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-15-10.00.00";
        String url = String.format("/api/prices/%s&%s&%s", brandId, productId, date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 15, 10, 0, 0);
        Date requestDate = calendar.getTime();

        when(service.findApplicablePrice(like(1L), like(35455L), like(requestDate))).thenReturn(Optional.of(PRICES.get(3L)));

        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.price").value(30.5))
                .andExpect(jsonPath("$.startDate").value("2020-06-15-00.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15-11.00.00"));

        verify(service, times(1)).findApplicablePrice(any(), any(), any());
    }

    @Test
    void testFindApplicablePrice5() throws Exception {
        String brandId = "1";
        String productId = "35455";
        String date = "2020-06-16-21.00.00";
        String url = String.format("/api/prices/%s&%s&%s", brandId, productId, date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 16, 21, 0, 0);
        Date requestDate = calendar.getTime();

        when(service.findApplicablePrice(like(1L), like(35455L), like(requestDate))).thenReturn(Optional.of(PRICES.get(4L)));

        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1L))
                .andExpect(jsonPath("$.productId").value(35455L))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.startDate").value("2020-06-15-16.00.00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"));

        verify(service, times(1)).findApplicablePrice(any(), any(), any());
    }

    @Test
    void testFindApplicablePriceNotFound() throws Exception {
        String brandId = "1";
        String productId = "3545";
        String date = "2020-06-16-21.00.00";
        String url = String.format("/api/prices/%s&%s&%s", brandId, productId, date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 16, 21, 0, 0);
        Date requestDate = calendar.getTime();

        when(service.findApplicablePrice(like(1L), like(3545L), like(requestDate))).thenReturn(Optional.empty());

        mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findApplicablePrice(any(), any(), any());
    }

    private static Long like (Long param) {
        return argThat(arg -> arg.equals(param));
    }

    private static Date like (Date param) {
        return argThat(arg -> arg.toString().equals(param.toString()));
    }
}