package org.inditex.prices.api.data;

import org.iditex.prices.model.entities.Brand;
import org.inditex.prices.api.persistence.entities.BrandDto;

public class BrandData {

    public static final Brand BRAND = new Brand(1L, "ZARA");

    public static final BrandDto BRAND_DTO = new BrandDto(1L, "ZARA");

}
