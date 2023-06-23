package org.iditex.prices.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testConstructor() {
        Product a = new Product(35455L, "Product A");
        Product b = new Product();
        b.setId(35455L);
        b.setName("Product A");

        assertAll(
                () -> assertEquals(a, b),
                () -> assertEquals(b, a)
        );
    }

}