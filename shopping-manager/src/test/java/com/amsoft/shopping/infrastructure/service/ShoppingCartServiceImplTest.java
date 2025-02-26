package com.amsoft.shopping.infrastructure.service;

import com.amsoft.shopping.core.dto.BookDto;
import com.amsoft.shopping.core.dto.ElectronicsDto;
import com.amsoft.shopping.core.dto.ProductDto;
import com.amsoft.shopping.core.factory.BookFactory;
import com.amsoft.shopping.core.factory.ElectronicsFactory;
import com.amsoft.shopping.core.model.Book;
import com.amsoft.shopping.core.model.Electronics;
import com.amsoft.shopping.core.repository.ProductRepository;
import com.amsoft.shopping.core.service.ShoppingCartService;
import com.amsoft.shopping.infrastructure.mapper.ProductMapper;
import com.amsoft.shopping.infrastructure.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Pumnea
 */
class ShoppingCartServiceImplTest {
    private ProductRepository<ProductDto> productRepository;

    private ShoppingCartService<ProductDto> shoppingCart;

    private ProductMapper mapper;
    private BookFactory bookFactory;
    private ElectronicsFactory electronicsFactory;


    @BeforeEach
    public void setup() {
        productRepository = new ProductRepositoryImpl();
        shoppingCart = new ShoppingCartServiceImpl(productRepository);
        mapper = new ProductMapper();
        bookFactory = new BookFactory();
        electronicsFactory = new ElectronicsFactory();
    }

    @Test
    void shouldAddOneProductToCart() {
        Book book = bookFactory.createBook("Java in Action", 20, 10, "Joshua Block", "IT");
        BookDto bookDto = mapper.getBookDto(book);
        productRepository.save(bookDto, book.getQuantity());
        shoppingCart.addToCart(bookDto, 2);
        assertThat(shoppingCart.getCartItems()).contains(bookDto);
        assertThat(shoppingCart.totalPrice()).isEqualTo(40);
        assertThat(productRepository.getQuantity(bookDto)).contains(8.0);
    }

    @Test
    void shouldAddToCartIfEnoughItemsAreAvailable() {

        Book book = bookFactory.createBook("Java in Action", 20, 10, "Joshua Block", "IT");
        BookDto bookDto = mapper.getBookDto(book);
        productRepository.save(bookDto, book.getQuantity());
        double quantity = 2.0;
        double availableQuantity = 10.0;

        assertThat(productRepository.getQuantity(bookDto)).isEqualTo(Optional.of(availableQuantity));

        shoppingCart.addToCart(bookDto, quantity);

        assertThat(shoppingCart.getCartItems()).contains(bookDto);
        productRepository.updateQuantity(bookDto, availableQuantity - quantity);
        assertThat(productRepository.getQuantity(bookDto)).isEqualTo(Optional.of(8.0));
    }

    @Test
    void shouldNotAddToCartIfNotEnoughItemsAreAvailable() {
        // Given
        Book book = bookFactory.createBook("Java in Action", 3, 5, "Joshua Block", "IT");
        BookDto bookDto = mapper.getBookDto(book);
        double availableQuantity = 3.0;
        double quantity = 5.0;
        productRepository.save(bookDto, availableQuantity);
        shoppingCart.addToCart(bookDto, quantity);

        assertThat(shoppingCart.getCartItems()).doesNotContain(bookDto);
    }

    @Test
    void shouldRemoveFromCartOneItem() {

        Book book = bookFactory.createBook("Java in Action", 20, 2, "Joshua Block", "IT");
        BookDto bookDto = mapper.getBookDto(book);
        double quantity = 2.0;
        shoppingCart.addToCart(bookDto, quantity);

        shoppingCart.removeFromCart(bookDto, quantity);

        assertThat(shoppingCart.getCartItems()).doesNotContain(bookDto);
    }

    @Test
    void shouldReturnZeroIfCartIsEmpty() {
        double totalPrice = shoppingCart.totalPrice();

        assertThat(totalPrice).isZero();
    }

    @Test
    void shouldCalculateTotalPriceOfFiveItemsInCart() {
        // Given
        Book book = bookFactory.createBook("Java in Action", 20, 10, "Joshua Block", "IT");
        BookDto bookDto = mapper.getBookDto(book);
        double quantity1 = 2.0;
        productRepository.save(bookDto, quantity1);
        Electronics electronics = electronicsFactory.createElectronics("Phone", 1000, 300, "Samsung", "Galaxy");
        ElectronicsDto electronicsDto = mapper.getElectronicsDto(electronics);
        double quantity2 = 3.0;
        productRepository.save(electronicsDto, quantity2);
        shoppingCart.addToCart(bookDto, quantity1);
        shoppingCart.addToCart(electronicsDto, quantity2);

        double totalPrice = shoppingCart.totalPrice();

        double expectedTotalPrice = (bookDto.getPrice() * quantity1) + (electronicsDto.getPrice() * quantity2);
        assertThat(totalPrice).isEqualTo(expectedTotalPrice);
    }
}
