package com.it4innov.geniusquiz.service.impl;

import com.it4innov.geniusquiz.service.CursusService;
import com.it4innov.geniusquiz.domain.Cursus;
import com.it4innov.geniusquiz.repository.CursusRepository;
import com.it4innov.geniusquiz.service.dto.CursusDTO;
import com.it4innov.geniusquiz.service.mapper.CursusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cursus}.
 */
@Service
@Transactional
public class CursusServiceImpl implements CursusService {

    private final Logger log = LoggerFactory.getLogger(CursusServiceImpl.class);

    private final CursusRepository cursusRepository;

    private final CursusMapper cursusMapper;

    public CursusServiceImpl(CursusRepository cursusRepository, CursusMapper cursusMapper) {
        this.cursusRepository = cursusRepository;
        this.cursusMapper = cursusMapper;
    }

    @Override
    public CursusDTO save(CursusDTO cursusDTO) {
        log.debug("Request to save Cursus : {}", cursusDTO);
        Cursus cursus = cursusMapper.toEntity(cursusDTO);
        cursus = cursusRepository.save(cursus);
        return cursusMapper.toDto(cursus);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CursusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cursuses");
        return cursusRepository.findAll(pageable)
            .map(cursusMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CursusDTO> findOne(Long id) {
        log.debug("Request to get Cursus : {}", id);
        return cursusRepository.findById(id)
            .map(cursusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cursus : {}", id);
        cursusRepository.deleteById(id);
    }
}
