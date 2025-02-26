package com.amsoft.shopping.core.model;

import lombok.*;

/**
 * Represents the base class of the product.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public abstract class Product {
    private String name;
    private double price;
    private double quantity;
}
