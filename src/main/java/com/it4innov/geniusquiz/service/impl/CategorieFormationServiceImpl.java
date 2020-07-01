package com.it4innov.geniusquiz.service.impl;

import com.it4innov.geniusquiz.service.CategorieFormationService;
import com.it4innov.geniusquiz.domain.CategorieFormation;
import com.it4innov.geniusquiz.repository.CategorieFormationRepository;
import com.it4innov.geniusquiz.service.dto.CategorieFormationDTO;
import com.it4innov.geniusquiz.service.mapper.CategorieFormationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CategorieFormation}.
 */
@Service
@Transactional
public class CategorieFormationServiceImpl implements CategorieFormationService {

    private final Logger log = LoggerFactory.getLogger(CategorieFormationServiceImpl.class);

    private final CategorieFormationRepository categorieFormationRepository;

    private final CategorieFormationMapper categorieFormationMapper;

    public CategorieFormationServiceImpl(CategorieFormationRepository categorieFormationRepository, CategorieFormationMapper categorieFormationMapper) {
        this.categorieFormationRepository = categorieFormationRepository;
        this.categorieFormationMapper = categorieFormationMapper;
    }

    @Override
    public CategorieFormationDTO save(CategorieFormationDTO categorieFormationDTO) {
        log.debug("Request to save CategorieFormation : {}", categorieFormationDTO);
        CategorieFormation categorieFormation = categorieFormationMapper.toEntity(categorieFormationDTO);
        categorieFormation = categorieFormationRepository.save(categorieFormation);
        return categorieFormationMapper.toDto(categorieFormation);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategorieFormationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategorieFormations");
        return categorieFormationRepository.findAll(pageable)
            .map(categorieFormationMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieFormationDTO> findOne(Long id) {
        log.debug("Request to get CategorieFormation : {}", id);
        return categorieFormationRepository.findById(id)
            .map(categorieFormationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategorieFormation : {}", id);
        categorieFormationRepository.deleteById(id);
    }
}
