package org.inditex.prices.api.persistence.entities;

import lombok.*;
import org.iditex.prices.model.entities.Price;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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
        return new Price(priceList, brand.toModel(), product.toModel(), startDate, endDate, priority, price, curr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDto dto = (PriceDto) o;
        return Objects.equals(priceList, dto.priceList) && Objects.equals(brand, dto.brand) && Objects.equals(product, dto.product) && Objects.equals(startDate, dto.startDate) && Objects.equals(endDate, dto.endDate) && Objects.equals(priority, dto.priority) && Objects.equals(price, dto.price) && Objects.equals(curr, dto.curr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceList, brand, product, startDate, endDate, priority, price, curr);
    }
}
