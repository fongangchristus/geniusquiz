package com.it4innov.geniusquiz.service.impl;

import com.it4innov.geniusquiz.service.NiveauService;
import com.it4innov.geniusquiz.domain.Niveau;
import com.it4innov.geniusquiz.repository.NiveauRepository;
import com.it4innov.geniusquiz.service.dto.NiveauDTO;
import com.it4innov.geniusquiz.service.mapper.NiveauMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Niveau}.
 */
@Service
@Transactional
public class NiveauServiceImpl implements NiveauService {

    private final Logger log = LoggerFactory.getLogger(NiveauServiceImpl.class);

    private final NiveauRepository niveauRepository;

    private final NiveauMapper niveauMapper;

    public NiveauServiceImpl(NiveauRepository niveauRepository, NiveauMapper niveauMapper) {
        this.niveauRepository = niveauRepository;
        this.niveauMapper = niveauMapper;
    }

    @Override
    public NiveauDTO save(NiveauDTO niveauDTO) {
        log.debug("Request to save Niveau : {}", niveauDTO);
        Niveau niveau = niveauMapper.toEntity(niveauDTO);
        niveau = niveauRepository.save(niveau);
        return niveauMapper.toDto(niveau);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NiveauDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Niveaus");
        return niveauRepository.findAll(pageable)
            .map(niveauMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NiveauDTO> findOne(Long id) {
        log.debug("Request to get Niveau : {}", id);
        return niveauRepository.findById(id)
            .map(niveauMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Niveau : {}", id);
        niveauRepository.deleteById(id);
    }
}
