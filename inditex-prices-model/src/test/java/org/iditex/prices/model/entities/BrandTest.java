package org.iditex.prices.model.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BrandTest {

    @Test
    void testConstructor() {
        Brand a = new Brand(1L, "ZARA");
        Brand b = new Brand();
        b.setId(1L);
        b.setName("ZARA");

        assertAll(
                () -> assertEquals(a, b),
                () -> assertEquals(b, a)
        );
    }

}