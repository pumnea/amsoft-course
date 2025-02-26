package com.amsoft.shopping.core.builder;

/**
 * Abstract builder class for creating products.
 *
 * @param <T> The concrete type of the builder.
 * @author Alex Pumnea
 */
public abstract class ProductBuilder<T extends ProductBuilder<T>> {
    protected String name;
    protected double price;
    protected double quantity;

    public T setName(String name) {
        this.name = name;
        return self();
    }

    public T setPrice(double price) {
        this.price = price;
        return self();
    }

    public T setQuantity(double quantity) {
        this.quantity = quantity;
        return self();
    }

    protected abstract T self();
}