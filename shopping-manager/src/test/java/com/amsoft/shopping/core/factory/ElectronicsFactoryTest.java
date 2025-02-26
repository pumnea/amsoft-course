package com.amsoft.shopping.core.factory;

import com.amsoft.shopping.core.model.Electronics;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Pumnea
 */
class ElectronicsFactoryTest {
    @Test
    void createProduct_ShouldCreateElectronicsWithGivenAttributes() {
        ElectronicsFactory electronicsFactory = new ElectronicsFactory();
        String name = "iPhone";
        double price = 210.99;
        double quantity = 5.0;

        Electronics electronics = electronicsFactory.createProduct(name, price, quantity);

        assertThat(electronics).isNotNull();
        assertThat(electronics.getName()).isEqualTo(name);
        assertThat(electronics.getPrice()).isEqualTo(price);
        assertThat(electronics.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void createElectronics_ShouldCreateElectronicsWithAllAttributes() {
        ElectronicsFactory electronicsFactory = new ElectronicsFactory();
        String name = "iPhone";
        double price = 210.99;
        double quantity = 5.0;
        String brand = "Apple";
        String model = "XR";

        Electronics electronics = electronicsFactory.createElectronics(name, price, quantity, brand, model);

        assertThat(electronics).isNotNull();
        assertThat(electronics.getName()).isEqualTo(name);
        assertThat(electronics.getPrice()).isEqualTo(price);
        assertThat(electronics.getQuantity()).isEqualTo(quantity);
        assertThat(electronics.getBrand()).isEqualTo(brand);
        assertThat(electronics.getModel()).isEqualTo(model);
    }
}