package com.amsoft.shopping.core.builder;

import com.amsoft.shopping.core.model.Electronics;

/**
 * Builder class for creating instances of {@link Electronics}.
 *
 * @author Alex Pumnea
 */
public class ElectronicsBuilder extends ProductBuilder<ElectronicsBuilder> {
    private String brand;
    private String model;

    public ElectronicsBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ElectronicsBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public Electronics build() {
        return new Electronics(name, price, quantity, brand, model);
    }

    @Override
    protected ElectronicsBuilder self() {
        return this;
    }
}