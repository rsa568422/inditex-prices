package org.inditex.prices.api.persistence.repositories;

import org.iditex.prices.model.entities.Price;
import org.iditex.prices.model.repositories.PriceRepository;
import org.inditex.prices.api.persistence.entities.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceH2RepositoryAdapter implements PriceRepository {

    private final PriceH2Repository repository;

    @Autowired
    public PriceH2RepositoryAdapter(PriceH2Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<Price> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, Date date) {
        return repository.findByBrandIdAndProductIdAndDate(brandId, productId, date)
                .stream()
                .map(PriceDto::toModel)
                .collect(Collectors.toList());
    }
}
