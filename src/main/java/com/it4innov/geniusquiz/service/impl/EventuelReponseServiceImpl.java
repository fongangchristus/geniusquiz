package com.it4innov.geniusquiz.service.impl;

import com.it4innov.geniusquiz.service.EventuelReponseService;
import com.it4innov.geniusquiz.domain.EventuelReponse;
import com.it4innov.geniusquiz.repository.EventuelReponseRepository;
import com.it4innov.geniusquiz.service.dto.EventuelReponseDTO;
import com.it4innov.geniusquiz.service.mapper.EventuelReponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EventuelReponse}.
 */
@Service
@Transactional
public class EventuelReponseServiceImpl implements EventuelReponseService {

    private final Logger log = LoggerFactory.getLogger(EventuelReponseServiceImpl.class);

    private final EventuelReponseRepository eventuelReponseRepository;

    private final EventuelReponseMapper eventuelReponseMapper;

    public EventuelReponseServiceImpl(EventuelReponseRepository eventuelReponseRepository, EventuelReponseMapper eventuelReponseMapper) {
        this.eventuelReponseRepository = eventuelReponseRepository;
        this.eventuelReponseMapper = eventuelReponseMapper;
    }

    @Override
    public EventuelReponseDTO save(EventuelReponseDTO eventuelReponseDTO) {
        log.debug("Request to save EventuelReponse : {}", eventuelReponseDTO);
        EventuelReponse eventuelReponse = eventuelReponseMapper.toEntity(eventuelReponseDTO);
        eventuelReponse = eventuelReponseRepository.save(eventuelReponse);
        return eventuelReponseMapper.toDto(eventuelReponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EventuelReponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventuelReponses");
        return eventuelReponseRepository.findAll(pageable)
            .map(eventuelReponseMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EventuelReponseDTO> findOne(Long id) {
        log.debug("Request to get EventuelReponse : {}", id);
        return eventuelReponseRepository.findById(id)
            .map(eventuelReponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventuelReponse : {}", id);
        eventuelReponseRepository.deleteById(id);
    }
}
