package org.inditex.prices.api.persistence.entities;

import lombok.*;
import org.iditex.prices.model.entities.Brand;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class BrandDto {

    @Id
    private Long id;

    private String name;

    public Brand toModel() {
        return new Brand(id, name);
    }

}
