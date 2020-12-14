package y.web.rest;

import y.TpsoaApp;
import y.domain.Manufactory;
import y.repository.ManufactoryRepository;
import y.service.ManufactoryService;

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
 * Integration tests for the {@link ManufactoryResource} REST controller.
 */
@SpringBootTest(classes = TpsoaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ManufactoryResourceIT {

    @Autowired
    private ManufactoryRepository manufactoryRepository;

    @Autowired
    private ManufactoryService manufactoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManufactoryMockMvc;

    private Manufactory manufactory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Manufactory createEntity(EntityManager em) {
        Manufactory manufactory = new Manufactory();
        return manufactory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Manufactory createUpdatedEntity(EntityManager em) {
        Manufactory manufactory = new Manufactory();
        return manufactory;
    }

    @BeforeEach
    public void initTest() {
        manufactory = createEntity(em);
    }

    @Test
    @Transactional
    public void createManufactory() throws Exception {
        int databaseSizeBeforeCreate = manufactoryRepository.findAll().size();
        // Create the Manufactory
        restManufactoryMockMvc.perform(post("/api/manufactories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufactory)))
            .andExpect(status().isCreated());

        // Validate the Manufactory in the database
        List<Manufactory> manufactoryList = manufactoryRepository.findAll();
        assertThat(manufactoryList).hasSize(databaseSizeBeforeCreate + 1);
        Manufactory testManufactory = manufactoryList.get(manufactoryList.size() - 1);
    }

    @Test
    @Transactional
    public void createManufactoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = manufactoryRepository.findAll().size();

        // Create the Manufactory with an existing ID
        manufactory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManufactoryMockMvc.perform(post("/api/manufactories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufactory)))
            .andExpect(status().isBadRequest());

        // Validate the Manufactory in the database
        List<Manufactory> manufactoryList = manufactoryRepository.findAll();
        assertThat(manufactoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllManufactories() throws Exception {
        // Initialize the database
        manufactoryRepository.saveAndFlush(manufactory);

        // Get all the manufactoryList
        restManufactoryMockMvc.perform(get("/api/manufactories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(manufactory.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getManufactory() throws Exception {
        // Initialize the database
        manufactoryRepository.saveAndFlush(manufactory);

        // Get the manufactory
        restManufactoryMockMvc.perform(get("/api/manufactories/{id}", manufactory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(manufactory.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingManufactory() throws Exception {
        // Get the manufactory
        restManufactoryMockMvc.perform(get("/api/manufactories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManufactory() throws Exception {
        // Initialize the database
        manufactoryService.save(manufactory);

        int databaseSizeBeforeUpdate = manufactoryRepository.findAll().size();

        // Update the manufactory
        Manufactory updatedManufactory = manufactoryRepository.findById(manufactory.getId()).get();
        // Disconnect from session so that the updates on updatedManufactory are not directly saved in db
        em.detach(updatedManufactory);

        restManufactoryMockMvc.perform(put("/api/manufactories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedManufactory)))
            .andExpect(status().isOk());

        // Validate the Manufactory in the database
        List<Manufactory> manufactoryList = manufactoryRepository.findAll();
        assertThat(manufactoryList).hasSize(databaseSizeBeforeUpdate);
        Manufactory testManufactory = manufactoryList.get(manufactoryList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingManufactory() throws Exception {
        int databaseSizeBeforeUpdate = manufactoryRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManufactoryMockMvc.perform(put("/api/manufactories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(manufactory)))
            .andExpect(status().isBadRequest());

        // Validate the Manufactory in the database
        List<Manufactory> manufactoryList = manufactoryRepository.findAll();
        assertThat(manufactoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManufactory() throws Exception {
        // Initialize the database
        manufactoryService.save(manufactory);

        int databaseSizeBeforeDelete = manufactoryRepository.findAll().size();

        // Delete the manufactory
        restManufactoryMockMvc.perform(delete("/api/manufactories/{id}", manufactory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Manufactory> manufactoryList = manufactoryRepository.findAll();
        assertThat(manufactoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
