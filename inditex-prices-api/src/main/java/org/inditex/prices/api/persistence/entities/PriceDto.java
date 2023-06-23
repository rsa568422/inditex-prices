package org.inditex.prices.api.persistence.entities;

import lombok.*;
import org.iditex.prices.model.entities.Price;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prices")
public class PriceDto {

    @Id
    @Column(name = "price_list")
    private Long priceList;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandDto brand;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductDto product;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private Integer priority;

    private BigDecimal price;

    private String curr;

    public Price toModel() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
