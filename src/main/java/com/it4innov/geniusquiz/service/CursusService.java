package com.it4innov.geniusquiz.service;

import com.it4innov.geniusquiz.service.dto.CursusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.it4innov.geniusquiz.domain.Cursus}.
 */
public interface CursusService {

    /**
     * Save a cursus.
     *
     * @param cursusDTO the entity to save.
     * @return the persisted entity.
     */
    CursusDTO save(CursusDTO cursusDTO);

    /**
     * Get all the cursuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CursusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cursus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CursusDTO> findOne(Long id);

    /**
     * Delete the "id" cursus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
