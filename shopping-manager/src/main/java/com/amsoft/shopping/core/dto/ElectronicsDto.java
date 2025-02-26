package com.amsoft.shopping.core.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a data transfer object (DTO) for electronics.
 * Extends the {@link ProductDto} class.
 * Contains additional attributes specific to electronics.
 * Provides getters and setters for accessing the attributes.
 * Overrides the toString() method to provide a string representation of electronics.
 * Implements equals() and hashCode() methods based on the attributes.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ElectronicsDto extends ProductDto {
    private final String brand;
    private final String model;

    public ElectronicsDto(String productName, double productPrice, String brand, String model) {
        super(productName, productPrice);
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Electronics - " +
                "name ='" + getName() + '\'' +
                ", price=" + getPrice() + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'';
    }
}
