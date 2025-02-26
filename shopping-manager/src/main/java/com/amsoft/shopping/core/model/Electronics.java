package com.amsoft.shopping.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an electronics product.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Electronics extends Product {
    private final String brand;
    private final String model;

    public Electronics(String productName, double productPrice, double productQuantity, String brand, String model) {
        super(productName, productPrice, productQuantity);
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Electronics{" +
                "productName='" + getName() + '\'' +
                ", productPrice=" + getPrice() +
                ", productQuantity=" + getQuantity() +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
