package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.service.EventuelReponseService;
import com.it4innov.geniusquiz.web.rest.errors.BadRequestAlertException;
import com.it4innov.geniusquiz.service.dto.EventuelReponseDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.it4innov.geniusquiz.domain.EventuelReponse}.
 */
@RestController
@RequestMapping("/api")
public class EventuelReponseResource {

    private final Logger log = LoggerFactory.getLogger(EventuelReponseResource.class);

    private static final String ENTITY_NAME = "eventuelReponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventuelReponseService eventuelReponseService;

    public EventuelReponseResource(EventuelReponseService eventuelReponseService) {
        this.eventuelReponseService = eventuelReponseService;
    }

    /**
     * {@code POST  /eventuel-reponses} : Create a new eventuelReponse.
     *
     * @param eventuelReponseDTO the eventuelReponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eventuelReponseDTO, or with status {@code 400 (Bad Request)} if the eventuelReponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eventuel-reponses")
    public ResponseEntity<EventuelReponseDTO> createEventuelReponse(@Valid @RequestBody EventuelReponseDTO eventuelReponseDTO) throws URISyntaxException {
        log.debug("REST request to save EventuelReponse : {}", eventuelReponseDTO);
        if (eventuelReponseDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventuelReponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventuelReponseDTO result = eventuelReponseService.save(eventuelReponseDTO);
        return ResponseEntity.created(new URI("/api/eventuel-reponses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /eventuel-reponses} : Updates an existing eventuelReponse.
     *
     * @param eventuelReponseDTO the eventuelReponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eventuelReponseDTO,
     * or with status {@code 400 (Bad Request)} if the eventuelReponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eventuelReponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eventuel-reponses")
    public ResponseEntity<EventuelReponseDTO> updateEventuelReponse(@Valid @RequestBody EventuelReponseDTO eventuelReponseDTO) throws URISyntaxException {
        log.debug("REST request to update EventuelReponse : {}", eventuelReponseDTO);
        if (eventuelReponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventuelReponseDTO result = eventuelReponseService.save(eventuelReponseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventuelReponseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /eventuel-reponses} : get all the eventuelReponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eventuelReponses in body.
     */
    @GetMapping("/eventuel-reponses")
    public ResponseEntity<List<EventuelReponseDTO>> getAllEventuelReponses(Pageable pageable) {
        log.debug("REST request to get a page of EventuelReponses");
        Page<EventuelReponseDTO> page = eventuelReponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /eventuel-reponses/:id} : get the "id" eventuelReponse.
     *
     * @param id the id of the eventuelReponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eventuelReponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eventuel-reponses/{id}")
    public ResponseEntity<EventuelReponseDTO> getEventuelReponse(@PathVariable Long id) {
        log.debug("REST request to get EventuelReponse : {}", id);
        Optional<EventuelReponseDTO> eventuelReponseDTO = eventuelReponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventuelReponseDTO);
    }

    /**
     * {@code DELETE  /eventuel-reponses/:id} : delete the "id" eventuelReponse.
     *
     * @param id the id of the eventuelReponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eventuel-reponses/{id}")
    public ResponseEntity<Void> deleteEventuelReponse(@PathVariable Long id) {
        log.debug("REST request to delete EventuelReponse : {}", id);
        eventuelReponseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
