package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Milestone;
import io.github.jhipster.application.repository.MilestoneRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MilestoneResource REST controller.
 *
 * @see MilestoneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class MilestoneResourceIntTest {

    @Autowired
    private MilestoneRepository milestoneRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMilestoneMockMvc;

    private Milestone milestone;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MilestoneResource milestoneResource = new MilestoneResource(milestoneRepository);
        this.restMilestoneMockMvc = MockMvcBuilders.standaloneSetup(milestoneResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Milestone createEntity(EntityManager em) {
        Milestone milestone = new Milestone();
        return milestone;
    }

    @Before
    public void initTest() {
        milestone = createEntity(em);
    }

    @Test
    @Transactional
    public void createMilestone() throws Exception {
        int databaseSizeBeforeCreate = milestoneRepository.findAll().size();

        // Create the Milestone
        restMilestoneMockMvc.perform(post("/api/milestones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(milestone)))
            .andExpect(status().isCreated());

        // Validate the Milestone in the database
        List<Milestone> milestoneList = milestoneRepository.findAll();
        assertThat(milestoneList).hasSize(databaseSizeBeforeCreate + 1);
        Milestone testMilestone = milestoneList.get(milestoneList.size() - 1);
    }

    @Test
    @Transactional
    public void createMilestoneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = milestoneRepository.findAll().size();

        // Create the Milestone with an existing ID
        milestone.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMilestoneMockMvc.perform(post("/api/milestones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(milestone)))
            .andExpect(status().isBadRequest());

        // Validate the Milestone in the database
        List<Milestone> milestoneList = milestoneRepository.findAll();
        assertThat(milestoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMilestones() throws Exception {
        // Initialize the database
        milestoneRepository.saveAndFlush(milestone);

        // Get all the milestoneList
        restMilestoneMockMvc.perform(get("/api/milestones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(milestone.getId().intValue())));
    }
    

    @Test
    @Transactional
    public void getMilestone() throws Exception {
        // Initialize the database
        milestoneRepository.saveAndFlush(milestone);

        // Get the milestone
        restMilestoneMockMvc.perform(get("/api/milestones/{id}", milestone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(milestone.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMilestone() throws Exception {
        // Get the milestone
        restMilestoneMockMvc.perform(get("/api/milestones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMilestone() throws Exception {
        // Initialize the database
        milestoneRepository.saveAndFlush(milestone);

        int databaseSizeBeforeUpdate = milestoneRepository.findAll().size();

        // Update the milestone
        Milestone updatedMilestone = milestoneRepository.findById(milestone.getId()).get();
        // Disconnect from session so that the updates on updatedMilestone are not directly saved in db
        em.detach(updatedMilestone);

        restMilestoneMockMvc.perform(put("/api/milestones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMilestone)))
            .andExpect(status().isOk());

        // Validate the Milestone in the database
        List<Milestone> milestoneList = milestoneRepository.findAll();
        assertThat(milestoneList).hasSize(databaseSizeBeforeUpdate);
        Milestone testMilestone = milestoneList.get(milestoneList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingMilestone() throws Exception {
        int databaseSizeBeforeUpdate = milestoneRepository.findAll().size();

        // Create the Milestone

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restMilestoneMockMvc.perform(put("/api/milestones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(milestone)))
            .andExpect(status().isBadRequest());

        // Validate the Milestone in the database
        List<Milestone> milestoneList = milestoneRepository.findAll();
        assertThat(milestoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMilestone() throws Exception {
        // Initialize the database
        milestoneRepository.saveAndFlush(milestone);

        int databaseSizeBeforeDelete = milestoneRepository.findAll().size();

        // Get the milestone
        restMilestoneMockMvc.perform(delete("/api/milestones/{id}", milestone.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Milestone> milestoneList = milestoneRepository.findAll();
        assertThat(milestoneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Milestone.class);
        Milestone milestone1 = new Milestone();
        milestone1.setId(1L);
        Milestone milestone2 = new Milestone();
        milestone2.setId(milestone1.getId());
        assertThat(milestone1).isEqualTo(milestone2);
        milestone2.setId(2L);
        assertThat(milestone1).isNotEqualTo(milestone2);
        milestone1.setId(null);
        assertThat(milestone1).isNotEqualTo(milestone2);
    }
}
