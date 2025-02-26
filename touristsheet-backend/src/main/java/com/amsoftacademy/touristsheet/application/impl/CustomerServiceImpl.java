package com.amsoftacademy.touristsheet.application.impl;

import com.amsoftacademy.touristsheet.adapter.in.rest.dto.CustomerRequest;
import com.amsoftacademy.touristsheet.adapter.in.rest.dto.CustomerResponse;
import com.amsoftacademy.touristsheet.adapter.in.rest.exception.CustomerAlreadyExistsException;
import com.amsoftacademy.touristsheet.adapter.in.rest.exception.CustomerNotFoundException;
import com.amsoftacademy.touristsheet.adapter.in.rest.mapper.CustomerRequestMapper;
import com.amsoftacademy.touristsheet.adapter.in.rest.mapper.CustomerResponseMapper;
import com.amsoftacademy.touristsheet.adapter.out.persistance.CustomerRepository;
import com.amsoftacademy.touristsheet.application.port.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link TouristService} interface for managing Customers.
 * Handles CRUD operations for Customers.
 * Implements the business logic for managing Customers.
 * Uses CustomerRepository for data access and CustomerRequestMapper/CustomerResponseMapper
 * for mapping DTOs to entities and entities to DTOs.
 * This class is annotated with @Service to mark it as a service component in the Spring application context.
 * It is also annotated with @Transactional to enable transaction management for the service methods.
 * The qualifier "CustomerServiceImpl" is used to differentiate this implementation
 * when multiple implementations of TouristService exist.
 * This class is designed to work with CustomerRequest DTO and CustomerResponse DTO.
 *
 * @author Alex Pumnea
 */
@Service
@Transactional
@RequiredArgsConstructor
@Qualifier("CustomerServiceImpl")
public class CustomerServiceImpl implements TouristService<CustomerRequest, CustomerResponse> {

    public static final String CUSTOMER_WITH_ID_WAS_NOT_FOUND = "Customer with id: %d was not found";
    private final CustomerRepository customerRepository;
    private final CustomerResponseMapper responseMapper;
    private final CustomerRequestMapper requestMapper;

    /**
     * Retrieves all Customers.
     *
     * @return an iterable of the CustomerResponse object
     */

    @Override
    public Iterable<CustomerResponse> getAll() {
        return this.customerRepository.findAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a Customer by ID.
     *
     * @param id the ID of the Customer to retrieve
     * @return the CustomerResponse if found
     * @throws CustomerNotFoundException if the Customer with the specified ID does not exist
     */

    @Override
    public CustomerResponse getById(Long id) {
        return this.customerRepository.findById(id)
                .map(responseMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException(String.format(CUSTOMER_WITH_ID_WAS_NOT_FOUND, id)));
    }

    /**
     * Saves a new Customer.
     *
     * @param request the CustomerRequest object
     * @return the saved CustomerResponse object
     * @throws CustomerAlreadyExistsException if a duplication is detected
     */

    @Override
    public CustomerResponse save(CustomerRequest request) {
        return Optional.of(request)
                .map(requestMapper::toEntity)
                .map(customerRepository::save)
                .map(responseMapper::toDto)
                .orElseThrow(() -> new CustomerAlreadyExistsException("Customer already exists"));
    }

    /**
     * Updates an existing Customer.
     *
     * @param id      the ID of the Customer to update
     * @param request the updated CustomerRequest object
     * @return the updated CustomerResponse
     * @throws CustomerNotFoundException if the Customer with the specified ID does not exist
     */

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        return this.customerRepository.findById(id)
                .map(customer -> {
                    requestMapper.partialUpdate(request, customer);
                    return responseMapper.toDto(this.customerRepository.save(customer));
                })
                .orElseThrow(() -> new CustomerNotFoundException(String.format(CUSTOMER_WITH_ID_WAS_NOT_FOUND, id)));
    }

    /**
     * Removes a Customer by ID.
     *
     * @param id the ID of the Customer to remove
     * @throws CustomerNotFoundException if the Customer with the specified ID does not exist
     */

    @Override
    public void removeById(Long id) {
        this.customerRepository.findAll().stream()
                .filter(touristSheet -> touristSheet.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(customerRepository::delete, () -> {
                    throw new CustomerNotFoundException(String.format(CUSTOMER_WITH_ID_WAS_NOT_FOUND, id));
                });
    }

    /**
     * Removes all Customers.
     */

    @Override
    public void removeAll() {
        this.customerRepository.deleteAll();
    }
}

