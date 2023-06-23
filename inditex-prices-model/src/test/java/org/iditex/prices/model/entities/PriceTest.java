package org.iditex.prices.model.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import static org.iditex.prices.model.data.BrandData.BRAND;
import static org.iditex.prices.model.data.PriceData.PRICES;
import static org.iditex.prices.model.data.PriceData.getDate;
import static org.iditex.prices.model.data.ProductData.PRODUCT;
import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void testIsApplicable() {
        Date date1 = getDate(2020, Calendar.JUNE, 13, 0, 0, 0);
        Date date2 = getDate(2020, Calendar.JUNE, 14, 0, 0, 0);
        Date date3 = getDate(2020, Calendar.JUNE, 15, 0, 0, 0);
        Date date4 = getDate(2020, Calendar.JUNE, 15, 16, 0, 0);
        Date date5 = getDate(2020, Calendar.JUNE, 16, 0, 0, 0);
        Date date6 = getDate(2020, Calendar.DECEMBER, 31, 23, 59, 59);
        Date date7 = getDate(2021, Calendar.JANUARY, 1, 0, 0, 0);

        assertAll(
                () -> {
                    Boolean visibles = PRICES.values().stream()
                            .map(price -> price.isApplicable(date1))
                            .reduce(false, Boolean::logicalOr);
                    assertFalse(visibles);
                },
                () -> assertAll(
                        () -> assertTrue(PRICES.get(1L).isApplicable(date2)),
                        () -> {
                            Boolean visibles = PRICES.values().stream()
                                    .filter(price -> !price.getPriceList().equals(1L))
                                    .map(price -> price.isApplicable(date2))
                                    .reduce(false, Boolean::logicalOr);
                            assertFalse(visibles);
                        }
                ),
                () -> assertAll(
                        () -> assertTrue(PRICES.get(1L).isApplicable(date3)),
                        () -> assertFalse(PRICES.get(2L).isApplicable(date3)),
                        () -> assertTrue(PRICES.get(3L).isApplicable(date3)),
                        () -> assertFalse(PRICES.get(4L).isApplicable(date3))
                ),
                () -> assertAll(
                        () -> assertTrue(PRICES.get(1L).isApplicable(date4)),
                        () -> assertFalse(PRICES.get(2L).isApplicable(date4)),
                        () -> assertFalse(PRICES.get(3L).isApplicable(date4)),
                        () -> assertTrue(PRICES.get(4L).isApplicable(date4))
                ),
                () -> assertAll(
                        () -> assertTrue(PRICES.get(1L).isApplicable(date5)),
                        () -> assertFalse(PRICES.get(2L).isApplicable(date5)),
                        () -> assertFalse(PRICES.get(3L).isApplicable(date5)),
                        () -> assertTrue(PRICES.get(4L).isApplicable(date5))
                ),
                () -> assertAll(
                        () -> assertTrue(PRICES.get(1L).isApplicable(date6)),
                        () -> assertFalse(PRICES.get(2L).isApplicable(date6)),
                        () -> assertFalse(PRICES.get(3L).isApplicable(date6)),
                        () -> assertTrue(PRICES.get(4L).isApplicable(date6))
                ),
                () -> {
                    Boolean visibles = PRICES.values().stream()
                            .map(price -> price.isApplicable(date7))
                            .reduce(false, Boolean::logicalOr);
                    assertFalse(visibles);
                }
        );
    }

    @Test
    void testCompareTo() {
        assertAll(
                () -> assertEquals(-1, PRICES.get(1L).compareTo(PRICES.get(2L))),
                () -> assertEquals(1, PRICES.get(2L).compareTo(PRICES.get(1L))),
                () -> assertEquals(0, PRICES.get(2L).compareTo(PRICES.get(3L))),
                () -> assertEquals(0, PRICES.get(2L).compareTo(PRICES.get(4L))),
                () -> assertEquals(0, PRICES.get(3L).compareTo(PRICES.get(4L)))
        );
    }

    @Test
    void testConstructor() {
        Price a = new Price(
                1L,
                BRAND,
                PRODUCT,
                getDate(2020, Calendar.JUNE, 14, 0, 0, 0),
                getDate(2020, Calendar.DECEMBER, 31, 23, 59, 59),
                0,
                new BigDecimal("35.50").setScale(2, RoundingMode.CEILING),
                "EUR");
        Price b = new Price();
        b.setBrand(BRAND);
        b.setStartDate(getDate(2020, Calendar.JUNE, 14, 0, 0, 0));
        b.setEndDate(getDate(2020, Calendar.DECEMBER, 31, 23, 59, 59));
        b.setPriceList(1L);
        b.setProduct(PRODUCT);
        b.setPriority(0);
        b.setPrice(new BigDecimal("35.50").setScale(2, RoundingMode.CEILING));
        b.setCurr("EUR");

        assertAll(
                () -> assertEquals(a, b),
                () -> assertEquals(b, a)
        );
    }
}