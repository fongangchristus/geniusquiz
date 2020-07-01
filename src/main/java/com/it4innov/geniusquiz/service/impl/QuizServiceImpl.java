package com.it4innov.geniusquiz.service.impl;

import com.it4innov.geniusquiz.service.QuizService;
import com.it4innov.geniusquiz.domain.Quiz;
import com.it4innov.geniusquiz.repository.QuizRepository;
import com.it4innov.geniusquiz.service.dto.QuizDTO;
import com.it4innov.geniusquiz.service.mapper.QuizMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Quiz}.
 */
@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final Logger log = LoggerFactory.getLogger(QuizServiceImpl.class);

    private final QuizRepository quizRepository;

    private final QuizMapper quizMapper;

    public QuizServiceImpl(QuizRepository quizRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public QuizDTO save(QuizDTO quizDTO) {
        log.debug("Request to save Quiz : {}", quizDTO);
        Quiz quiz = quizMapper.toEntity(quizDTO);
        quiz = quizRepository.save(quiz);
        return quizMapper.toDto(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuizDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Quizzes");
        return quizRepository.findAll(pageable)
            .map(quizMapper::toDto);
    }


    public Page<QuizDTO> findAllWithEagerRelationships(Pageable pageable) {
        return quizRepository.findAllWithEagerRelationships(pageable).map(quizMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuizDTO> findOne(Long id) {
        log.debug("Request to get Quiz : {}", id);
        return quizRepository.findOneWithEagerRelationships(id)
            .map(quizMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Quiz : {}", id);
        quizRepository.deleteById(id);
    }
}
