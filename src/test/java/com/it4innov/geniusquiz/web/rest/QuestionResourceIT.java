package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.GeniusquizApp;
import com.it4innov.geniusquiz.domain.Question;
import com.it4innov.geniusquiz.repository.QuestionRepository;
import com.it4innov.geniusquiz.service.QuestionService;
import com.it4innov.geniusquiz.service.dto.QuestionDTO;
import com.it4innov.geniusquiz.service.mapper.QuestionMapper;

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
 * Integration tests for the {@link QuestionResource} REST controller.
 */
@SpringBootTest(classes = GeniusquizApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class QuestionResourceIT {

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_CHAPITRE = 1L;
    private static final Long UPDATED_ID_CHAPITRE = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_COUVERTURE = "BBBBBBBBBB";

    private static final Integer DEFAULT_POINT = 1;
    private static final Integer UPDATED_POINT = 2;

    private static final Boolean DEFAULT_IS_ACTIF = false;
    private static final Boolean UPDATED_IS_ACTIF = true;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionMockMvc;

    private Question question;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createEntity(EntityManager em) {
        Question question = new Question()
            .libele(DEFAULT_LIBELE)
            .idChapitre(DEFAULT_ID_CHAPITRE)
            .description(DEFAULT_DESCRIPTION)
            .imageCouverture(DEFAULT_IMAGE_COUVERTURE)
            .point(DEFAULT_POINT)
            .isActif(DEFAULT_IS_ACTIF);
        return question;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createUpdatedEntity(EntityManager em) {
        Question question = new Question()
            .libele(UPDATED_LIBELE)
            .idChapitre(UPDATED_ID_CHAPITRE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .point(UPDATED_POINT)
            .isActif(UPDATED_IS_ACTIF);
        return question;
    }

    @BeforeEach
    public void initTest() {
        question = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuestion() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();
        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);
        restQuestionMockMvc.perform(post("/api/questions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isCreated());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate + 1);
        Question testQuestion = questionList.get(questionList.size() - 1);
        assertThat(testQuestion.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testQuestion.getIdChapitre()).isEqualTo(DEFAULT_ID_CHAPITRE);
        assertThat(testQuestion.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testQuestion.getImageCouverture()).isEqualTo(DEFAULT_IMAGE_COUVERTURE);
        assertThat(testQuestion.getPoint()).isEqualTo(DEFAULT_POINT);
        assertThat(testQuestion.isIsActif()).isEqualTo(DEFAULT_IS_ACTIF);
    }

    @Test
    @Transactional
    public void createQuestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();

        // Create the Question with an existing ID
        question.setId(1L);
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionMockMvc.perform(post("/api/questions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibeleIsRequired() throws Exception {
        int databaseSizeBeforeTest = questionRepository.findAll().size();
        // set the field null
        question.setLibele(null);

        // Create the Question, which fails.
        QuestionDTO questionDTO = questionMapper.toDto(question);


        restQuestionMockMvc.perform(post("/api/questions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isBadRequest());

        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuestions() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get all the questionList
        restQuestionMockMvc.perform(get("/api/questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(question.getId().intValue())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].idChapitre").value(hasItem(DEFAULT_ID_CHAPITRE.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].imageCouverture").value(hasItem(DEFAULT_IMAGE_COUVERTURE)))
            .andExpect(jsonPath("$.[*].point").value(hasItem(DEFAULT_POINT)))
            .andExpect(jsonPath("$.[*].isActif").value(hasItem(DEFAULT_IS_ACTIF.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get the question
        restQuestionMockMvc.perform(get("/api/questions/{id}", question.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(question.getId().intValue()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.idChapitre").value(DEFAULT_ID_CHAPITRE.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.imageCouverture").value(DEFAULT_IMAGE_COUVERTURE))
            .andExpect(jsonPath("$.point").value(DEFAULT_POINT))
            .andExpect(jsonPath("$.isActif").value(DEFAULT_IS_ACTIF.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingQuestion() throws Exception {
        // Get the question
        restQuestionMockMvc.perform(get("/api/questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Update the question
        Question updatedQuestion = questionRepository.findById(question.getId()).get();
        // Disconnect from session so that the updates on updatedQuestion are not directly saved in db
        em.detach(updatedQuestion);
        updatedQuestion
            .libele(UPDATED_LIBELE)
            .idChapitre(UPDATED_ID_CHAPITRE)
            .description(UPDATED_DESCRIPTION)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .point(UPDATED_POINT)
            .isActif(UPDATED_IS_ACTIF);
        QuestionDTO questionDTO = questionMapper.toDto(updatedQuestion);

        restQuestionMockMvc.perform(put("/api/questions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isOk());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
        Question testQuestion = questionList.get(questionList.size() - 1);
        assertThat(testQuestion.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testQuestion.getIdChapitre()).isEqualTo(UPDATED_ID_CHAPITRE);
        assertThat(testQuestion.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testQuestion.getImageCouverture()).isEqualTo(UPDATED_IMAGE_COUVERTURE);
        assertThat(testQuestion.getPoint()).isEqualTo(UPDATED_POINT);
        assertThat(testQuestion.isIsActif()).isEqualTo(UPDATED_IS_ACTIF);
    }

    @Test
    @Transactional
    public void updateNonExistingQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionMockMvc.perform(put("/api/questions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeDelete = questionRepository.findAll().size();

        // Delete the question
        restQuestionMockMvc.perform(delete("/api/questions/{id}", question.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
