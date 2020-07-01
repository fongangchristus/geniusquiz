package com.it4innov.geniusquiz.service;

import com.it4innov.geniusquiz.service.dto.ReponseUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.it4innov.geniusquiz.domain.ReponseUser}.
 */
public interface ReponseUserService {

    /**
     * Save a reponseUser.
     *
     * @param reponseUserDTO the entity to save.
     * @return the persisted entity.
     */
    ReponseUserDTO save(ReponseUserDTO reponseUserDTO);

    /**
     * Get all the reponseUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReponseUserDTO> findAll(Pageable pageable);

    /**
     * Get all the reponseUsers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ReponseUserDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" reponseUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReponseUserDTO> findOne(Long id);

    /**
     * Delete the "id" reponseUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
