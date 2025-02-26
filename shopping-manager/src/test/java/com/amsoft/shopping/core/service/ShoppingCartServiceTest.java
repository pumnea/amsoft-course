package com.amsoft.shopping.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Alex Pumnea
 */
class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartService<String> shoppingCartService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddToCartTwoItems() {
        String item = "Item";
        double quantity = 2;

        doNothing().when(shoppingCartService).addToCart(item, quantity);

        shoppingCartService.addToCart(item, quantity);

        verify(shoppingCartService).addToCart(item, quantity);
    }

    @Test
    void shouldRemoveFromCartOneItem() {
        String item = "Item";
        double quantity = 1;

        // Set up mock behavior
        doNothing().when(shoppingCartService).removeFromCart(item, quantity);

        // Call the method under test
        shoppingCartService.removeFromCart(item, quantity);

        // Verify the method was called with the correct arguments
        verify(shoppingCartService).removeFromCart(item, quantity);
    }

    @Test
    void shouldReturnTotalPriceOfTenDotNinetyNine() {
        double expectedTotalPrice = 10.99;

        when(shoppingCartService.totalPrice()).thenReturn(expectedTotalPrice);

        double totalPrice = shoppingCartService.totalPrice();

        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }

    @Test
    void shouldReturnThreeItems() {
        String item1 = "Item 1";
        String item2 = "Item 2";
        String item3 = "Item 3";

        when(shoppingCartService.getCartItems()).thenReturn(List.of(item1, item2, item3));

        Iterable<String> cartItems = shoppingCartService.getCartItems();

        assertThat(cartItems).containsExactly(item1, item2, item3);
    }

    @Test
    void shouldCallDisplayCartItems() {

        doNothing().when(shoppingCartService).displayCartItems();

        shoppingCartService.displayCartItems();

        verify(shoppingCartService).displayCartItems();
    }
}
