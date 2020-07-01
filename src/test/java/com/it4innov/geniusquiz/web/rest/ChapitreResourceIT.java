package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.GeniusquizApp;
import com.it4innov.geniusquiz.domain.Chapitre;
import com.it4innov.geniusquiz.repository.ChapitreRepository;
import com.it4innov.geniusquiz.service.ChapitreService;
import com.it4innov.geniusquiz.service.dto.ChapitreDTO;
import com.it4innov.geniusquiz.service.mapper.ChapitreMapper;

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
 * Integration tests for the {@link ChapitreResource} REST controller.
 */
@SpringBootTest(classes = GeniusquizApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ChapitreResourceIT {

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_COUVERTURE = "BBBBBBBBBB";

    private static final String DEFAULT_FICHIER_COURS = "AAAAAAAAAA";
    private static final String UPDATED_FICHIER_COURS = "BBBBBBBBBB";

    @Autowired
    private ChapitreRepository chapitreRepository;

    @Autowired
    private ChapitreMapper chapitreMapper;

    @Autowired
    private ChapitreService chapitreService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChapitreMockMvc;

    private Chapitre chapitre;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chapitre createEntity(EntityManager em) {
        Chapitre chapitre = new Chapitre()
            .libele(DEFAULT_LIBELE)
            .description(DEFAULT_DESCRIPTION)
            .imageCouverture(DEFAULT_IMAGE_COUVERTURE)
            .fichierCours(DEFAULT_FICHIER_COURS);
        return chapitre;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chapitre createUpdatedEntity(EntityManager em) {
        Chapitre chapitre = new Chapitre()
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .fichierCours(UPDATED_FICHIER_COURS);
        return chapitre;
    }

    @BeforeEach
    public void initTest() {
        chapitre = createEntity(em);
    }

    @Test
    @Transactional
    public void createChapitre() throws Exception {
        int databaseSizeBeforeCreate = chapitreRepository.findAll().size();
        // Create the Chapitre
        ChapitreDTO chapitreDTO = chapitreMapper.toDto(chapitre);
        restChapitreMockMvc.perform(post("/api/chapitres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chapitreDTO)))
            .andExpect(status().isCreated());

        // Validate the Chapitre in the database
        List<Chapitre> chapitreList = chapitreRepository.findAll();
        assertThat(chapitreList).hasSize(databaseSizeBeforeCreate + 1);
        Chapitre testChapitre = chapitreList.get(chapitreList.size() - 1);
        assertThat(testChapitre.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testChapitre.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testChapitre.getImageCouverture()).isEqualTo(DEFAULT_IMAGE_COUVERTURE);
        assertThat(testChapitre.getFichierCours()).isEqualTo(DEFAULT_FICHIER_COURS);
    }

    @Test
    @Transactional
    public void createChapitreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chapitreRepository.findAll().size();

        // Create the Chapitre with an existing ID
        chapitre.setId(1L);
        ChapitreDTO chapitreDTO = chapitreMapper.toDto(chapitre);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChapitreMockMvc.perform(post("/api/chapitres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chapitreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chapitre in the database
        List<Chapitre> chapitreList = chapitreRepository.findAll();
        assertThat(chapitreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibeleIsRequired() throws Exception {
        int databaseSizeBeforeTest = chapitreRepository.findAll().size();
        // set the field null
        chapitre.setLibele(null);

        // Create the Chapitre, which fails.
        ChapitreDTO chapitreDTO = chapitreMapper.toDto(chapitre);


        restChapitreMockMvc.perform(post("/api/chapitres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chapitreDTO)))
            .andExpect(status().isBadRequest());

        List<Chapitre> chapitreList = chapitreRepository.findAll();
        assertThat(chapitreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChapitres() throws Exception {
        // Initialize the database
        chapitreRepository.saveAndFlush(chapitre);

        // Get all the chapitreList
        restChapitreMockMvc.perform(get("/api/chapitres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chapitre.getId().intValue())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageCouverture").value(hasItem(DEFAULT_IMAGE_COUVERTURE)))
            .andExpect(jsonPath("$.[*].fichierCours").value(hasItem(DEFAULT_FICHIER_COURS)));
    }
    
    @Test
    @Transactional
    public void getChapitre() throws Exception {
        // Initialize the database
        chapitreRepository.saveAndFlush(chapitre);

        // Get the chapitre
        restChapitreMockMvc.perform(get("/api/chapitres/{id}", chapitre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chapitre.getId().intValue()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imageCouverture").value(DEFAULT_IMAGE_COUVERTURE))
            .andExpect(jsonPath("$.fichierCours").value(DEFAULT_FICHIER_COURS));
    }
    @Test
    @Transactional
    public void getNonExistingChapitre() throws Exception {
        // Get the chapitre
        restChapitreMockMvc.perform(get("/api/chapitres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChapitre() throws Exception {
        // Initialize the database
        chapitreRepository.saveAndFlush(chapitre);

        int databaseSizeBeforeUpdate = chapitreRepository.findAll().size();

        // Update the chapitre
        Chapitre updatedChapitre = chapitreRepository.findById(chapitre.getId()).get();
        // Disconnect from session so that the updates on updatedChapitre are not directly saved in db
        em.detach(updatedChapitre);
        updatedChapitre
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .fichierCours(UPDATED_FICHIER_COURS);
        ChapitreDTO chapitreDTO = chapitreMapper.toDto(updatedChapitre);

        restChapitreMockMvc.perform(put("/api/chapitres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chapitreDTO)))
            .andExpect(status().isOk());

        // Validate the Chapitre in the database
        List<Chapitre> chapitreList = chapitreRepository.findAll();
        assertThat(chapitreList).hasSize(databaseSizeBeforeUpdate);
        Chapitre testChapitre = chapitreList.get(chapitreList.size() - 1);
        assertThat(testChapitre.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testChapitre.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testChapitre.getImageCouverture()).isEqualTo(UPDATED_IMAGE_COUVERTURE);
        assertThat(testChapitre.getFichierCours()).isEqualTo(UPDATED_FICHIER_COURS);
    }

    @Test
    @Transactional
    public void updateNonExistingChapitre() throws Exception {
        int databaseSizeBeforeUpdate = chapitreRepository.findAll().size();

        // Create the Chapitre
        ChapitreDTO chapitreDTO = chapitreMapper.toDto(chapitre);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChapitreMockMvc.perform(put("/api/chapitres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chapitreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chapitre in the database
        List<Chapitre> chapitreList = chapitreRepository.findAll();
        assertThat(chapitreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChapitre() throws Exception {
        // Initialize the database
        chapitreRepository.saveAndFlush(chapitre);

        int databaseSizeBeforeDelete = chapitreRepository.findAll().size();

        // Delete the chapitre
        restChapitreMockMvc.perform(delete("/api/chapitres/{id}", chapitre.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Chapitre> chapitreList = chapitreRepository.findAll();
        assertThat(chapitreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
