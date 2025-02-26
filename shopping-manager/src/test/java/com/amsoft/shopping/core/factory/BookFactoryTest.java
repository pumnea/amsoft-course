package com.amsoft.shopping.core.factory;

import com.amsoft.shopping.core.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Pumnea
 */
@Slf4j
class BookFactoryTest {
    @Test
    void createProduct_ShouldCreateBookWithGivenAttributes() {
        BookFactory bookFactory = new BookFactory();
        String name = "Java in Action";
        double price = 10.99;
        double quantity = 2.0;

        Book book = bookFactory.createProduct(name, price, quantity);

        assertThat(book).isNotNull();
        assertThat(book.getName()).isEqualTo(name);
        assertThat(book.getPrice()).isEqualTo(price);
        assertThat(book.getQuantity()).isEqualTo(quantity);
    }

    @Test
    void createBook_ShouldCreateBookWithAllAttributes() {
        BookFactory bookFactory = new BookFactory();
        String name = "Java in Action";
        double price = 10.99;
        double quantity = 2.0;
        String author = "Joshua Block";
        String category = "IT";

        Book book = bookFactory.createBook(name, price, quantity, author, category);

        assertThat(book).isNotNull();
        assertThat(book.getName()).isEqualTo(name);
        assertThat(book.getPrice()).isEqualTo(price);
        assertThat(book.getQuantity()).isEqualTo(quantity);
        assertThat(book.getAuthor()).isEqualTo(author);
        assertThat(book.getCategory()).isEqualTo(category);
    }
}