package com.amsoftacademy.touristsheet.application.port;

import org.springframework.stereotype.Service;

/**
 * This interface defines the contract for the TouristService,
 * which provides operations for retrieving, creating, updating, and deleting.
 *
 * @param <T> request object used for creating or updating
 * @param <R> response object returned
 * @author Alex Pumnea
 */
@Service
public interface TouristService<T, R> {

    /**
     * Retrieves all objects.
     *
     * @return an iterable collection of response objects
     */
    Iterable<R> getAll();

    /**
     * Retrieves an object by the specified ID.
     *
     * @param id the ID of the object to retrieve
     * @return the response object
     */

    R getById(Long id);

    /**
     * Saves a new object based on the provided request object.
     *
     * @param request the request object
     * @return the created response object
     */

    R save(T request);

    /**
     * Updates an existing object with the specified ID based on the provided request object.
     *
     * @param id      the ID of the object to update
     * @param request the request object containing the updated object information
     * @return the updated response object
     */

    R update(Long id, T request);

    /**
     * Removes an object by the specified ID.
     *
     * @param id the ID of the object to remove
     */

    void removeById(Long id);

    /**
     * Removes all objects.
     */

    void removeAll();
}
