package com.amsoft.shopping.core.builder;

import com.amsoft.shopping.core.dto.ProductDto;

/**
 * Builder class for creating instances of {@link ProductDto}.
 *
 * @author Alex Pumnea
 */
public abstract class ProductDtoBuilder<T extends ProductDtoBuilder<T>> {
    protected String name;
    protected double price;

    public T setName(String name) {
        this.name = name;
        return self();
    }

    public T setPrice(double price) {
        this.price = price;
        return self();
    }

    protected abstract T self();
}
