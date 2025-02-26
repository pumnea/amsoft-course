package com.amsoft.shopping.core.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a data transfer object (DTO) for a book.
 * Extends the {@link ProductDto} class.
 * Contains additional attributes specific to books.
 * Provides getters and setters for accessing the attributes.
 * Overrides the toString() method to provide a string representation of the book.
 * Implements equals() and hashCode() methods based on the attributes.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookDto extends ProductDto {
    private final String author;
    private final String category;

    public BookDto(String name, double price, String author, String category) {
        super(name, price);
        this.author = author;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book - " +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", author='" + author + '\'' +
                ", category='" + category + '\'';
    }
}
