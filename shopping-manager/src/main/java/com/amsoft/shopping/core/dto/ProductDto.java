package com.amsoft.shopping.core.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) for representing a product.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public abstract class ProductDto {
    private String name;
    private double price;
}
