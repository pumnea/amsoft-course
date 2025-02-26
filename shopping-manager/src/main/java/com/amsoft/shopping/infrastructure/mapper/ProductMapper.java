package com.amsoft.shopping.infrastructure.mapper;

import com.amsoft.shopping.core.builder.BookDtoBuilder;
import com.amsoft.shopping.core.builder.ElectronicsDtoBuilder;
import com.amsoft.shopping.core.dto.BookDto;
import com.amsoft.shopping.core.dto.ElectronicsDto;
import com.amsoft.shopping.core.model.Book;
import com.amsoft.shopping.core.model.Electronics;

/**
 * Mapper class to map product entities to DTOs.
 *
 * @author Alex Pumnea
 */
public class ProductMapper {
    public BookDto getBookDto(Book item) {
        return new BookDtoBuilder()
                .setName(item.getName())
                .setPrice(item.getPrice())
                .setAuthor(item.getAuthor())
                .setCategory(item.getCategory())
                .build();
    }

    public ElectronicsDto getElectronicsDto(Electronics item) {
        return new ElectronicsDtoBuilder()
                .setName(item.getName())
                .setPrice(item.getPrice())
                .setBrand(item.getBrand())
                .setModel(item.getModel())
                .build();
    }
}
