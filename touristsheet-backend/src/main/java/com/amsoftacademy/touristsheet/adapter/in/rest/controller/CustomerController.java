package com.amsoftacademy.touristsheet.adapter.in.rest.controller;

import com.amsoftacademy.touristsheet.adapter.in.rest.dto.CustomerRequest;
import com.amsoftacademy.touristsheet.adapter.in.rest.dto.CustomerResponse;
import com.amsoftacademy.touristsheet.application.port.TouristService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for handling Customer-related operations.
 * Acts as an entry point for Customer-related API endpoints.
 *
 * @author Alex Pumnea
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final TouristService<CustomerRequest, CustomerResponse> customerService;

    /**
     * Retrieves all Customers.
     *
     * @return ResponseEntity containing the iterable of CustomerResponses
     */
    @GetMapping
    ResponseEntity<Iterable<CustomerResponse>> getAll() {
        return ResponseEntity.ok(this.customerService.getAll());
    }

    /**
     * Retrieves a Customer by ID.
     *
     * @param id the ID of the Customer to retrieve
     * @return ResponseEntity containing the CustomerResponse
     */
    @GetMapping({"/{id}"})
    ResponseEntity<CustomerResponse> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.customerService.getById(id));
    }

    /**
     * Creates a new Customer.
     *
     * @param request the CustomerRequest object containing the Customer details
     * @return ResponseEntity containing the created CustomerResponse
     */
    @PostMapping
    ResponseEntity<CustomerResponse> save(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.customerService.save(request));
    }

    /**
     * Updates an existing Customer.
     *
     * @param id      the ID of the Customer to update
     * @param request the CustomerRequest object containing the updated Customer details
     * @return ResponseEntity containing the updated CustomerResponse
     */
    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> update(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.customerService.update(id, request));
    }

    /**
     * Deletes a Customer by ID.
     *
     * @param id the ID of the Customer to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    ResponseEntity<CustomerResponse> deleteById(@PathVariable Long id) {
        this.customerService.removeById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Deletes all Customers.
     *
     * @return ResponseEntity with no content
     */
    @DeleteMapping
    ResponseEntity<CustomerResponse> deleteAll() {
        this.customerService.removeAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
