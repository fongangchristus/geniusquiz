package com.it4innov.geniusquiz.service;

import com.it4innov.geniusquiz.service.dto.ClasseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.it4innov.geniusquiz.domain.Classe}.
 */
public interface ClasseService {

    /**
     * Save a classe.
     *
     * @param classeDTO the entity to save.
     * @return the persisted entity.
     */
    ClasseDTO save(ClasseDTO classeDTO);

    /**
     * Get all the classes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClasseDTO> findAll(Pageable pageable);

    /**
     * Get all the classes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ClasseDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" classe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClasseDTO> findOne(Long id);

    /**
     * Delete the "id" classe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
