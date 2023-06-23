package org.iditex.prices.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price implements Comparable<Price> {

    private Long priceList;

    private Brand brand;

    private Product product;

    private Date startDate;

    private Date endDate;

    private Integer priority;

    private BigDecimal price;

    private String curr;

    public boolean isApplicable(Date date) {
        return false;
    }

    @Override
    public int compareTo(Price o) {
        return 0;
    }
}
