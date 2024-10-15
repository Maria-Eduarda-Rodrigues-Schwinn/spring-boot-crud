package com.example.crud.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity(name = "product")
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price_in_cents;

    private boolean active;

    public Product(ProductRequest productRequest) {
        this.name = productRequest.name();
        this.price_in_cents = productRequest.price_in_cents();
        this.active = true;
    }
}
