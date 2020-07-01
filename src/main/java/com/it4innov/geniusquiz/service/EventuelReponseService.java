package com.it4innov.geniusquiz.service;

import com.it4innov.geniusquiz.service.dto.EventuelReponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.it4innov.geniusquiz.domain.EventuelReponse}.
 */
public interface EventuelReponseService {

    /**
     * Save a eventuelReponse.
     *
     * @param eventuelReponseDTO the entity to save.
     * @return the persisted entity.
     */
    EventuelReponseDTO save(EventuelReponseDTO eventuelReponseDTO);

    /**
     * Get all the eventuelReponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EventuelReponseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" eventuelReponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventuelReponseDTO> findOne(Long id);

    /**
     * Delete the "id" eventuelReponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
