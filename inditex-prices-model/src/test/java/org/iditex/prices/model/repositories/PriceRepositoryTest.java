package org.iditex.prices.model.repositories;

import org.iditex.prices.model.data.PriceData;
import org.iditex.prices.model.entities.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.iditex.prices.model.data.PriceData.PRICES;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceRepositoryTest {

    @Spy
    private PriceRepository repository;

    @Test
    void testFindApplicablePrice1() {
        Date date1 = PriceData.getDate(2020, Calendar.JUNE, 13, 0, 0, 0);

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date1)))
                .thenReturn(expectedPrices(1L, 35455L, date1));

        Optional<Price> price1 = repository.findApplicablePrice(1L, 35455L, date1);

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(1L, 35455L, date1);
        assertFalse(price1.isPresent());
    }

    @Test
    void testFindApplicablePrice2() {
        Date date2 = PriceData.getDate(2020, Calendar.JUNE, 14, 0, 0, 0);

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date2)))
                .thenReturn(expectedPrices(1L, 35455L, date2));

        Optional<Price> price2 = repository.findApplicablePrice(1L, 35455L, date2);

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(1L, 35455L, date2);
        assertAll(
                () -> assertTrue(price2.isPresent()),
                () -> assertEquals(1L, price2.get().getPriceList())
        );
    }

    @Test
    void testFindApplicablePrice3() {
        Date date3 = PriceData.getDate(2020, Calendar.JUNE, 15, 0, 0, 0);

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date3)))
                .thenReturn(expectedPrices(1L, 35455L, date3));

        Optional<Price> price3 = repository.findApplicablePrice(1L, 35455L, date3);

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(1L, 35455L, date3);
        assertAll(
                () -> assertTrue(price3.isPresent()),
                () -> assertEquals(3L, price3.get().getPriceList())
        );
    }

    @Test
    void testFindApplicablePrice4() {
        Date date4 = PriceData.getDate(2020, Calendar.JUNE, 15, 16, 0, 0);

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date4)))
                .thenReturn(expectedPrices(1L, 35455L, date4));

        Optional<Price> price4 = repository.findApplicablePrice(1L, 35455L, date4);

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(1L, 35455L, date4);
        assertAll(
                () -> assertTrue(price4.isPresent()),
                () -> assertEquals(4L, price4.get().getPriceList())
        );
    }

    @Test
    void testFindApplicablePrice5() {
        Date date5 = PriceData.getDate(2020, Calendar.JUNE, 16, 0, 0, 0);

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date5)))
                .thenReturn(expectedPrices(1L, 35455L, date5));

        Optional<Price> price5 = repository.findApplicablePrice(1L, 35455L, date5);

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(1L, 35455L, date5);
        assertAll(
                () -> assertTrue(price5.isPresent()),
                () -> assertEquals(4L, price5.get().getPriceList())
        );
    }

    @Test
    void testFindApplicablePrice6() {
        Date date6 = PriceData.getDate(2020, Calendar.DECEMBER, 31, 23, 59, 59);

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date6)))
                .thenReturn(expectedPrices(1L, 35455L, date6));

        Optional<Price> price6 = repository.findApplicablePrice(1L, 35455L, date6);

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(1L, 35455L, date6);
        assertAll(
                () -> assertTrue(price6.isPresent()),
                () -> assertEquals(4L, price6.get().getPriceList())
        );
    }

    @Test
    void testFindApplicablePrice7() {
        Date date7 = PriceData.getDate(2021, Calendar.JANUARY, 1, 0, 0, 0);

        when(repository.findByBrandIdAndProductIdAndDate(like(1L), like(35455L), like(date7)))
                .thenReturn(expectedPrices(1L, 35455L, date7));

        Optional<Price> price7 = repository.findApplicablePrice(1L, 35455L, date7);

        verify(repository, times(1)).findByBrandIdAndProductIdAndDate(1L, 35455L, date7);
        assertFalse(price7.isPresent());
    }

    private static Long like (Long param) {
        return argThat(arg -> arg.equals(param));
    }

    private static Date like (Date param) {
        return argThat(arg -> arg.equals(param));
    }

    private static List<Price> expectedPrices(Long brandId, Long productId, Date date) {
        return PRICES.values().stream()
                .filter(price -> price.getBrand().getId().equals(brandId))
                .filter(price -> price.getProduct().getId().equals(productId))
                .filter(price -> price.isApplicable(date))
                .collect(Collectors.toList());
    }
}