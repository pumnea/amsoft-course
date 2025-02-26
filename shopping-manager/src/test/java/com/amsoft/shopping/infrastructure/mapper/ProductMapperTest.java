package com.amsoft.shopping.infrastructure.mapper;

import com.amsoft.shopping.core.dto.BookDto;
import com.amsoft.shopping.core.dto.ElectronicsDto;
import com.amsoft.shopping.core.model.Book;
import com.amsoft.shopping.core.model.Electronics;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Pumnea
 */
class ProductMapperTest {
    private final ProductMapper productMapper = new ProductMapper();

    @Test
    void shouldMapBookToBookDto() {

        Book book = new Book("Java in Action", 10.0, 50, "Joshua Block", "IT");

        BookDto bookDto = productMapper.getBookDto(book);

        assertThat(bookDto.getName()).isEqualTo("Java in Action");
        assertThat(bookDto.getPrice()).isEqualTo(10.0);
        assertThat(bookDto.getAuthor()).isEqualTo("Joshua Block");
        assertThat(bookDto.getCategory()).isEqualTo("IT");
    }

    @Test
    void shouldMapElectronicsToElectronicsDto() {

        Electronics electronics = new Electronics("Phone", 20.0, 100, "Apple", "XR");

        ElectronicsDto electronicsDto = productMapper.getElectronicsDto(electronics);

        assertThat(electronicsDto.getName()).isEqualTo("Phone");
        assertThat(electronicsDto.getPrice()).isEqualTo(20.0);
        assertThat(electronicsDto.getBrand()).isEqualTo("Apple");
        assertThat(electronicsDto.getModel()).isEqualTo("XR");
    }

}