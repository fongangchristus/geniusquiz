package com.it4innov.geniusquiz.service;

import com.it4innov.geniusquiz.service.dto.CategorieFormationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.it4innov.geniusquiz.domain.CategorieFormation}.
 */
public interface CategorieFormationService {

    /**
     * Save a categorieFormation.
     *
     * @param categorieFormationDTO the entity to save.
     * @return the persisted entity.
     */
    CategorieFormationDTO save(CategorieFormationDTO categorieFormationDTO);

    /**
     * Get all the categorieFormations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CategorieFormationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" categorieFormation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CategorieFormationDTO> findOne(Long id);

    /**
     * Delete the "id" categorieFormation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
