package com.amsoft.shopping.infrastructure.service;

import com.amsoft.shopping.core.dto.ProductDto;
import com.amsoft.shopping.core.repository.ProductRepository;
import com.amsoft.shopping.core.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;


/**
 * Implementation of the ShoppingCartService interface.
 *
 * @author Alex Pumnea
 */
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService<ProductDto> {
    private final ProductRepository<ProductDto> productRepository;
    private final Map<ProductDto, Double> cartItems;

    public ShoppingCartServiceImpl(ProductRepository<ProductDto> productRepository) {
        this.productRepository = productRepository;
        this.cartItems = new HashMap<>();
    }

    @Override
    public void addToCart(ProductDto item, double quantity) {
        productRepository.getQuantity(item)
                .filter(qty -> qty >= quantity)
                .ifPresentOrElse(
                        qty -> {
                            cartItems.put(item, quantity);
                            log.info("Item added successfully: {}", item);
                            productRepository.updateQuantity(item, qty - quantity);
                        },
                        () -> log.error("Not enough items. Only: {} available", productRepository.getQuantity(item))
                );
    }

    @Override
    public void removeFromCart(ProductDto item, double quantity) {
        cartItems.computeIfPresent(item, (key, value) -> value - quantity <= 0 ? null : value - quantity);
        log.info("Item removed successfully: {}", item.toString());
    }

    @Override
    public double totalPrice() {
        return cartItems.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    @Override
    public Iterable<ProductDto> getCartItems() {
        return new ArrayList<>(cartItems.keySet());
    }

    @Override
    public double getTotalAmountOfItems() {
        return cartItems.values().stream()
                .mapToDouble(aDouble -> aDouble)
                .sum();
    }

    @Override
    public void displayCartItems() {
        List<String> formattedItems = StreamSupport.stream(getCartItems().spliterator(), false)
                .map(ProductDto::toString)
                .toList();

        String displayCartItems = "\nShopping Cart Items:\n" +
                String.join("\n", formattedItems) +
                "\nTotal amount of items: " + getTotalAmountOfItems() +
                "\nTotal price of items: " + totalPrice() + "\n";
        log.info(displayCartItems);
    }
}
