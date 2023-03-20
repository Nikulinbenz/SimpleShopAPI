package com.test.productservice.entity;

import com.test.organisationservice.entity.Organization;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToMany(mappedBy = "products")
    private Set<Discount> discounts;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;

    @Column(name = "keywords")
    private String keywords;

    @OneToMany(mappedBy = "product")
    private Set<ProductCharacteristic> characteristics;

    @OneToMany(mappedBy = "product")
    private Set<Rating> ratings;

    }
}
