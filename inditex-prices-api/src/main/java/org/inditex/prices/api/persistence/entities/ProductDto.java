package org.inditex.prices.api.persistence.entities;

import lombok.*;
import org.iditex.prices.model.entities.Product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class ProductDto {

    @Id
    private Long id;

    private String name;

    public Product toModel() {
        return null;
    }

}
