package org.inditex.prices.api.data;

import org.iditex.prices.model.entities.Product;
import org.inditex.prices.api.persistence.entities.ProductDto;

public class ProductData {

    public static final Product PRODUCT = new Product(35455L, "Product A");

    public static final ProductDto PRODUCT_DTO = new ProductDto(35455L, "Product A");

}
