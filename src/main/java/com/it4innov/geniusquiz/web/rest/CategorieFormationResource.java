package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.service.CategorieFormationService;
import com.it4innov.geniusquiz.web.rest.errors.BadRequestAlertException;
import com.it4innov.geniusquiz.service.dto.CategorieFormationDTO;

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
 * REST controller for managing {@link com.it4innov.geniusquiz.domain.CategorieFormation}.
 */
@RestController
@RequestMapping("/api")
public class CategorieFormationResource {

    private final Logger log = LoggerFactory.getLogger(CategorieFormationResource.class);

    private static final String ENTITY_NAME = "categorieFormation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategorieFormationService categorieFormationService;

    public CategorieFormationResource(CategorieFormationService categorieFormationService) {
        this.categorieFormationService = categorieFormationService;
    }

    /**
     * {@code POST  /categorie-formations} : Create a new categorieFormation.
     *
     * @param categorieFormationDTO the categorieFormationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categorieFormationDTO, or with status {@code 400 (Bad Request)} if the categorieFormation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categorie-formations")
    public ResponseEntity<CategorieFormationDTO> createCategorieFormation(@RequestBody CategorieFormationDTO categorieFormationDTO) throws URISyntaxException {
        log.debug("REST request to save CategorieFormation : {}", categorieFormationDTO);
        if (categorieFormationDTO.getId() != null) {
            throw new BadRequestAlertException("A new categorieFormation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategorieFormationDTO result = categorieFormationService.save(categorieFormationDTO);
        return ResponseEntity.created(new URI("/api/categorie-formations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categorie-formations} : Updates an existing categorieFormation.
     *
     * @param categorieFormationDTO the categorieFormationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categorieFormationDTO,
     * or with status {@code 400 (Bad Request)} if the categorieFormationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categorieFormationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categorie-formations")
    public ResponseEntity<CategorieFormationDTO> updateCategorieFormation(@RequestBody CategorieFormationDTO categorieFormationDTO) throws URISyntaxException {
        log.debug("REST request to update CategorieFormation : {}", categorieFormationDTO);
        if (categorieFormationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategorieFormationDTO result = categorieFormationService.save(categorieFormationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categorieFormationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /categorie-formations} : get all the categorieFormations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categorieFormations in body.
     */
    @GetMapping("/categorie-formations")
    public ResponseEntity<List<CategorieFormationDTO>> getAllCategorieFormations(Pageable pageable) {
        log.debug("REST request to get a page of CategorieFormations");
        Page<CategorieFormationDTO> page = categorieFormationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /categorie-formations/:id} : get the "id" categorieFormation.
     *
     * @param id the id of the categorieFormationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categorieFormationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categorie-formations/{id}")
    public ResponseEntity<CategorieFormationDTO> getCategorieFormation(@PathVariable Long id) {
        log.debug("REST request to get CategorieFormation : {}", id);
        Optional<CategorieFormationDTO> categorieFormationDTO = categorieFormationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categorieFormationDTO);
    }

    /**
     * {@code DELETE  /categorie-formations/:id} : delete the "id" categorieFormation.
     *
     * @param id the id of the categorieFormationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categorie-formations/{id}")
    public ResponseEntity<Void> deleteCategorieFormation(@PathVariable Long id) {
        log.debug("REST request to delete CategorieFormation : {}", id);
        categorieFormationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
