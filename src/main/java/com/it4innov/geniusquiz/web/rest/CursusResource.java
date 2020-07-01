package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.service.CursusService;
import com.it4innov.geniusquiz.web.rest.errors.BadRequestAlertException;
import com.it4innov.geniusquiz.service.dto.CursusDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.it4innov.geniusquiz.domain.Cursus}.
 */
@RestController
@RequestMapping("/api")
public class CursusResource {

    private final Logger log = LoggerFactory.getLogger(CursusResource.class);

    private static final String ENTITY_NAME = "cursus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CursusService cursusService;

    public CursusResource(CursusService cursusService) {
        this.cursusService = cursusService;
    }

    /**
     * {@code POST  /cursuses} : Create a new cursus.
     *
     * @param cursusDTO the cursusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cursusDTO, or with status {@code 400 (Bad Request)} if the cursus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cursuses")
    public ResponseEntity<CursusDTO> createCursus(@RequestBody CursusDTO cursusDTO) throws URISyntaxException {
        log.debug("REST request to save Cursus : {}", cursusDTO);
        if (cursusDTO.getId() != null) {
            throw new BadRequestAlertException("A new cursus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CursusDTO result = cursusService.save(cursusDTO);
        return ResponseEntity.created(new URI("/api/cursuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cursuses} : Updates an existing cursus.
     *
     * @param cursusDTO the cursusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cursusDTO,
     * or with status {@code 400 (Bad Request)} if the cursusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cursusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cursuses")
    public ResponseEntity<CursusDTO> updateCursus(@RequestBody CursusDTO cursusDTO) throws URISyntaxException {
        log.debug("REST request to update Cursus : {}", cursusDTO);
        if (cursusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CursusDTO result = cursusService.save(cursusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cursusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cursuses} : get all the cursuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cursuses in body.
     */
    @GetMapping("/cursuses")
    public ResponseEntity<List<CursusDTO>> getAllCursuses(Pageable pageable) {
        log.debug("REST request to get a page of Cursuses");
        Page<CursusDTO> page = cursusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cursuses/:id} : get the "id" cursus.
     *
     * @param id the id of the cursusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cursusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cursuses/{id}")
    public ResponseEntity<CursusDTO> getCursus(@PathVariable Long id) {
        log.debug("REST request to get Cursus : {}", id);
        Optional<CursusDTO> cursusDTO = cursusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cursusDTO);
    }

    /**
     * {@code DELETE  /cursuses/:id} : delete the "id" cursus.
     *
     * @param id the id of the cursusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cursuses/{id}")
    public ResponseEntity<Void> deleteCursus(@PathVariable Long id) {
        log.debug("REST request to delete Cursus : {}", id);
        cursusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
