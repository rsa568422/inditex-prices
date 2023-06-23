package org.iditex.prices.model.repositories;

import org.iditex.prices.model.entities.Price;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PriceRepository {

    List<Price> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, Date date);

    default Optional<Price> findApplicablePrice(Long brandId, Long productId, Date date) {
        return filterMaxPriority(findByBrandIdAndProductIdAndDate(brandId, productId, date));
    }

    private static Optional<Price> filterMaxPriority(List<Price> prices) {
        return prices.stream().max(Price::compareTo);
    }

}
