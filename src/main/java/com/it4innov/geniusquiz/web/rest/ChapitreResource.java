package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.service.ChapitreService;
import com.it4innov.geniusquiz.web.rest.errors.BadRequestAlertException;
import com.it4innov.geniusquiz.service.dto.ChapitreDTO;

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
 * REST controller for managing {@link com.it4innov.geniusquiz.domain.Chapitre}.
 */
@RestController
@RequestMapping("/api")
public class ChapitreResource {

    private final Logger log = LoggerFactory.getLogger(ChapitreResource.class);

    private static final String ENTITY_NAME = "chapitre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChapitreService chapitreService;

    public ChapitreResource(ChapitreService chapitreService) {
        this.chapitreService = chapitreService;
    }

    /**
     * {@code POST  /chapitres} : Create a new chapitre.
     *
     * @param chapitreDTO the chapitreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chapitreDTO, or with status {@code 400 (Bad Request)} if the chapitre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chapitres")
    public ResponseEntity<ChapitreDTO> createChapitre(@Valid @RequestBody ChapitreDTO chapitreDTO) throws URISyntaxException {
        log.debug("REST request to save Chapitre : {}", chapitreDTO);
        if (chapitreDTO.getId() != null) {
            throw new BadRequestAlertException("A new chapitre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChapitreDTO result = chapitreService.save(chapitreDTO);
        return ResponseEntity.created(new URI("/api/chapitres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chapitres} : Updates an existing chapitre.
     *
     * @param chapitreDTO the chapitreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chapitreDTO,
     * or with status {@code 400 (Bad Request)} if the chapitreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chapitreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chapitres")
    public ResponseEntity<ChapitreDTO> updateChapitre(@Valid @RequestBody ChapitreDTO chapitreDTO) throws URISyntaxException {
        log.debug("REST request to update Chapitre : {}", chapitreDTO);
        if (chapitreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChapitreDTO result = chapitreService.save(chapitreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chapitreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chapitres} : get all the chapitres.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chapitres in body.
     */
    @GetMapping("/chapitres")
    public ResponseEntity<List<ChapitreDTO>> getAllChapitres(Pageable pageable) {
        log.debug("REST request to get a page of Chapitres");
        Page<ChapitreDTO> page = chapitreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /chapitres/:id} : get the "id" chapitre.
     *
     * @param id the id of the chapitreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chapitreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chapitres/{id}")
    public ResponseEntity<ChapitreDTO> getChapitre(@PathVariable Long id) {
        log.debug("REST request to get Chapitre : {}", id);
        Optional<ChapitreDTO> chapitreDTO = chapitreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chapitreDTO);
    }

    /**
     * {@code DELETE  /chapitres/:id} : delete the "id" chapitre.
     *
     * @param id the id of the chapitreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chapitres/{id}")
    public ResponseEntity<Void> deleteChapitre(@PathVariable Long id) {
        log.debug("REST request to delete Chapitre : {}", id);
        chapitreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
