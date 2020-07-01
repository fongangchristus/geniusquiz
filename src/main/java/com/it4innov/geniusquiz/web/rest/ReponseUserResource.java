package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.service.ReponseUserService;
import com.it4innov.geniusquiz.web.rest.errors.BadRequestAlertException;
import com.it4innov.geniusquiz.service.dto.ReponseUserDTO;

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
 * REST controller for managing {@link com.it4innov.geniusquiz.domain.ReponseUser}.
 */
@RestController
@RequestMapping("/api")
public class ReponseUserResource {

    private final Logger log = LoggerFactory.getLogger(ReponseUserResource.class);

    private static final String ENTITY_NAME = "reponseUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReponseUserService reponseUserService;

    public ReponseUserResource(ReponseUserService reponseUserService) {
        this.reponseUserService = reponseUserService;
    }

    /**
     * {@code POST  /reponse-users} : Create a new reponseUser.
     *
     * @param reponseUserDTO the reponseUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reponseUserDTO, or with status {@code 400 (Bad Request)} if the reponseUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reponse-users")
    public ResponseEntity<ReponseUserDTO> createReponseUser(@RequestBody ReponseUserDTO reponseUserDTO) throws URISyntaxException {
        log.debug("REST request to save ReponseUser : {}", reponseUserDTO);
        if (reponseUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new reponseUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReponseUserDTO result = reponseUserService.save(reponseUserDTO);
        return ResponseEntity.created(new URI("/api/reponse-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reponse-users} : Updates an existing reponseUser.
     *
     * @param reponseUserDTO the reponseUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reponseUserDTO,
     * or with status {@code 400 (Bad Request)} if the reponseUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reponseUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reponse-users")
    public ResponseEntity<ReponseUserDTO> updateReponseUser(@RequestBody ReponseUserDTO reponseUserDTO) throws URISyntaxException {
        log.debug("REST request to update ReponseUser : {}", reponseUserDTO);
        if (reponseUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReponseUserDTO result = reponseUserService.save(reponseUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reponseUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reponse-users} : get all the reponseUsers.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reponseUsers in body.
     */
    @GetMapping("/reponse-users")
    public ResponseEntity<List<ReponseUserDTO>> getAllReponseUsers(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of ReponseUsers");
        Page<ReponseUserDTO> page;
        if (eagerload) {
            page = reponseUserService.findAllWithEagerRelationships(pageable);
        } else {
            page = reponseUserService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reponse-users/:id} : get the "id" reponseUser.
     *
     * @param id the id of the reponseUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reponseUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reponse-users/{id}")
    public ResponseEntity<ReponseUserDTO> getReponseUser(@PathVariable Long id) {
        log.debug("REST request to get ReponseUser : {}", id);
        Optional<ReponseUserDTO> reponseUserDTO = reponseUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reponseUserDTO);
    }

    /**
     * {@code DELETE  /reponse-users/:id} : delete the "id" reponseUser.
     *
     * @param id the id of the reponseUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reponse-users/{id}")
    public ResponseEntity<Void> deleteReponseUser(@PathVariable Long id) {
        log.debug("REST request to delete ReponseUser : {}", id);
        reponseUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
