package org.inditex.prices.api.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.iditex.prices.model.entities.Price;
import org.inditex.prices.api.utils.Parser;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PriceResponse {

    private Long brandId;

    private Long productId;

    private BigDecimal price;

    private String startDate;

    private String endDate;

    public static PriceResponse fromPrice(@NonNull Price price) {
        return new PriceResponse(
                price.getBrand().getId(),
                price.getProduct().getId(),
                price.getPrice(),
                Parser.toString(price.getStartDate()),
                Parser.toString(price.getEndDate())
        );
    }

}
