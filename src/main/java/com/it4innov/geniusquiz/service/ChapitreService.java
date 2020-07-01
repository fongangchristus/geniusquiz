package com.it4innov.geniusquiz.service;

import com.it4innov.geniusquiz.service.dto.ChapitreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.it4innov.geniusquiz.domain.Chapitre}.
 */
public interface ChapitreService {

    /**
     * Save a chapitre.
     *
     * @param chapitreDTO the entity to save.
     * @return the persisted entity.
     */
    ChapitreDTO save(ChapitreDTO chapitreDTO);

    /**
     * Get all the chapitres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChapitreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" chapitre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChapitreDTO> findOne(Long id);

    /**
     * Delete the "id" chapitre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
