package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.GeniusquizApp;
import com.it4innov.geniusquiz.domain.Classe;
import com.it4innov.geniusquiz.repository.ClasseRepository;
import com.it4innov.geniusquiz.service.ClasseService;
import com.it4innov.geniusquiz.service.dto.ClasseDTO;
import com.it4innov.geniusquiz.service.mapper.ClasseMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ClasseResource} REST controller.
 */
@SpringBootTest(classes = GeniusquizApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClasseResourceIT {

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_COUVERTURE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private ClasseRepository classeRepository;

    @Mock
    private ClasseRepository classeRepositoryMock;

    @Autowired
    private ClasseMapper classeMapper;

    @Mock
    private ClasseService classeServiceMock;

    @Autowired
    private ClasseService classeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClasseMockMvc;

    private Classe classe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classe createEntity(EntityManager em) {
        Classe classe = new Classe()
            .libele(DEFAULT_LIBELE)
            .description(DEFAULT_DESCRIPTION)
            .imageCouverture(DEFAULT_IMAGE_COUVERTURE)
            .code(DEFAULT_CODE);
        return classe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classe createUpdatedEntity(EntityManager em) {
        Classe classe = new Classe()
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .code(UPDATED_CODE);
        return classe;
    }

    @BeforeEach
    public void initTest() {
        classe = createEntity(em);
    }

    @Test
    @Transactional
    public void createClasse() throws Exception {
        int databaseSizeBeforeCreate = classeRepository.findAll().size();
        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);
        restClasseMockMvc.perform(post("/api/classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classeDTO)))
            .andExpect(status().isCreated());

        // Validate the Classe in the database
        List<Classe> classeList = classeRepository.findAll();
        assertThat(classeList).hasSize(databaseSizeBeforeCreate + 1);
        Classe testClasse = classeList.get(classeList.size() - 1);
        assertThat(testClasse.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testClasse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testClasse.getImageCouverture()).isEqualTo(DEFAULT_IMAGE_COUVERTURE);
        assertThat(testClasse.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createClasseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = classeRepository.findAll().size();

        // Create the Classe with an existing ID
        classe.setId(1L);
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClasseMockMvc.perform(post("/api/classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Classe in the database
        List<Classe> classeList = classeRepository.findAll();
        assertThat(classeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllClasses() throws Exception {
        // Initialize the database
        classeRepository.saveAndFlush(classe);

        // Get all the classeList
        restClasseMockMvc.perform(get("/api/classes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(classe.getId().intValue())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageCouverture").value(hasItem(DEFAULT_IMAGE_COUVERTURE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllClassesWithEagerRelationshipsIsEnabled() throws Exception {
        when(classeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restClasseMockMvc.perform(get("/api/classes?eagerload=true"))
            .andExpect(status().isOk());

        verify(classeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllClassesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(classeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restClasseMockMvc.perform(get("/api/classes?eagerload=true"))
            .andExpect(status().isOk());

        verify(classeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getClasse() throws Exception {
        // Initialize the database
        classeRepository.saveAndFlush(classe);

        // Get the classe
        restClasseMockMvc.perform(get("/api/classes/{id}", classe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(classe.getId().intValue()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imageCouverture").value(DEFAULT_IMAGE_COUVERTURE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingClasse() throws Exception {
        // Get the classe
        restClasseMockMvc.perform(get("/api/classes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClasse() throws Exception {
        // Initialize the database
        classeRepository.saveAndFlush(classe);

        int databaseSizeBeforeUpdate = classeRepository.findAll().size();

        // Update the classe
        Classe updatedClasse = classeRepository.findById(classe.getId()).get();
        // Disconnect from session so that the updates on updatedClasse are not directly saved in db
        em.detach(updatedClasse);
        updatedClasse
            .libele(UPDATED_LIBELE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .code(UPDATED_CODE);
        ClasseDTO classeDTO = classeMapper.toDto(updatedClasse);

        restClasseMockMvc.perform(put("/api/classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classeDTO)))
            .andExpect(status().isOk());

        // Validate the Classe in the database
        List<Classe> classeList = classeRepository.findAll();
        assertThat(classeList).hasSize(databaseSizeBeforeUpdate);
        Classe testClasse = classeList.get(classeList.size() - 1);
        assertThat(testClasse.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testClasse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testClasse.getImageCouverture()).isEqualTo(UPDATED_IMAGE_COUVERTURE);
        assertThat(testClasse.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingClasse() throws Exception {
        int databaseSizeBeforeUpdate = classeRepository.findAll().size();

        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClasseMockMvc.perform(put("/api/classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Classe in the database
        List<Classe> classeList = classeRepository.findAll();
        assertThat(classeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClasse() throws Exception {
        // Initialize the database
        classeRepository.saveAndFlush(classe);

        int databaseSizeBeforeDelete = classeRepository.findAll().size();

        // Delete the classe
        restClasseMockMvc.perform(delete("/api/classes/{id}", classe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Classe> classeList = classeRepository.findAll();
        assertThat(classeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
