package com.amsoft.shopping.infrastructure.repository;

import com.amsoft.shopping.core.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author Alex Pumnea
 */
class ProductRepositoryImplTest {
    private ProductRepositoryImpl productRepository;
    @Mock
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepositoryImpl();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddProductWithQuantityTen() {
        double quantity = 10.0;

        productRepository.save(productDto, quantity);

        Optional<Double> savedQuantity = productRepository.getQuantity(productDto);
        assertThat(savedQuantity).isPresent().contains(quantity);
    }

    @Test
    void shouldRemoveTenProducts() {

        productRepository.save(productDto, 10.0);

        productRepository.delete(productDto);

        Optional<Double> quantity = productRepository.getQuantity(productDto);
        assertThat(quantity).isEmpty();
    }

    @Test
    void shouldReturnAllProducts() {

        ProductDto product1 = mock(ProductDto.class);
        ProductDto product2 = mock(ProductDto.class);
        productRepository.save(product1, 5.0);
        productRepository.save(product2, 3.0);

        Iterable<ProductDto> allProducts = productRepository.getAll();

        assertThat(allProducts).containsExactlyInAnyOrder(product1, product2);
    }

    @Test
    void shouldUpdateProductQuantityFromFiveToEight() {

        productRepository.save(productDto, 5.0);
        double updatedQuantity = 8.0;

        productRepository.updateQuantity(productDto, updatedQuantity);

        Optional<Double> quantity = productRepository.getQuantity(productDto);
        assertThat(quantity).isPresent().contains(updatedQuantity);
    }
}