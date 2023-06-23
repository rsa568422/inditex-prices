package org.iditex.prices.model.data;

import org.iditex.prices.model.entities.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.iditex.prices.model.data.BrandData.BRAND;
import static org.iditex.prices.model.data.ProductData.PRODUCT;

public class PriceData {

    private static final Calendar CALENDAR = Calendar.getInstance();

    public static final Map<Long, Price> PRICES = Stream.of(
            new Price(
                    1L,
                    BRAND,
                    PRODUCT,
                    getDate(2020, Calendar.JUNE, 14, 0, 0, 0),
                    getDate(2020, Calendar.DECEMBER, 31, 23, 59, 59),
                    0,
                    new BigDecimal("35.50").setScale(2, RoundingMode.CEILING),
                    "EUR"
            ),
            new Price(
                    2L,
                    BRAND,
                    PRODUCT,
                    getDate(2020, Calendar.JUNE, 14, 15, 0, 0),
                    getDate(2020, Calendar.JUNE, 14, 18, 30, 0),
                    1,
                    new BigDecimal("25.45").setScale(2, RoundingMode.CEILING),
                    "EUR"
            ),
            new Price(
                    3L,
                    BRAND,
                    PRODUCT,
                    getDate(2020, Calendar.JUNE, 15, 0, 0, 0),
                    getDate(2020, Calendar.JUNE, 15, 11, 0, 0),
                    1,
                    new BigDecimal("30.50").setScale(2, RoundingMode.CEILING),
                    "EUR"
            ),
            new Price(
                    4L,
                    BRAND,
                    PRODUCT,
                    getDate(2020, Calendar.JUNE, 15, 16, 0, 0),
                    getDate(2020, Calendar.DECEMBER, 31, 23, 59, 59),
                    1,
                    new BigDecimal("38.95").setScale(2, RoundingMode.CEILING),
                    "EUR"
            )
    ).collect(Collectors.groupingBy(Price::getPriceList, Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0))));


    public static Date getDate(int year, int month, int day, int hour, int min, int sec) {
        CALENDAR.set(year, month, day, hour, min, sec);
        return CALENDAR.getTime();
    }
}
