package com.amsoft.shopping.core.builder;

import com.amsoft.shopping.core.dto.BookDto;

/**
 * Builder class for creating instances of {@link BookDto}.
 *
 * @author Alex Pumnea
 */
public class BookDtoBuilder extends ProductDtoBuilder<BookDtoBuilder> {
    private String author;
    private String category;

    public BookDtoBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookDtoBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public BookDto build() {
        return new BookDto(name, price, author, category);
    }

    @Override
    protected BookDtoBuilder self() {
        return this;
    }
}
