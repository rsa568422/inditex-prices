package org.inditex.prices.api.persistence.repositories;

import org.inditex.prices.api.data.PriceData;
import org.inditex.prices.api.persistence.entities.PriceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Calendar.JUNE;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PriceH2RepositoryIntegrationTest {

    private final PriceH2Repository repository;

    @Autowired
    private PriceH2RepositoryIntegrationTest(PriceH2Repository repository) {
        this.repository = repository;
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 10, 0, 0);
        Date date = calendar.getTime();

        List<PriceDto> prices = repository.findByBrandIdAndProductIdAndDate(1L, 35455L, date);

        assertAll(
                () -> assertEquals(1, prices.size()),
                () -> assertTrue(prices.stream().map(PriceDto::getPriceList).collect(Collectors.toSet()).contains(1L)),
                () -> assertEquals(PriceData.PRICE_DTOS.get(1L).getPriceList(), prices.get(0).getPriceList()),
                () -> assertEquals(PriceData.PRICE_DTOS.get(1L).getBrand().getId(), prices.get(0).getBrand().getId()),
                () -> assertEquals(PriceData.PRICE_DTOS.get(1L).getBrand().getName(), prices.get(0).getBrand().getName()),
                () -> assertEquals(PriceData.PRICE_DTOS.get(1L).getBrand().toString(), prices.get(0).getBrand().toString()),
                () -> assertEquals(PriceData.PRICE_DTOS.get(1L).getProduct().getId(), prices.get(0).getProduct().getId()),
                () -> assertEquals(PriceData.PRICE_DTOS.get(1L).getProduct().getName(), prices.get(0).getProduct().getName()),
                () -> assertEquals(PriceData.PRICE_DTOS.get(1L).getProduct().toString(), prices.get(0).getProduct().toString())
        );
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 16, 0, 0);
        Date date = calendar.getTime();

        List<PriceDto> prices = repository.findByBrandIdAndProductIdAndDate(1L, 35455L, date);

        assertAll(
                () -> assertEquals(2, prices.size()),
                () -> assertTrue(prices.stream().map(PriceDto::getPriceList).collect(Collectors.toSet()).containsAll(Set.of(1L, 2L)))
        );
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 14, 21, 0, 0);
        Date date = calendar.getTime();

        List<PriceDto> prices = repository.findByBrandIdAndProductIdAndDate(1L, 35455L, date);

        assertAll(
                () -> assertEquals(1, prices.size()),
                () -> assertTrue(prices.stream().map(PriceDto::getPriceList).collect(Collectors.toSet()).contains(1L))
        );
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 15, 10, 0, 0);
        Date date = calendar.getTime();

        List<PriceDto> prices = repository.findByBrandIdAndProductIdAndDate(1L, 35455L, date);

        assertAll(
                () -> assertEquals(2, prices.size()),
                () -> assertTrue(prices.stream().map(PriceDto::getPriceList).collect(Collectors.toSet()).containsAll(Set.of(1L, 3L)))
        );
    }

    @Test
    void testFindByBrandIdAndProductIdAndDate5() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, JUNE, 16, 21, 0, 0);
        Date date = calendar.getTime();

        List<PriceDto> prices = repository.findByBrandIdAndProductIdAndDate(1L, 35455L, date);

        assertAll(
                () -> assertEquals(2, prices.size()),
                () -> assertTrue(prices.stream().map(PriceDto::getPriceList).collect(Collectors.toSet()).containsAll(Set.of(1L, 4L)))
        );
    }

}