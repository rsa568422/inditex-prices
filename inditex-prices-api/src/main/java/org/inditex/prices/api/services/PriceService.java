package org.inditex.prices.api.services;

import org.iditex.prices.model.entities.Price;

import java.util.Date;
import java.util.Optional;

public interface PriceService {

    Optional<Price> findApplicablePrice(Long brandId, Long productId, Date date);
}
