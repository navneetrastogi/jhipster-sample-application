package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Kudos;
import io.github.jhipster.application.repository.KudosRepository;
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
 * Test class for the KudosResource REST controller.
 *
 * @see KudosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class KudosResourceIntTest {

    @Autowired
    private KudosRepository kudosRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKudosMockMvc;

    private Kudos kudos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KudosResource kudosResource = new KudosResource(kudosRepository);
        this.restKudosMockMvc = MockMvcBuilders.standaloneSetup(kudosResource)
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
    public static Kudos createEntity(EntityManager em) {
        Kudos kudos = new Kudos();
        return kudos;
    }

    @Before
    public void initTest() {
        kudos = createEntity(em);
    }

    @Test
    @Transactional
    public void createKudos() throws Exception {
        int databaseSizeBeforeCreate = kudosRepository.findAll().size();

        // Create the Kudos
        restKudosMockMvc.perform(post("/api/kudos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kudos)))
            .andExpect(status().isCreated());

        // Validate the Kudos in the database
        List<Kudos> kudosList = kudosRepository.findAll();
        assertThat(kudosList).hasSize(databaseSizeBeforeCreate + 1);
        Kudos testKudos = kudosList.get(kudosList.size() - 1);
    }

    @Test
    @Transactional
    public void createKudosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kudosRepository.findAll().size();

        // Create the Kudos with an existing ID
        kudos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKudosMockMvc.perform(post("/api/kudos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kudos)))
            .andExpect(status().isBadRequest());

        // Validate the Kudos in the database
        List<Kudos> kudosList = kudosRepository.findAll();
        assertThat(kudosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKudos() throws Exception {
        // Initialize the database
        kudosRepository.saveAndFlush(kudos);

        // Get all the kudosList
        restKudosMockMvc.perform(get("/api/kudos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kudos.getId().intValue())));
    }
    

    @Test
    @Transactional
    public void getKudos() throws Exception {
        // Initialize the database
        kudosRepository.saveAndFlush(kudos);

        // Get the kudos
        restKudosMockMvc.perform(get("/api/kudos/{id}", kudos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kudos.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingKudos() throws Exception {
        // Get the kudos
        restKudosMockMvc.perform(get("/api/kudos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKudos() throws Exception {
        // Initialize the database
        kudosRepository.saveAndFlush(kudos);

        int databaseSizeBeforeUpdate = kudosRepository.findAll().size();

        // Update the kudos
        Kudos updatedKudos = kudosRepository.findById(kudos.getId()).get();
        // Disconnect from session so that the updates on updatedKudos are not directly saved in db
        em.detach(updatedKudos);

        restKudosMockMvc.perform(put("/api/kudos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKudos)))
            .andExpect(status().isOk());

        // Validate the Kudos in the database
        List<Kudos> kudosList = kudosRepository.findAll();
        assertThat(kudosList).hasSize(databaseSizeBeforeUpdate);
        Kudos testKudos = kudosList.get(kudosList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingKudos() throws Exception {
        int databaseSizeBeforeUpdate = kudosRepository.findAll().size();

        // Create the Kudos

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restKudosMockMvc.perform(put("/api/kudos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kudos)))
            .andExpect(status().isBadRequest());

        // Validate the Kudos in the database
        List<Kudos> kudosList = kudosRepository.findAll();
        assertThat(kudosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKudos() throws Exception {
        // Initialize the database
        kudosRepository.saveAndFlush(kudos);

        int databaseSizeBeforeDelete = kudosRepository.findAll().size();

        // Get the kudos
        restKudosMockMvc.perform(delete("/api/kudos/{id}", kudos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Kudos> kudosList = kudosRepository.findAll();
        assertThat(kudosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kudos.class);
        Kudos kudos1 = new Kudos();
        kudos1.setId(1L);
        Kudos kudos2 = new Kudos();
        kudos2.setId(kudos1.getId());
        assertThat(kudos1).isEqualTo(kudos2);
        kudos2.setId(2L);
        assertThat(kudos1).isNotEqualTo(kudos2);
        kudos1.setId(null);
        assertThat(kudos1).isNotEqualTo(kudos2);
    }
}
