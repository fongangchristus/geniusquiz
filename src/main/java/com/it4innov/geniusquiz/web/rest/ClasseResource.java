package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.service.ClasseService;
import com.it4innov.geniusquiz.web.rest.errors.BadRequestAlertException;
import com.it4innov.geniusquiz.service.dto.ClasseDTO;

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
 * REST controller for managing {@link com.it4innov.geniusquiz.domain.Classe}.
 */
@RestController
@RequestMapping("/api")
public class ClasseResource {

    private final Logger log = LoggerFactory.getLogger(ClasseResource.class);

    private static final String ENTITY_NAME = "classe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClasseService classeService;

    public ClasseResource(ClasseService classeService) {
        this.classeService = classeService;
    }

    /**
     * {@code POST  /classes} : Create a new classe.
     *
     * @param classeDTO the classeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classeDTO, or with status {@code 400 (Bad Request)} if the classe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/classes")
    public ResponseEntity<ClasseDTO> createClasse(@RequestBody ClasseDTO classeDTO) throws URISyntaxException {
        log.debug("REST request to save Classe : {}", classeDTO);
        if (classeDTO.getId() != null) {
            throw new BadRequestAlertException("A new classe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClasseDTO result = classeService.save(classeDTO);
        return ResponseEntity.created(new URI("/api/classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /classes} : Updates an existing classe.
     *
     * @param classeDTO the classeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classeDTO,
     * or with status {@code 400 (Bad Request)} if the classeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the classeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/classes")
    public ResponseEntity<ClasseDTO> updateClasse(@RequestBody ClasseDTO classeDTO) throws URISyntaxException {
        log.debug("REST request to update Classe : {}", classeDTO);
        if (classeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClasseDTO result = classeService.save(classeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /classes} : get all the classes.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classes in body.
     */
    @GetMapping("/classes")
    public ResponseEntity<List<ClasseDTO>> getAllClasses(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Classes");
        Page<ClasseDTO> page;
        if (eagerload) {
            page = classeService.findAllWithEagerRelationships(pageable);
        } else {
            page = classeService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /classes/:id} : get the "id" classe.
     *
     * @param id the id of the classeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/classes/{id}")
    public ResponseEntity<ClasseDTO> getClasse(@PathVariable Long id) {
        log.debug("REST request to get Classe : {}", id);
        Optional<ClasseDTO> classeDTO = classeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(classeDTO);
    }

    /**
     * {@code DELETE  /classes/:id} : delete the "id" classe.
     *
     * @param id the id of the classeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        log.debug("REST request to delete Classe : {}", id);
        classeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
