package org.inditex.prices.api.persistence.repositories;

import org.inditex.prices.api.persistence.entities.PriceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PriceH2Repository extends JpaRepository<PriceDto, Long> {

    @Query("select p from PriceDto p where p.brand.id = ?1 and p.product.id = ?2 and ?3 between p.startDate and p.endDate")
    List<PriceDto> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, Date date);

}
