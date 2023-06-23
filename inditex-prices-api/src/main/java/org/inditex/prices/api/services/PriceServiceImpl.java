package org.inditex.prices.api.services;

import org.iditex.prices.model.entities.Price;
import org.iditex.prices.model.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository repository;

    @Autowired
    public PriceServiceImpl(PriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Price> findApplicablePrice(Long brandId, Long productId, Date date) {
        return repository.findApplicablePrice(brandId, productId, date);
    }

}
