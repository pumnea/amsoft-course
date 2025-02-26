package com.amsoftacademy.touristsheet.application.impl;

import com.amsoftacademy.touristsheet.adapter.in.rest.dto.TouristSheetRequest;
import com.amsoftacademy.touristsheet.adapter.in.rest.dto.TouristSheetResponse;
import com.amsoftacademy.touristsheet.adapter.in.rest.exception.TouristSheetAlreadyExistsException;
import com.amsoftacademy.touristsheet.adapter.in.rest.exception.TouristSheetNotFoundException;
import com.amsoftacademy.touristsheet.adapter.in.rest.mapper.TouristSheetRequestMapper;
import com.amsoftacademy.touristsheet.adapter.in.rest.mapper.TouristSheetResponseMapper;
import com.amsoftacademy.touristsheet.adapter.out.persistance.TouristSheetRepository;
import com.amsoftacademy.touristsheet.application.port.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link TouristService} interface for managing TouristSheets.
 * Handles CRUD operations for TouristSheets.
 * Implements the business logic for managing TouristSheets.
 * Uses TouristSheetRepository for data access and TouristSheetRequestMapper/TouristSheetResponseMapper
 * for mapping DTOs to entities  and entities to DTOs.
 * This class is annotated with @Service to mark it as a service component in the Spring application context.
 * It is also annotated with @Transactional to enable transaction management for the service methods.
 * The qualifier "TouristSheetServiceImpl" is used to differentiate this implementation
 * when multiple implementations of TouristService exist.
 * This class is designed to work with TouristSheetRequest DTO and TouristSheetResponse DTO.
 *
 * @author Alex Pumnea
 */
@Service
@Transactional
@RequiredArgsConstructor
@Qualifier("TouristSheetServiceImpl")
public class TouristSheetServiceImpl implements TouristService<TouristSheetRequest, TouristSheetResponse> {

    public static final String TOURIST_SHEET_WITH_ID_WAS_NOT_FOUND = "Tourist Sheet with id: %d was not found";
    private final TouristSheetRepository touristSheetRepository;
    private final TouristSheetRequestMapper requestMapper;
    private final TouristSheetResponseMapper responseMapper;

    /**
     * Retrieves all TouristSheets.
     *
     * @return an iterable of the TouristSheetResponse object
     */
    @Override
    public Iterable<TouristSheetResponse> getAll() {
        return this.touristSheetRepository.findAll().stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a TouristSheet by ID.
     *
     * @param id the ID of the TouristSheet to retrieve
     * @return the TouristSheetResponse if found
     * @throws TouristSheetNotFoundException if the TouristSheet with the specified ID does not exist
     */

    @Override
    public TouristSheetResponse getById(Long id) {
        return this.touristSheetRepository.findById(id)
                .map(responseMapper::toDto)
                .orElseThrow(() -> new TouristSheetNotFoundException(
                        String.format(TOURIST_SHEET_WITH_ID_WAS_NOT_FOUND, id)));
    }

    /**
     * Saves a new TouristSheet.
     *
     * @param request the TouristSheetRequest object
     * @return the saved TouristSheetResponse object
     * @throws TouristSheetAlreadyExistsException if a duplication is detected
     */
    @Override
    public TouristSheetResponse save(@Valid TouristSheetRequest request) {
        return Optional.of(request)
                .map(requestMapper::toEntity)
                .map(touristSheetRepository::save)
                .map(responseMapper::toDto)
                .orElseThrow(() -> new TouristSheetAlreadyExistsException("Tourist Sheet already exists"));
    }

    /**
     * Updates an existing TouristSheet.
     *
     * @param id      the ID of the TouristSheet to update
     * @param request the updated TouristSheetRequest object
     * @return the updated TouristSheetResponse
     * @throws TouristSheetNotFoundException if the TouristSheet with the specified ID does not exist
     */

    @Override
    public TouristSheetResponse update(Long id, @Valid TouristSheetRequest request) {
        return this.touristSheetRepository.findById(id)
                .map(existingTouristSheet -> {
                    requestMapper.partialUpdate(request, existingTouristSheet);
                    return responseMapper.toDto(touristSheetRepository.save(existingTouristSheet));
                })
                .orElseThrow(() -> new TouristSheetNotFoundException(
                        String.format(TOURIST_SHEET_WITH_ID_WAS_NOT_FOUND, id)));
    }

    /**
     * Removes a TouristSheet by ID.
     *
     * @param id the ID of the TouristSheet to remove
     * @throws TouristSheetNotFoundException if the TouristSheet with the specified ID does not exist
     */

    @Override
    public void removeById(Long id) {
        this.touristSheetRepository.findAll().stream()
                .filter(touristSheet -> touristSheet.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(touristSheetRepository::delete, () -> {
                    throw new TouristSheetNotFoundException(String.format(TOURIST_SHEET_WITH_ID_WAS_NOT_FOUND, id));
                });
    }

    /**
     * Removes all TouristSheets.
     */

    @Override
    public void removeAll() {
        this.touristSheetRepository.deleteAll();
    }
}