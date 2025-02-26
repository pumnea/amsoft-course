package com.amsoft.shopping.core.repository;

import com.amsoft.shopping.core.dto.ProductDto;

import java.util.Optional;


/**
 * The interface for a product repository.
 *
 * @param <T> the type of the product DTO
 * @author Alex Pumnea
 */
public interface ProductRepository<T extends ProductDto> {
    void save(T itemDto, double quantity);

    void delete(T item);

    Iterable<T> getAll();

    Optional<Double> getQuantity(T item);

    void updateQuantity(T item, double quantity);

}
