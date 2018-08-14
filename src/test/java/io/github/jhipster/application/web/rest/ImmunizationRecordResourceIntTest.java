package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ImmunizationRecord;
import io.github.jhipster.application.repository.ImmunizationRecordRepository;
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
 * Test class for the ImmunizationRecordResource REST controller.
 *
 * @see ImmunizationRecordResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ImmunizationRecordResourceIntTest {

    @Autowired
    private ImmunizationRecordRepository immunizationRecordRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restImmunizationRecordMockMvc;

    private ImmunizationRecord immunizationRecord;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImmunizationRecordResource immunizationRecordResource = new ImmunizationRecordResource(immunizationRecordRepository);
        this.restImmunizationRecordMockMvc = MockMvcBuilders.standaloneSetup(immunizationRecordResource)
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
    public static ImmunizationRecord createEntity(EntityManager em) {
        ImmunizationRecord immunizationRecord = new ImmunizationRecord();
        return immunizationRecord;
    }

    @Before
    public void initTest() {
        immunizationRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createImmunizationRecord() throws Exception {
        int databaseSizeBeforeCreate = immunizationRecordRepository.findAll().size();

        // Create the ImmunizationRecord
        restImmunizationRecordMockMvc.perform(post("/api/immunization-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationRecord)))
            .andExpect(status().isCreated());

        // Validate the ImmunizationRecord in the database
        List<ImmunizationRecord> immunizationRecordList = immunizationRecordRepository.findAll();
        assertThat(immunizationRecordList).hasSize(databaseSizeBeforeCreate + 1);
        ImmunizationRecord testImmunizationRecord = immunizationRecordList.get(immunizationRecordList.size() - 1);
    }

    @Test
    @Transactional
    public void createImmunizationRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = immunizationRecordRepository.findAll().size();

        // Create the ImmunizationRecord with an existing ID
        immunizationRecord.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImmunizationRecordMockMvc.perform(post("/api/immunization-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationRecord)))
            .andExpect(status().isBadRequest());

        // Validate the ImmunizationRecord in the database
        List<ImmunizationRecord> immunizationRecordList = immunizationRecordRepository.findAll();
        assertThat(immunizationRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllImmunizationRecords() throws Exception {
        // Initialize the database
        immunizationRecordRepository.saveAndFlush(immunizationRecord);

        // Get all the immunizationRecordList
        restImmunizationRecordMockMvc.perform(get("/api/immunization-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(immunizationRecord.getId().intValue())));
    }
    

    @Test
    @Transactional
    public void getImmunizationRecord() throws Exception {
        // Initialize the database
        immunizationRecordRepository.saveAndFlush(immunizationRecord);

        // Get the immunizationRecord
        restImmunizationRecordMockMvc.perform(get("/api/immunization-records/{id}", immunizationRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(immunizationRecord.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingImmunizationRecord() throws Exception {
        // Get the immunizationRecord
        restImmunizationRecordMockMvc.perform(get("/api/immunization-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImmunizationRecord() throws Exception {
        // Initialize the database
        immunizationRecordRepository.saveAndFlush(immunizationRecord);

        int databaseSizeBeforeUpdate = immunizationRecordRepository.findAll().size();

        // Update the immunizationRecord
        ImmunizationRecord updatedImmunizationRecord = immunizationRecordRepository.findById(immunizationRecord.getId()).get();
        // Disconnect from session so that the updates on updatedImmunizationRecord are not directly saved in db
        em.detach(updatedImmunizationRecord);

        restImmunizationRecordMockMvc.perform(put("/api/immunization-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedImmunizationRecord)))
            .andExpect(status().isOk());

        // Validate the ImmunizationRecord in the database
        List<ImmunizationRecord> immunizationRecordList = immunizationRecordRepository.findAll();
        assertThat(immunizationRecordList).hasSize(databaseSizeBeforeUpdate);
        ImmunizationRecord testImmunizationRecord = immunizationRecordList.get(immunizationRecordList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingImmunizationRecord() throws Exception {
        int databaseSizeBeforeUpdate = immunizationRecordRepository.findAll().size();

        // Create the ImmunizationRecord

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restImmunizationRecordMockMvc.perform(put("/api/immunization-records")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(immunizationRecord)))
            .andExpect(status().isBadRequest());

        // Validate the ImmunizationRecord in the database
        List<ImmunizationRecord> immunizationRecordList = immunizationRecordRepository.findAll();
        assertThat(immunizationRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImmunizationRecord() throws Exception {
        // Initialize the database
        immunizationRecordRepository.saveAndFlush(immunizationRecord);

        int databaseSizeBeforeDelete = immunizationRecordRepository.findAll().size();

        // Get the immunizationRecord
        restImmunizationRecordMockMvc.perform(delete("/api/immunization-records/{id}", immunizationRecord.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImmunizationRecord> immunizationRecordList = immunizationRecordRepository.findAll();
        assertThat(immunizationRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImmunizationRecord.class);
        ImmunizationRecord immunizationRecord1 = new ImmunizationRecord();
        immunizationRecord1.setId(1L);
        ImmunizationRecord immunizationRecord2 = new ImmunizationRecord();
        immunizationRecord2.setId(immunizationRecord1.getId());
        assertThat(immunizationRecord1).isEqualTo(immunizationRecord2);
        immunizationRecord2.setId(2L);
        assertThat(immunizationRecord1).isNotEqualTo(immunizationRecord2);
        immunizationRecord1.setId(null);
        assertThat(immunizationRecord1).isNotEqualTo(immunizationRecord2);
    }
}
