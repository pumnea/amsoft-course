package com.amsoft.shopping.core.factory;

import com.amsoft.shopping.core.builder.BookBuilder;
import com.amsoft.shopping.core.model.Book;

/**
 * Factory class for creating Book objects.
 * Implements the ProductFactory interface with Book as the type parameter.
 * Provides methods to create Book objects with different parameters.
 *
 * @author Alex Pumnea
 */
public class BookFactory implements ProductFactory<Book> {
    @Override
    public Book createProduct(String name, double price, double quantity) {
        return new BookBuilder()
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .build();
    }

    public Book createBook(String name, double price, double quantity, String author, String category) {
        return new BookBuilder()
                .setName(name)
                .setPrice(price)
                .setQuantity(quantity)
                .setAuthor(author)
                .setCategory(category)
                .build();
    }
}

