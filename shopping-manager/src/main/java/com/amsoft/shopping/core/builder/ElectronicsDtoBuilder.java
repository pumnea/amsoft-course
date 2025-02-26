package com.amsoft.shopping.core.builder;

import com.amsoft.shopping.core.dto.ElectronicsDto;

/**
 * Builder class for creating instances of {@link ElectronicsDto}.
 *
 * @author Alex Pumnea
 */
public class ElectronicsDtoBuilder extends ProductDtoBuilder<ElectronicsDtoBuilder> {
    private String brand;
    private String model;

    public ElectronicsDtoBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ElectronicsDtoBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public ElectronicsDto build() {
        return new ElectronicsDto(name, price, brand, model);
    }

    @Override
    protected ElectronicsDtoBuilder self() {
        return this;
    }
}
