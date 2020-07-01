package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.GeniusquizApp;
import com.it4innov.geniusquiz.domain.Quiz;
import com.it4innov.geniusquiz.domain.Question;
import com.it4innov.geniusquiz.repository.QuizRepository;
import com.it4innov.geniusquiz.service.QuizService;
import com.it4innov.geniusquiz.service.dto.QuizDTO;
import com.it4innov.geniusquiz.service.mapper.QuizMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QuizResource} REST controller.
 */
@SpringBootTest(classes = GeniusquizApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class QuizResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_MATIERE = 1L;
    private static final Long UPDATED_ID_MATIERE = 2L;

    private static final String DEFAULT_ENTETE = "AAAAAAAAAA";
    private static final String UPDATED_ENTETE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_COUVERTURE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_COUVERTURE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DUREE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DUREE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_NBR_QUESTION = 1;
    private static final Integer UPDATED_NBR_QUESTION = 2;

    private static final Instant DEFAULT_DATE_PUBLICATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_PUBLICATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EXPIRATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EXPIRATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private QuizRepository quizRepository;

    @Mock
    private QuizRepository quizRepositoryMock;

    @Autowired
    private QuizMapper quizMapper;

    @Mock
    private QuizService quizServiceMock;

    @Autowired
    private QuizService quizService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuizMockMvc;

    private Quiz quiz;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quiz createEntity(EntityManager em) {
        Quiz quiz = new Quiz()
            .type(DEFAULT_TYPE)
            .idMatiere(DEFAULT_ID_MATIERE)
            .entete(DEFAULT_ENTETE)
            .description(DEFAULT_DESCRIPTION)
            .libele(DEFAULT_LIBELE)
            .imageCouverture(DEFAULT_IMAGE_COUVERTURE)
            .duree(DEFAULT_DUREE)
            .nbrQuestion(DEFAULT_NBR_QUESTION)
            .datePublication(DEFAULT_DATE_PUBLICATION)
            .dateExpiration(DEFAULT_DATE_EXPIRATION);
        // Add required entity
        Question question;
        if (TestUtil.findAll(em, Question.class).isEmpty()) {
            question = QuestionResourceIT.createEntity(em);
            em.persist(question);
            em.flush();
        } else {
            question = TestUtil.findAll(em, Question.class).get(0);
        }
        quiz.getQuestions().add(question);
        return quiz;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quiz createUpdatedEntity(EntityManager em) {
        Quiz quiz = new Quiz()
            .type(UPDATED_TYPE)
            .idMatiere(UPDATED_ID_MATIERE)
            .entete(UPDATED_ENTETE)
            .description(UPDATED_DESCRIPTION)
            .libele(UPDATED_LIBELE)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .duree(UPDATED_DUREE)
            .nbrQuestion(UPDATED_NBR_QUESTION)
            .datePublication(UPDATED_DATE_PUBLICATION)
            .dateExpiration(UPDATED_DATE_EXPIRATION);
        // Add required entity
        Question question;
        if (TestUtil.findAll(em, Question.class).isEmpty()) {
            question = QuestionResourceIT.createUpdatedEntity(em);
            em.persist(question);
            em.flush();
        } else {
            question = TestUtil.findAll(em, Question.class).get(0);
        }
        quiz.getQuestions().add(question);
        return quiz;
    }

    @BeforeEach
    public void initTest() {
        quiz = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuiz() throws Exception {
        int databaseSizeBeforeCreate = quizRepository.findAll().size();
        // Create the Quiz
        QuizDTO quizDTO = quizMapper.toDto(quiz);
        restQuizMockMvc.perform(post("/api/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quizDTO)))
            .andExpect(status().isCreated());

        // Validate the Quiz in the database
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeCreate + 1);
        Quiz testQuiz = quizList.get(quizList.size() - 1);
        assertThat(testQuiz.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testQuiz.getIdMatiere()).isEqualTo(DEFAULT_ID_MATIERE);
        assertThat(testQuiz.getEntete()).isEqualTo(DEFAULT_ENTETE);
        assertThat(testQuiz.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testQuiz.getLibele()).isEqualTo(DEFAULT_LIBELE);
        assertThat(testQuiz.getImageCouverture()).isEqualTo(DEFAULT_IMAGE_COUVERTURE);
        assertThat(testQuiz.getDuree()).isEqualTo(DEFAULT_DUREE);
        assertThat(testQuiz.getNbrQuestion()).isEqualTo(DEFAULT_NBR_QUESTION);
        assertThat(testQuiz.getDatePublication()).isEqualTo(DEFAULT_DATE_PUBLICATION);
        assertThat(testQuiz.getDateExpiration()).isEqualTo(DEFAULT_DATE_EXPIRATION);
    }

    @Test
    @Transactional
    public void createQuizWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quizRepository.findAll().size();

        // Create the Quiz with an existing ID
        quiz.setId(1L);
        QuizDTO quizDTO = quizMapper.toDto(quiz);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuizMockMvc.perform(post("/api/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quizDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Quiz in the database
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibeleIsRequired() throws Exception {
        int databaseSizeBeforeTest = quizRepository.findAll().size();
        // set the field null
        quiz.setLibele(null);

        // Create the Quiz, which fails.
        QuizDTO quizDTO = quizMapper.toDto(quiz);


        restQuizMockMvc.perform(post("/api/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quizDTO)))
            .andExpect(status().isBadRequest());

        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuizzes() throws Exception {
        // Initialize the database
        quizRepository.saveAndFlush(quiz);

        // Get all the quizList
        restQuizMockMvc.perform(get("/api/quizzes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quiz.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].idMatiere").value(hasItem(DEFAULT_ID_MATIERE.intValue())))
            .andExpect(jsonPath("$.[*].entete").value(hasItem(DEFAULT_ENTETE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)))
            .andExpect(jsonPath("$.[*].imageCouverture").value(hasItem(DEFAULT_IMAGE_COUVERTURE)))
            .andExpect(jsonPath("$.[*].duree").value(hasItem(DEFAULT_DUREE.toString())))
            .andExpect(jsonPath("$.[*].nbrQuestion").value(hasItem(DEFAULT_NBR_QUESTION)))
            .andExpect(jsonPath("$.[*].datePublication").value(hasItem(DEFAULT_DATE_PUBLICATION.toString())))
            .andExpect(jsonPath("$.[*].dateExpiration").value(hasItem(DEFAULT_DATE_EXPIRATION.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllQuizzesWithEagerRelationshipsIsEnabled() throws Exception {
        when(quizServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restQuizMockMvc.perform(get("/api/quizzes?eagerload=true"))
            .andExpect(status().isOk());

        verify(quizServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllQuizzesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(quizServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restQuizMockMvc.perform(get("/api/quizzes?eagerload=true"))
            .andExpect(status().isOk());

        verify(quizServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getQuiz() throws Exception {
        // Initialize the database
        quizRepository.saveAndFlush(quiz);

        // Get the quiz
        restQuizMockMvc.perform(get("/api/quizzes/{id}", quiz.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(quiz.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.idMatiere").value(DEFAULT_ID_MATIERE.intValue()))
            .andExpect(jsonPath("$.entete").value(DEFAULT_ENTETE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE))
            .andExpect(jsonPath("$.imageCouverture").value(DEFAULT_IMAGE_COUVERTURE))
            .andExpect(jsonPath("$.duree").value(DEFAULT_DUREE.toString()))
            .andExpect(jsonPath("$.nbrQuestion").value(DEFAULT_NBR_QUESTION))
            .andExpect(jsonPath("$.datePublication").value(DEFAULT_DATE_PUBLICATION.toString()))
            .andExpect(jsonPath("$.dateExpiration").value(DEFAULT_DATE_EXPIRATION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingQuiz() throws Exception {
        // Get the quiz
        restQuizMockMvc.perform(get("/api/quizzes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuiz() throws Exception {
        // Initialize the database
        quizRepository.saveAndFlush(quiz);

        int databaseSizeBeforeUpdate = quizRepository.findAll().size();

        // Update the quiz
        Quiz updatedQuiz = quizRepository.findById(quiz.getId()).get();
        // Disconnect from session so that the updates on updatedQuiz are not directly saved in db
        em.detach(updatedQuiz);
        updatedQuiz
            .type(UPDATED_TYPE)
            .idMatiere(UPDATED_ID_MATIERE)
            .entete(UPDATED_ENTETE)
            .description(UPDATED_DESCRIPTION)
            .libele(UPDATED_LIBELE)
            .imageCouverture(UPDATED_IMAGE_COUVERTURE)
            .duree(UPDATED_DUREE)
            .nbrQuestion(UPDATED_NBR_QUESTION)
            .datePublication(UPDATED_DATE_PUBLICATION)
            .dateExpiration(UPDATED_DATE_EXPIRATION);
        QuizDTO quizDTO = quizMapper.toDto(updatedQuiz);

        restQuizMockMvc.perform(put("/api/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quizDTO)))
            .andExpect(status().isOk());

        // Validate the Quiz in the database
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeUpdate);
        Quiz testQuiz = quizList.get(quizList.size() - 1);
        assertThat(testQuiz.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testQuiz.getIdMatiere()).isEqualTo(UPDATED_ID_MATIERE);
        assertThat(testQuiz.getEntete()).isEqualTo(UPDATED_ENTETE);
        assertThat(testQuiz.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testQuiz.getLibele()).isEqualTo(UPDATED_LIBELE);
        assertThat(testQuiz.getImageCouverture()).isEqualTo(UPDATED_IMAGE_COUVERTURE);
        assertThat(testQuiz.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testQuiz.getNbrQuestion()).isEqualTo(UPDATED_NBR_QUESTION);
        assertThat(testQuiz.getDatePublication()).isEqualTo(UPDATED_DATE_PUBLICATION);
        assertThat(testQuiz.getDateExpiration()).isEqualTo(UPDATED_DATE_EXPIRATION);
    }

    @Test
    @Transactional
    public void updateNonExistingQuiz() throws Exception {
        int databaseSizeBeforeUpdate = quizRepository.findAll().size();

        // Create the Quiz
        QuizDTO quizDTO = quizMapper.toDto(quiz);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuizMockMvc.perform(put("/api/quizzes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(quizDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Quiz in the database
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuiz() throws Exception {
        // Initialize the database
        quizRepository.saveAndFlush(quiz);

        int databaseSizeBeforeDelete = quizRepository.findAll().size();

        // Delete the quiz
        restQuizMockMvc.perform(delete("/api/quizzes/{id}", quiz.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Quiz> quizList = quizRepository.findAll();
        assertThat(quizList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
