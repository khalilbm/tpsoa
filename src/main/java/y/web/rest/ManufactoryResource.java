package y.web.rest;

import y.domain.Manufactory;
import y.service.ManufactoryService;
import y.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link y.domain.Manufactory}.
 */
@RestController
@RequestMapping("/api")
public class ManufactoryResource {

    private final Logger log = LoggerFactory.getLogger(ManufactoryResource.class);

    private static final String ENTITY_NAME = "manufactory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManufactoryService manufactoryService;

    public ManufactoryResource(ManufactoryService manufactoryService) {
        this.manufactoryService = manufactoryService;
    }

    /**
     * {@code POST  /manufactories} : Create a new manufactory.
     *
     * @param manufactory the manufactory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new manufactory, or with status {@code 400 (Bad Request)} if the manufactory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/manufactories")
    public ResponseEntity<Manufactory> createManufactory(@RequestBody Manufactory manufactory) throws URISyntaxException {
        log.debug("REST request to save Manufactory : {}", manufactory);
        if (manufactory.getId() != null) {
            throw new BadRequestAlertException("A new manufactory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Manufactory result = manufactoryService.save(manufactory);
        return ResponseEntity.created(new URI("/api/manufactories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /manufactories} : Updates an existing manufactory.
     *
     * @param manufactory the manufactory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated manufactory,
     * or with status {@code 400 (Bad Request)} if the manufactory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the manufactory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/manufactories")
    public ResponseEntity<Manufactory> updateManufactory(@RequestBody Manufactory manufactory) throws URISyntaxException {
        log.debug("REST request to update Manufactory : {}", manufactory);
        if (manufactory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Manufactory result = manufactoryService.save(manufactory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, manufactory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /manufactories} : get all the manufactories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of manufactories in body.
     */
    @GetMapping("/manufactories")
    public List<Manufactory> getAllManufactories() {
        log.debug("REST request to get all Manufactories");
        return manufactoryService.findAll();
    }

    /**
     * {@code GET  /manufactories/:id} : get the "id" manufactory.
     *
     * @param id the id of the manufactory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the manufactory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/manufactories/{id}")
    public ResponseEntity<Manufactory> getManufactory(@PathVariable Long id) {
        log.debug("REST request to get Manufactory : {}", id);
        Optional<Manufactory> manufactory = manufactoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(manufactory);
    }

    /**
     * {@code DELETE  /manufactories/:id} : delete the "id" manufactory.
     *
     * @param id the id of the manufactory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/manufactories/{id}")
    public ResponseEntity<Void> deleteManufactory(@PathVariable Long id) {
        log.debug("REST request to delete Manufactory : {}", id);
        manufactoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
