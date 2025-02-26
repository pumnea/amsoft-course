package com.amsoft.shopping.core.repository;

import com.amsoft.shopping.core.dto.BookDto;
import com.amsoft.shopping.core.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Alex Pumnea
 */
class ProductRepositoryTest {
    @Mock
    private ProductRepository<ProductDto> repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveOneItem() {
        BookDto bookDto = new BookDto("Java in Action", 20, "Joshua Block", "IT");
        double quantity = 5.0;

        repository.save(bookDto, quantity);

        verify(repository).save(bookDto, quantity);
    }

    @Test
    void shouldDeleteOneItem() {
        BookDto bookDto = new BookDto("Java in Action", 20, "Joshua Block", "IT");

        repository.delete(bookDto);

        verify(repository).delete(bookDto);
    }

    @Test
    void shouldReturnAllItems() {
        BookDto bookDto1 = new BookDto("Java in Action", 20, "Joshua Block", "IT");
        BookDto bookDto2 = new BookDto("Cruising along with Java", 30, "Venkat Subramaniam", "IT");

        when(repository.getAll()).thenReturn(List.of(bookDto1, bookDto2));

        Iterable<ProductDto> result = repository.getAll();

        assertThat(result).containsExactlyInAnyOrder(bookDto1, bookDto2);
    }

    @Test
    void shouldReturnQuantityOfTen() {
        BookDto bookDto = new BookDto("Java in Action", 20, "Joshua Block", "IT");
        double expectedQuantity = 10.0;

        when(repository.getQuantity(bookDto)).thenReturn(Optional.of(expectedQuantity));

        Optional<Double> result = repository.getQuantity(bookDto);

        assertThat(result).isPresent().contains(expectedQuantity);
    }

    @Test
    void shouldUpdateQuantityToEight() {
        BookDto bookDto = new BookDto("Java in Action", 20, "Joshua Block", "IT");
        double newQuantity = 8.0;

        repository.updateQuantity(bookDto, newQuantity);

        verify(repository).updateQuantity(bookDto, newQuantity);
    }
}
