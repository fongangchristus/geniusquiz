package com.it4innov.geniusquiz.service.impl;

import com.it4innov.geniusquiz.service.ReponseUserService;
import com.it4innov.geniusquiz.domain.ReponseUser;
import com.it4innov.geniusquiz.repository.ReponseUserRepository;
import com.it4innov.geniusquiz.service.dto.ReponseUserDTO;
import com.it4innov.geniusquiz.service.mapper.ReponseUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ReponseUser}.
 */
@Service
@Transactional
public class ReponseUserServiceImpl implements ReponseUserService {

    private final Logger log = LoggerFactory.getLogger(ReponseUserServiceImpl.class);

    private final ReponseUserRepository reponseUserRepository;

    private final ReponseUserMapper reponseUserMapper;

    public ReponseUserServiceImpl(ReponseUserRepository reponseUserRepository, ReponseUserMapper reponseUserMapper) {
        this.reponseUserRepository = reponseUserRepository;
        this.reponseUserMapper = reponseUserMapper;
    }

    @Override
    public ReponseUserDTO save(ReponseUserDTO reponseUserDTO) {
        log.debug("Request to save ReponseUser : {}", reponseUserDTO);
        ReponseUser reponseUser = reponseUserMapper.toEntity(reponseUserDTO);
        reponseUser = reponseUserRepository.save(reponseUser);
        return reponseUserMapper.toDto(reponseUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReponseUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReponseUsers");
        return reponseUserRepository.findAll(pageable)
            .map(reponseUserMapper::toDto);
    }


    public Page<ReponseUserDTO> findAllWithEagerRelationships(Pageable pageable) {
        return reponseUserRepository.findAllWithEagerRelationships(pageable).map(reponseUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReponseUserDTO> findOne(Long id) {
        log.debug("Request to get ReponseUser : {}", id);
        return reponseUserRepository.findOneWithEagerRelationships(id)
            .map(reponseUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReponseUser : {}", id);
        reponseUserRepository.deleteById(id);
    }
}
