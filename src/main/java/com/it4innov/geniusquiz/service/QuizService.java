package com.it4innov.geniusquiz.service;

import com.it4innov.geniusquiz.service.dto.QuizDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.it4innov.geniusquiz.domain.Quiz}.
 */
public interface QuizService {

    /**
     * Save a quiz.
     *
     * @param quizDTO the entity to save.
     * @return the persisted entity.
     */
    QuizDTO save(QuizDTO quizDTO);

    /**
     * Get all the quizzes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuizDTO> findAll(Pageable pageable);

    /**
     * Get all the quizzes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<QuizDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" quiz.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuizDTO> findOne(Long id);

    /**
     * Delete the "id" quiz.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
