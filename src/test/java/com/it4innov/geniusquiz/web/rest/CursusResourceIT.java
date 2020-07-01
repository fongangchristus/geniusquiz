package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.GeniusquizApp;
import com.it4innov.geniusquiz.domain.Cursus;
import com.it4innov.geniusquiz.repository.CursusRepository;
import com.it4innov.geniusquiz.service.CursusService;
import com.it4innov.geniusquiz.service.dto.CursusDTO;
import com.it4innov.geniusquiz.service.mapper.CursusMapper;

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
 * Integration tests for the {@link CursusResource} REST controller.
 */
@SpringBootTest(classes = GeniusquizApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CursusResourceIT {

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_COUVERTURE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private CursusRepository cursusRepository;

    @Autowired
    private CursusMapper cursusMapper;

    @Autowired
    private CursusService cursusService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCursusMockMvc;

    private Cursus cursus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cursus createEntity(EntityManager em) {
        Cursus cursus = new Cursus()
            .libele(DEFAULT_LIBELE)
            .description(DEFAULT_DESCRIPTION)
            .imageCouverture(DEFAULT_IMAGE_COUVERTURE)
            .code(DEFAULT_CODE);
        return cursus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cursus createUpdatedEntity(EntityManager em) {
        Cursus cursus = new Cursus()
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .code(UPDATED_CODE);
        return cursus;
    }

    @BeforeEach
    public void initTest() {
        cursus = createEntity(em);
    }

    @Test
    @Transactional
    public void createCursus() throws Exception {
        int databaseSizeBeforeCreate = cursusRepository.findAll().size();
        // Create the Cursus
        CursusDTO cursusDTO = cursusMapper.toDto(cursus);
        restCursusMockMvc.perform(post("/api/cursuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cursusDTO)))
            .andExpect(status().isCreated());

        // Validate the Cursus in the database
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeCreate + 1);
        Cursus testCursus = cursusList.get(cursusList.size() - 1);
        assertThat(testCursus.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testCursus.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCursus.getImageCouverture()).isEqualTo(DEFAULT_IMAGE_COUVERTURE);
        assertThat(testCursus.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createCursusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cursusRepository.findAll().size();

        // Create the Cursus with an existing ID
        cursus.setId(1L);
        CursusDTO cursusDTO = cursusMapper.toDto(cursus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCursusMockMvc.perform(post("/api/cursuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cursusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cursus in the database
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCursuses() throws Exception {
        // Initialize the database
        cursusRepository.saveAndFlush(cursus);

        // Get all the cursusList
        restCursusMockMvc.perform(get("/api/cursuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cursus.getId().intValue())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageCouverture").value(hasItem(DEFAULT_IMAGE_COUVERTURE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getCursus() throws Exception {
        // Initialize the database
        cursusRepository.saveAndFlush(cursus);

        // Get the cursus
        restCursusMockMvc.perform(get("/api/cursuses/{id}", cursus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cursus.getId().intValue()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imageCouverture").value(DEFAULT_IMAGE_COUVERTURE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingCursus() throws Exception {
        // Get the cursus
        restCursusMockMvc.perform(get("/api/cursuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCursus() throws Exception {
        // Initialize the database
        cursusRepository.saveAndFlush(cursus);

        int databaseSizeBeforeUpdate = cursusRepository.findAll().size();

        // Update the cursus
        Cursus updatedCursus = cursusRepository.findById(cursus.getId()).get();
        // Disconnect from session so that the updates on updatedCursus are not directly saved in db
        em.detach(updatedCursus);
        updatedCursus
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .code(UPDATED_CODE);
        CursusDTO cursusDTO = cursusMapper.toDto(updatedCursus);

        restCursusMockMvc.perform(put("/api/cursuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cursusDTO)))
            .andExpect(status().isOk());

        // Validate the Cursus in the database
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeUpdate);
        Cursus testCursus = cursusList.get(cursusList.size() - 1);
        assertThat(testCursus.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testCursus.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCursus.getImageCouverture()).isEqualTo(UPDATED_IMAGE_COUVERTURE);
        assertThat(testCursus.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingCursus() throws Exception {
        int databaseSizeBeforeUpdate = cursusRepository.findAll().size();

        // Create the Cursus
        CursusDTO cursusDTO = cursusMapper.toDto(cursus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCursusMockMvc.perform(put("/api/cursuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cursusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cursus in the database
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCursus() throws Exception {
        // Initialize the database
        cursusRepository.saveAndFlush(cursus);

        int databaseSizeBeforeDelete = cursusRepository.findAll().size();

        // Delete the cursus
        restCursusMockMvc.perform(delete("/api/cursuses/{id}", cursus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cursus> cursusList = cursusRepository.findAll();
        assertThat(cursusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
