package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.GeniusquizApp;
import com.it4innov.geniusquiz.domain.EventuelReponse;
import com.it4innov.geniusquiz.repository.EventuelReponseRepository;
import com.it4innov.geniusquiz.service.EventuelReponseService;
import com.it4innov.geniusquiz.service.dto.EventuelReponseDTO;
import com.it4innov.geniusquiz.service.mapper.EventuelReponseMapper;

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
 * Integration tests for the {@link EventuelReponseResource} REST controller.
 */
@SpringBootTest(classes = GeniusquizApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EventuelReponseResourceIT {

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_COUVERTURE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CORRECT_ANSWER = false;
    private static final Boolean UPDATED_CORRECT_ANSWER = true;

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    @Autowired
    private EventuelReponseRepository eventuelReponseRepository;

    @Autowired
    private EventuelReponseMapper eventuelReponseMapper;

    @Autowired
    private EventuelReponseService eventuelReponseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEventuelReponseMockMvc;

    private EventuelReponse eventuelReponse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventuelReponse createEntity(EntityManager em) {
        EventuelReponse eventuelReponse = new EventuelReponse()
            .libele(DEFAULT_LIBELE)
            .code(DEFAULT_CODE)
            .imageCouverture(DEFAULT_IMAGE_COUVERTURE)
            .description(DEFAULT_DESCRIPTION)
            .correctAnswer(DEFAULT_CORRECT_ANSWER)
            .point(DEFAULT_POINT);
        return eventuelReponse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EventuelReponse createUpdatedEntity(EntityManager em) {
        EventuelReponse eventuelReponse = new EventuelReponse()
            .libele(UPDATED_LIBELE)
            .code(UPDATED_CODE)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .description(UPDATED_DESCRIPTION)
            .correctAnswer(UPDATED_CORRECT_ANSWER)
            .point(UPDATED_POINT);
        return eventuelReponse;
    }

    @BeforeEach
    public void initTest() {
        eventuelReponse = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventuelReponse() throws Exception {
        int databaseSizeBeforeCreate = eventuelReponseRepository.findAll().size();
        // Create the EventuelReponse
        EventuelReponseDTO eventuelReponseDTO = eventuelReponseMapper.toDto(eventuelReponse);
        restEventuelReponseMockMvc.perform(post("/api/eventuel-reponses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventuelReponseDTO)))
            .andExpect(status().isCreated());

        // Validate the EventuelReponse in the database
        List<EventuelReponse> eventuelReponseList = eventuelReponseRepository.findAll();
        assertThat(eventuelReponseList).hasSize(databaseSizeBeforeCreate + 1);
        EventuelReponse testEventuelReponse = eventuelReponseList.get(eventuelReponseList.size() - 1);
        assertThat(testEventuelReponse.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testEventuelReponse.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testEventuelReponse.getImageCouverture()).isEqualTo(DEFAULT_IMAGE_COUVERTURE);
        assertThat(testEventuelReponse.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testEventuelReponse.isCorrectAnswer()).isEqualTo(DEFAULT_CORRECT_ANSWER);
        assertThat(testEventuelReponse.getPoint()).isEqualTo(DEFAULT_POINT);
    }

    @Test
    @Transactional
    public void createEventuelReponseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventuelReponseRepository.findAll().size();

        // Create the EventuelReponse with an existing ID
        eventuelReponse.setId(1L);
        EventuelReponseDTO eventuelReponseDTO = eventuelReponseMapper.toDto(eventuelReponse);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventuelReponseMockMvc.perform(post("/api/eventuel-reponses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventuelReponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventuelReponse in the database
        List<EventuelReponse> eventuelReponseList = eventuelReponseRepository.findAll();
        assertThat(eventuelReponseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibeleIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventuelReponseRepository.findAll().size();
        // set the field null
        eventuelReponse.setLibele(null);

        // Create the EventuelReponse, which fails.
        EventuelReponseDTO eventuelReponseDTO = eventuelReponseMapper.toDto(eventuelReponse);


        restEventuelReponseMockMvc.perform(post("/api/eventuel-reponses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventuelReponseDTO)))
            .andExpect(status().isBadRequest());

        List<EventuelReponse> eventuelReponseList = eventuelReponseRepository.findAll();
        assertThat(eventuelReponseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEventuelReponses() throws Exception {
        // Initialize the database
        eventuelReponseRepository.saveAndFlush(eventuelReponse);

        // Get all the eventuelReponseList
        restEventuelReponseMockMvc.perform(get("/api/eventuel-reponses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventuelReponse.getId().intValue())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].imageCouverture").value(hasItem(DEFAULT_IMAGE_COUVERTURE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].correctAnswer").value(hasItem(DEFAULT_CORRECT_ANSWER.booleanValue())))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)));
    }
    
    @Test
    @Transactional
    public void getEventuelReponse() throws Exception {
        // Initialize the database
        eventuelReponseRepository.saveAndFlush(eventuelReponse);

        // Get the eventuelReponse
        restEventuelReponseMockMvc.perform(get("/api/eventuel-reponses/{id}", eventuelReponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eventuelReponse.getId().intValue()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.imageCouverture").value(DEFAULT_IMAGE_COUVERTURE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.correctAnswer").value(DEFAULT_CORRECT_ANSWER.booleanValue()))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT));
    }
    @Test
    @Transactional
    public void getNonExistingEventuelReponse() throws Exception {
        // Get the eventuelReponse
        restEventuelReponseMockMvc.perform(get("/api/eventuel-reponses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventuelReponse() throws Exception {
        // Initialize the database
        eventuelReponseRepository.saveAndFlush(eventuelReponse);

        int databaseSizeBeforeUpdate = eventuelReponseRepository.findAll().size();

        // Update the eventuelReponse
        EventuelReponse updatedEventuelReponse = eventuelReponseRepository.findById(eventuelReponse.getId()).get();
        // Disconnect from session so that the updates on updatedEventuelReponse are not directly saved in db
        em.detach(updatedEventuelReponse);
        updatedEventuelReponse
            .libele(UPDATED_LIBELE)
            .code(UPDATED_CODE)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .description(UPDATED_DESCRIPTION)
            .correctAnswer(UPDATED_CORRECT_ANSWER)
            .point(UPDATED_POINT);
        EventuelReponseDTO eventuelReponseDTO = eventuelReponseMapper.toDto(updatedEventuelReponse);

        restEventuelReponseMockMvc.perform(put("/api/eventuel-reponses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventuelReponseDTO)))
            .andExpect(status().isOk());

        // Validate the EventuelReponse in the database
        List<EventuelReponse> eventuelReponseList = eventuelReponseRepository.findAll();
        assertThat(eventuelReponseList).hasSize(databaseSizeBeforeUpdate);
        EventuelReponse testEventuelReponse = eventuelReponseList.get(eventuelReponseList.size() - 1);
        assertThat(testEventuelReponse.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testEventuelReponse.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testEventuelReponse.getImageCouverture()).isEqualTo(UPDATED_IMAGE_COUVERTURE);
        assertThat(testEventuelReponse.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testEventuelReponse.isCorrectAnswer()).isEqualTo(UPDATED_CORRECT_ANSWER);
        assertThat(testEventuelReponse.getPoint()).isEqualTo(UPDATED_POINT);
    }

    @Test
    @Transactional
    public void updateNonExistingEventuelReponse() throws Exception {
        int databaseSizeBeforeUpdate = eventuelReponseRepository.findAll().size();

        // Create the EventuelReponse
        EventuelReponseDTO eventuelReponseDTO = eventuelReponseMapper.toDto(eventuelReponse);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventuelReponseMockMvc.perform(put("/api/eventuel-reponses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(eventuelReponseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventuelReponse in the database
        List<EventuelReponse> eventuelReponseList = eventuelReponseRepository.findAll();
        assertThat(eventuelReponseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventuelReponse() throws Exception {
        // Initialize the database
        eventuelReponseRepository.saveAndFlush(eventuelReponse);

        int databaseSizeBeforeDelete = eventuelReponseRepository.findAll().size();

        // Delete the eventuelReponse
        restEventuelReponseMockMvc.perform(delete("/api/eventuel-reponses/{id}", eventuelReponse.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EventuelReponse> eventuelReponseList = eventuelReponseRepository.findAll();
        assertThat(eventuelReponseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
