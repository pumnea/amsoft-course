package com.amsoft.shopping.application;

import com.amsoft.shopping.core.dto.BookDto;
import com.amsoft.shopping.core.dto.ElectronicsDto;
import com.amsoft.shopping.core.dto.ProductDto;
import com.amsoft.shopping.core.factory.BookFactory;
import com.amsoft.shopping.core.factory.ElectronicsFactory;
import com.amsoft.shopping.core.model.Book;
import com.amsoft.shopping.core.model.Electronics;
import com.amsoft.shopping.core.repository.ProductRepository;
import com.amsoft.shopping.infrastructure.mapper.ProductMapper;
import com.amsoft.shopping.infrastructure.repository.ProductRepositoryImpl;
import com.amsoft.shopping.infrastructure.service.ShoppingCartServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Alex Pumnea
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        ProductRepository<ProductDto> productRepository = new ProductRepositoryImpl();
        ShoppingCartServiceImpl shoppingCart = new ShoppingCartServiceImpl(productRepository);
        ProductMapper mapper = new ProductMapper();

        runBookExample(productRepository, shoppingCart, mapper);
        runElectronicsExample(productRepository, shoppingCart, mapper);
    }

    private static void runBookExample(ProductRepository<ProductDto> productRepository, ShoppingCartServiceImpl shoppingCart, ProductMapper mapper) {
        BookFactory bookFactory = new BookFactory();

        Book javaInAction = bookFactory.createBook("Java in Action", 16.90, 20, "Joshua Block", "IT");
        Book cruisingAlongWithJava = bookFactory.createBook("Cruising along with Java", 25.90, 203, "Venkat Subramaniam", "IT");

        BookDto javaInActionDto = mapper.getBookDto(javaInAction);
        BookDto cruisingAlongWithJavaDto = mapper.getBookDto(cruisingAlongWithJava);

        productRepository.save(javaInActionDto, javaInAction.getQuantity());
        productRepository.save(cruisingAlongWithJavaDto, cruisingAlongWithJava.getQuantity());

        shoppingCart.addToCart(javaInActionDto, 1);
        shoppingCart.addToCart(cruisingAlongWithJavaDto, 100);

        shoppingCart.displayCartItems();
    }

    private static void runElectronicsExample(ProductRepository<ProductDto> productRepository, ShoppingCartServiceImpl shoppingCart, ProductMapper mapper) {
        ElectronicsFactory electronicsFactory = new ElectronicsFactory();

        Electronics electronics = electronicsFactory.createElectronics("Phone", 300, 5, "Iphone", "13");

        ElectronicsDto electronicsDto = mapper.getElectronicsDto(electronics);

        productRepository.save(electronicsDto, electronics.getQuantity());

        shoppingCart.addToCart(electronicsDto, 5);

        shoppingCart.displayCartItems();

        shoppingCart.removeFromCart(electronicsDto, 3);

        shoppingCart.displayCartItems();
    }
}
