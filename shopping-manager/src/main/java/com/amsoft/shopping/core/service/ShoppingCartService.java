package com.amsoft.shopping.core.service;

/**
 * Interface for a shopping cart service.
 *
 * @param <T> the type of items in the shopping cart
 * @author Alex Pumnea
 */
public interface ShoppingCartService<T> {
    void addToCart(T item, double quantity);

    void removeFromCart(T item, double quantity);

    double totalPrice();

    Iterable<T> getCartItems();

    void displayCartItems();

    double getTotalAmountOfItems();
}
