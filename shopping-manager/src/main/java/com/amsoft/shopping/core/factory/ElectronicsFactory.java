package com.amsoft.shopping.core.factory;

import com.amsoft.shopping.core.builder.ElectronicsBuilder;
import com.amsoft.shopping.core.model.Electronics;

/**
 * Factory class for creating Electronics objects.
 * Implements the ProductFactory interface with Electronics as the type parameter.
 * Provides methods to create Electronics objects with different parameters.
 *
 * @author Alex Pumnea
 */
public class ElectronicsFactory implements ProductFactory<Electronics> {
    @Override
    public Electronics createProduct(String name, double price, double quantity) {
        return new ElectronicsBuilder()
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .build();
    }

    public Electronics createElectronics(String name, double price, double quantity, String brand, String model) {
        return new ElectronicsBuilder()
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .setBrand(brand)
                .setModel(model)
                .build();
    }
}
