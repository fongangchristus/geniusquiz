package com.it4innov.geniusquiz.web.rest;

import com.it4innov.geniusquiz.GeniusquizApp;
import com.it4innov.geniusquiz.domain.ReponseUser;
import com.it4innov.geniusquiz.repository.ReponseUserRepository;
import com.it4innov.geniusquiz.service.ReponseUserService;
import com.it4innov.geniusquiz.service.dto.ReponseUserDTO;
import com.it4innov.geniusquiz.service.mapper.ReponseUserMapper;

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
 * Integration tests for the {@link ReponseUserResource} REST controller.
 */
@SpringBootTest(classes = GeniusquizApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReponseUserResourceIT {

    private static final Instant DEFAULT_DATE_REPONSE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_REPONSE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LIBELE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELE = "BBBBBBBBBB";

    @Autowired
    private ReponseUserRepository reponseUserRepository;

    @Mock
    private ReponseUserRepository reponseUserRepositoryMock;

    @Autowired
    private ReponseUserMapper reponseUserMapper;

    @Mock
    private ReponseUserService reponseUserServiceMock;

    @Autowired
    private ReponseUserService reponseUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReponseUserMockMvc;

    private ReponseUser reponseUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReponseUser createEntity(EntityManager em) {
        ReponseUser reponseUser = new ReponseUser()
            .dateReponse(DEFAULT_DATE_REPONSE)
            .libele(DEFAULT_LIBELE);
        return reponseUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReponseUser createUpdatedEntity(EntityManager em) {
        ReponseUser reponseUser = new ReponseUser()
            .dateReponse(UPDATED_DATE_REPONSE)
            .libele(UPDATED_LIBELE);
        return reponseUser;
    }

    @BeforeEach
    public void initTest() {
        reponseUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createReponseUser() throws Exception {
        int databaseSizeBeforeCreate = reponseUserRepository.findAll().size();
        // Create the ReponseUser
        ReponseUserDTO reponseUserDTO = reponseUserMapper.toDto(reponseUser);
        restReponseUserMockMvc.perform(post("/api/reponse-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reponseUserDTO)))
            .andExpect(status().isCreated());

        // Validate the ReponseUser in the database
        List<ReponseUser> reponseUserList = reponseUserRepository.findAll();
        assertThat(reponseUserList).hasSize(databaseSizeBeforeCreate + 1);
        ReponseUser testReponseUser = reponseUserList.get(reponseUserList.size() - 1);
        assertThat(testReponseUser.getDateReponse()).isEqualTo(DEFAULT_DATE_REPONSE);
        assertThat(testReponseUser.getLibele()).isEqualTo(DEFAULT_LIBELE);
    }

    @Test
    @Transactional
    public void createReponseUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reponseUserRepository.findAll().size();

        // Create the ReponseUser with an existing ID
        reponseUser.setId(1L);
        ReponseUserDTO reponseUserDTO = reponseUserMapper.toDto(reponseUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReponseUserMockMvc.perform(post("/api/reponse-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reponseUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReponseUser in the database
        List<ReponseUser> reponseUserList = reponseUserRepository.findAll();
        assertThat(reponseUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReponseUsers() throws Exception {
        // Initialize the database
        reponseUserRepository.saveAndFlush(reponseUser);

        // Get all the reponseUserList
        restReponseUserMockMvc.perform(get("/api/reponse-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reponseUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateReponse").value(hasItem(DEFAULT_DATE_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].libele").value(hasItem(DEFAULT_LIBELE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllReponseUsersWithEagerRelationshipsIsEnabled() throws Exception {
        when(reponseUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restReponseUserMockMvc.perform(get("/api/reponse-users?eagerload=true"))
            .andExpect(status().isOk());

        verify(reponseUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllReponseUsersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(reponseUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restReponseUserMockMvc.perform(get("/api/reponse-users?eagerload=true"))
            .andExpect(status().isOk());

        verify(reponseUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getReponseUser() throws Exception {
        // Initialize the database
        reponseUserRepository.saveAndFlush(reponseUser);

        // Get the reponseUser
        restReponseUserMockMvc.perform(get("/api/reponse-users/{id}", reponseUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reponseUser.getId().intValue()))
            .andExpect(jsonPath("$.dateReponse").value(DEFAULT_DATE_REPONSE.toString()))
            .andExpect(jsonPath("$.libele").value(DEFAULT_LIBELE));
    }
    @Test
    @Transactional
    public void getNonExistingReponseUser() throws Exception {
        // Get the reponseUser
        restReponseUserMockMvc.perform(get("/api/reponse-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReponseUser() throws Exception {
        // Initialize the database
        reponseUserRepository.saveAndFlush(reponseUser);

        int databaseSizeBeforeUpdate = reponseUserRepository.findAll().size();

        // Update the reponseUser
        ReponseUser updatedReponseUser = reponseUserRepository.findById(reponseUser.getId()).get();
        // Disconnect from session so that the updates on updatedReponseUser are not directly saved in db
        em.detach(updatedReponseUser);
        updatedReponseUser
            .dateReponse(UPDATED_DATE_REPONSE)
            .libele(UPDATED_LIBELE);
        ReponseUserDTO reponseUserDTO = reponseUserMapper.toDto(updatedReponseUser);

        restReponseUserMockMvc.perform(put("/api/reponse-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reponseUserDTO)))
            .andExpect(status().isOk());

        // Validate the ReponseUser in the database
        List<ReponseUser> reponseUserList = reponseUserRepository.findAll();
        assertThat(reponseUserList).hasSize(databaseSizeBeforeUpdate);
        ReponseUser testReponseUser = reponseUserList.get(reponseUserList.size() - 1);
        assertThat(testReponseUser.getDateReponse()).isEqualTo(UPDATED_DATE_REPONSE);
        assertThat(testReponseUser.getLibele()).isEqualTo(UPDATED_LIBELE);
    }

    @Test
    @Transactional
    public void updateNonExistingReponseUser() throws Exception {
        int databaseSizeBeforeUpdate = reponseUserRepository.findAll().size();

        // Create the ReponseUser
        ReponseUserDTO reponseUserDTO = reponseUserMapper.toDto(reponseUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReponseUserMockMvc.perform(put("/api/reponse-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reponseUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReponseUser in the database
        List<ReponseUser> reponseUserList = reponseUserRepository.findAll();
        assertThat(reponseUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReponseUser() throws Exception {
        // Initialize the database
        reponseUserRepository.saveAndFlush(reponseUser);

        int databaseSizeBeforeDelete = reponseUserRepository.findAll().size();

        // Delete the reponseUser
        restReponseUserMockMvc.perform(delete("/api/reponse-users/{id}", reponseUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReponseUser> reponseUserList = reponseUserRepository.findAll();
        assertThat(reponseUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
