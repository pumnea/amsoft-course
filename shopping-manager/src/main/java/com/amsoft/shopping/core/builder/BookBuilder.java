package com.amsoft.shopping.core.builder;

import com.amsoft.shopping.core.model.Book;

/**
 * Builder class for creating instances of {@link Book}.
 *
 * @author Alex Pumnea
 */


public class BookBuilder extends ProductBuilder<BookBuilder> {
    private String author;
    private String category;

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public Book build() {
        return new Book(name, price, quantity, author, category);
    }

    @Override
    protected BookBuilder self() {
        return this;
    }
}