package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.GeniusquizApp;
import com.it4innov.geniusquiz.domain.CategorieFormation;
import com.it4innov.geniusquiz.repository.CategorieFormationRepository;
import com.it4innov.geniusquiz.service.CategorieFormationService;
import com.it4innov.geniusquiz.service.dto.CategorieFormationDTO;
import com.it4innov.geniusquiz.service.mapper.CategorieFormationMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CategorieFormationResource} REST controller.
 */
@SpringBootTest(classes = GeniusquizApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CategorieFormationResourceIT {

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_COUVERTURE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private CategorieFormationRepository categorieFormationRepository;

    @Autowired
    private CategorieFormationMapper categorieFormationMapper;

    @Autowired
    private CategorieFormationService categorieFormationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCategorieFormationMockMvc;

    private CategorieFormation categorieFormation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieFormation createEntity(EntityManager em) {
        CategorieFormation categorieFormation = new CategorieFormation()
            .libele(DEFAULT_LIBELE)
            .description(DEFAULT_DESCRIPTION)
            .imageCouverture(DEFAULT_IMAGE_COUVERTURE)
            .code(DEFAULT_CODE);
        return categorieFormation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CategorieFormation createUpdatedEntity(EntityManager em) {
        CategorieFormation categorieFormation = new CategorieFormation()
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .code(UPDATED_CODE);
        return categorieFormation;
    }

    @BeforeEach
    public void initTest() {
        categorieFormation = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategorieFormation() throws Exception {
        int databaseSizeBeforeCreate = categorieFormationRepository.findAll().size();
        // Create the CategorieFormation
        CategorieFormationDTO categorieFormationDTO = categorieFormationMapper.toDto(categorieFormation);
        restCategorieFormationMockMvc.perform(post("/api/categorie-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieFormationDTO)))
            .andExpect(status().isCreated());

        // Validate the CategorieFormation in the database
        List<CategorieFormation> categorieFormationList = categorieFormationRepository.findAll();
        assertThat(categorieFormationList).hasSize(databaseSizeBeforeCreate + 1);
        CategorieFormation testCategorieFormation = categorieFormationList.get(categorieFormationList.size() - 1);
        assertThat(testCategorieFormation.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testCategorieFormation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCategorieFormation.getImageCouverture()).isEqualTo(DEFAULT_IMAGE_COUVERTURE);
        assertThat(testCategorieFormation.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createCategorieFormationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = categorieFormationRepository.findAll().size();

        // Create the CategorieFormation with an existing ID
        categorieFormation.setId(1L);
        CategorieFormationDTO categorieFormationDTO = categorieFormationMapper.toDto(categorieFormation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategorieFormationMockMvc.perform(post("/api/categorie-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieFormationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieFormation in the database
        List<CategorieFormation> categorieFormationList = categorieFormationRepository.findAll();
        assertThat(categorieFormationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCategorieFormations() throws Exception {
        // Initialize the database
        categorieFormationRepository.saveAndFlush(categorieFormation);

        // Get all the categorieFormationList
        restCategorieFormationMockMvc.perform(get("/api/categorie-formations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(categorieFormation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageCouverture").value(hasItem(DEFAULT_IMAGE_COUVERTURE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getCategorieFormation() throws Exception {
        // Initialize the database
        categorieFormationRepository.saveAndFlush(categorieFormation);

        // Get the categorieFormation
        restCategorieFormationMockMvc.perform(get("/api/categorie-formations/{id}", categorieFormation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(categorieFormation.getId().intValue()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imageCouverture").value(DEFAULT_IMAGE_COUVERTURE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingCategorieFormation() throws Exception {
        // Get the categorieFormation
        restCategorieFormationMockMvc.perform(get("/api/categorie-formations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategorieFormation() throws Exception {
        // Initialize the database
        categorieFormationRepository.saveAndFlush(categorieFormation);

        int databaseSizeBeforeUpdate = categorieFormationRepository.findAll().size();

        // Update the categorieFormation
        CategorieFormation updatedCategorieFormation = categorieFormationRepository.findById(categorieFormation.getId()).get();
        // Disconnect from session so that the updates on updatedCategorieFormation are not directly saved in db
        em.detach(updatedCategorieFormation);
        updatedCategorieFormation
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .code(UPDATED_CODE);
        CategorieFormationDTO categorieFormationDTO = categorieFormationMapper.toDto(updatedCategorieFormation);

        restCategorieFormationMockMvc.perform(put("/api/categorie-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieFormationDTO)))
            .andExpect(status().isOk());

        // Validate the CategorieFormation in the database
        List<CategorieFormation> categorieFormationList = categorieFormationRepository.findAll();
        assertThat(categorieFormationList).hasSize(databaseSizeBeforeUpdate);
        CategorieFormation testCategorieFormation = categorieFormationList.get(categorieFormationList.size() - 1);
        assertThat(testCategorieFormation.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testCategorieFormation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCategorieFormation.getImageCouverture()).isEqualTo(UPDATED_IMAGE_COUVERTURE);
        assertThat(testCategorieFormation.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingCategorieFormation() throws Exception {
        int databaseSizeBeforeUpdate = categorieFormationRepository.findAll().size();

        // Create the CategorieFormation
        CategorieFormationDTO categorieFormationDTO = categorieFormationMapper.toDto(categorieFormation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategorieFormationMockMvc.perform(put("/api/categorie-formations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(categorieFormationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CategorieFormation in the database
        List<CategorieFormation> categorieFormationList = categorieFormationRepository.findAll();
        assertThat(categorieFormationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategorieFormation() throws Exception {
        // Initialize the database
        categorieFormationRepository.saveAndFlush(categorieFormation);

        int databaseSizeBeforeDelete = categorieFormationRepository.findAll().size();

        // Delete the categorieFormation
        restCategorieFormationMockMvc.perform(delete("/api/categorie-formations/{id}", categorieFormation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CategorieFormation> categorieFormationList = categorieFormationRepository.findAll();
        assertThat(categorieFormationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
