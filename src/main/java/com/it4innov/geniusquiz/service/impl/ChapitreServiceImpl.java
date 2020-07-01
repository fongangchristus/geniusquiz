package com.it4innov.geniusquiz.service.impl;

import com.it4innov.geniusquiz.service.ChapitreService;
import com.it4innov.geniusquiz.domain.Chapitre;
import com.it4innov.geniusquiz.repository.ChapitreRepository;
import com.it4innov.geniusquiz.service.dto.ChapitreDTO;
import com.it4innov.geniusquiz.service.mapper.ChapitreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Chapitre}.
 */
@Service
@Transactional
public class ChapitreServiceImpl implements ChapitreService {

    private final Logger log = LoggerFactory.getLogger(ChapitreServiceImpl.class);

    private final ChapitreRepository chapitreRepository;

    private final ChapitreMapper chapitreMapper;

    public ChapitreServiceImpl(ChapitreRepository chapitreRepository, ChapitreMapper chapitreMapper) {
        this.chapitreRepository = chapitreRepository;
        this.chapitreMapper = chapitreMapper;
    }

    @Override
    public ChapitreDTO save(ChapitreDTO chapitreDTO) {
        log.debug("Request to save Chapitre : {}", chapitreDTO);
        Chapitre chapitre = chapitreMapper.toEntity(chapitreDTO);
        chapitre = chapitreRepository.save(chapitre);
        return chapitreMapper.toDto(chapitre);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChapitreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Chapitres");
        return chapitreRepository.findAll(pageable)
            .map(chapitreMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ChapitreDTO> findOne(Long id) {
        log.debug("Request to get Chapitre : {}", id);
        return chapitreRepository.findById(id)
            .map(chapitreMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Chapitre : {}", id);
        chapitreRepository.deleteById(id);
    }
}
