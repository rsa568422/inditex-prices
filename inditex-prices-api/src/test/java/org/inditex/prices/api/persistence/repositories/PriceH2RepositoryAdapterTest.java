package org.inditex.prices.api.persistence.repositories;

import org.iditex.prices.model.entities.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static java.util.Calendar.JUNE;
import static org.inditex.prices.api.data.PriceData.PRICES;
import static org.inditex.prices.api.data.PriceData.PRICE_DTOS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class PriceH2RepositoryAdapterTest {

    private PriceH2RepositoryAdapter adapter;

    @MockBean
    private PriceH2Repository repository;

    @BeforeEach
    void setUp() {
        adapter = new PriceH2RepositoryAdapter(repository);
    }

    @Test
    void testFindApplicablePrice1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 10, 0, 0);
        Date date = calendar.getTime();

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date))).thenReturn(Collections.singletonList(PRICE_DTOS.get(1L)));

        Optional<Price> applicablePrice = adapter.findApplicablePrice(1L, 35455L, date);

        assertAll(
                () -> assertTrue(applicablePrice.isPresent()),
                () -> assertEquals(Optional.of(PRICES.get(1L)), applicablePrice)
        );

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(any(), any(), any());
    }

    @Test
    void testFindApplicablePrice2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 16, 0, 0);
        Date date = calendar.getTime();

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date))).thenReturn(Collections.singletonList(PRICE_DTOS.get(2L)));

        Optional<Price> applicablePrice = adapter.findApplicablePrice(1L, 35455L, date);

        assertAll(
                () -> assertTrue(applicablePrice.isPresent()),
                () -> assertEquals(Optional.of(PRICES.get(2L)), applicablePrice)
        );

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(any(), any(), any());
    }

    @Test
    void testFindApplicablePrice3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 21, 0, 0);
        Date date = calendar.getTime();

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date))).thenReturn(Collections.singletonList(PRICE_DTOS.get(1L)));

        Optional<Price> applicablePrice = adapter.findApplicablePrice(1L, 35455L, date);

        assertAll(
                () -> assertTrue(applicablePrice.isPresent()),
                () -> assertEquals(Optional.of(PRICES.get(1L)), applicablePrice)
        );

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(any(), any(), any());
    }

    @Test
    void testFindApplicablePrice4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 15, 10, 0, 0);
        Date date = calendar.getTime();

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date))).thenReturn(Collections.singletonList(PRICE_DTOS.get(3L)));

        Optional<Price> applicablePrice = adapter.findApplicablePrice(1L, 35455L, date);

        assertAll(
                () -> assertTrue(applicablePrice.isPresent()),
                () -> assertEquals(Optional.of(PRICES.get(3L)), applicablePrice)
        );

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(any(), any(), any());
    }

    @Test
    void testFindApplicablePrice5() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 16, 21, 0, 0);
        Date date = calendar.getTime();

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date))).thenReturn(Collections.singletonList(PRICE_DTOS.get(4L)));

        Optional<Price> applicablePrice = adapter.findApplicablePrice(1L, 35455L, date);

        assertAll(
                () -> assertTrue(applicablePrice.isPresent()),
                () -> assertEquals(Optional.of(PRICES.get(4L)), applicablePrice)
        );

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(any(), any(), any());
    }

    private static Long like (Long param) {
        return argThat(arg -> arg.equals(param));
    }

    private static Date like (Date param) {
        return argThat(arg -> arg.toString().equals(param.toString()));
    }

}