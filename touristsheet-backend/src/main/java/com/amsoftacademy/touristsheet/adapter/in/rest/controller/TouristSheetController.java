package com.amsoftacademy.touristsheet.adapter.in.rest.controller;

import com.amsoftacademy.touristsheet.adapter.in.rest.dto.TouristSheetRequest;
import com.amsoftacademy.touristsheet.adapter.in.rest.dto.TouristSheetResponse;
import com.amsoftacademy.touristsheet.application.port.TouristService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for handling TouristSheet-related operations.
 * Acts as an entry point for TouristSheet-related API endpoints.
 *
 * @author Alex Pumnea
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tourist-sheets")
public class TouristSheetController {
    private final TouristService<TouristSheetRequest, TouristSheetResponse> touristService;

    /**
     * Retrieves all TouristSheets.
     *
     * @return ResponseEntity containing the iterable of TouristSheet
     */
    @GetMapping
    ResponseEntity<Iterable<TouristSheetResponse>> getAll() {
        return ResponseEntity.ok(touristService.getAll());
    }

    /**
     * Retrieves a TouristSheet by ID.
     *
     * @param id the ID of the TouristSheet to retrieve
     * @return ResponseEntity containing the TouristSheetResponse
     */
    @GetMapping("/{id}")
    ResponseEntity<TouristSheetResponse> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.touristService.getById(id));
    }

    /**
     * Creates a new TouristSheet.
     *
     * @param request the TouristSheetRequest object containing the TouristSheet details
     * @return ResponseEntity containing the created TouristSheetResponse
     */
    @PostMapping
    ResponseEntity<TouristSheetResponse> save(@Valid @RequestBody TouristSheetRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.touristService.save(request));
    }

    /**
     * Updates an existing TouristSheet.
     *
     * @param id      the ID of the TouristSheet to update
     * @param request the TouristSheetRequest object containing the updated TouristSheet details
     * @return ResponseEntity containing the updated TouristSheetResponse
     */
    @PutMapping("/{id}")
    ResponseEntity<TouristSheetResponse> updateById(@PathVariable Long id, @Valid @RequestBody TouristSheetRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.touristService.update(id, request));
    }

    /**
     * Deletes a TouristSheet by ID.
     *
     * @param id the ID of the TouristSheet to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    ResponseEntity<TouristSheetResponse> deleteById(@PathVariable Long id) {
        this.touristService.removeById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Deletes all TouristSheets.
     *
     * @return ResponseEntity with no content
     */
    @DeleteMapping()
    ResponseEntity<TouristSheetResponse> deleteAll() {
        this.touristService.removeAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
