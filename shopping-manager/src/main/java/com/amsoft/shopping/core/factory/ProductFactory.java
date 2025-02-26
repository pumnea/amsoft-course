package com.amsoft.shopping.core.factory;

import com.amsoft.shopping.core.model.Product;

/**
 * Interface representing a factory for creating products.
 *
 * @param <T> the type of product to create
 * @author Alex Pumnea
 */
public interface ProductFactory<T extends Product> {
    T createProduct(String name, double price, double quantity);
}
