package com.amsoft.shopping.infrastructure.repository;

import com.amsoft.shopping.core.dto.ProductDto;
import com.amsoft.shopping.core.repository.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@link ProductRepository} interface that stores products in memory using a map.
 *
 * @author Alex Pumnea
 */
public class ProductRepositoryImpl implements ProductRepository<ProductDto> {
    private final Map<ProductDto, Double> products;

    public ProductRepositoryImpl() {
        this.products = new HashMap<>();
    }

    @Override
    public void save(ProductDto itemDto, double quantity) {
        products.put(itemDto, quantity);
    }

    @Override
    public void delete(ProductDto itemDto) {
        products.remove(itemDto);
    }

    @Override
    public Iterable<ProductDto> getAll() {
        return new ArrayList<>(products.keySet());
    }

    @Override
    public Optional<Double> getQuantity(ProductDto itemDto) {
        return Optional.ofNullable(products.get(itemDto));
    }

    @Override
    public void updateQuantity(ProductDto itemDto, double quantity) {
        products.put(itemDto, quantity);
    }
}
