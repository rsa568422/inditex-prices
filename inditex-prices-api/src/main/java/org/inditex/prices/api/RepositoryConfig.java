package org.inditex.prices.api;

import org.iditex.prices.model.repositories.PriceRepository;
import org.inditex.prices.api.persistence.repositories.PriceH2Repository;
import org.inditex.prices.api.persistence.repositories.PriceH2RepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RepositoryConfig {

    private final PriceH2Repository repository;

    @Autowired
    public RepositoryConfig(PriceH2Repository repository) {
        this.repository = repository;
    }

    @Bean
    @Primary
    public PriceRepository getH2Repository() {
        return new PriceH2RepositoryAdapter(repository);
    }

}
