package com.amsoft.shopping.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a book product.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Book extends Product {
    private final String author;
    private final String category;

    public Book(String name, double price, double quantity, String author, String category) {
        super(name, price, quantity);
        this.author = author;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "productName='" + getName() + '\'' +
                ", productPrice=" + getPrice() +
                ", productQuantity=" + getQuantity() +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
