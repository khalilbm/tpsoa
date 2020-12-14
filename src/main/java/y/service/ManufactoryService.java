package y.service;

import y.domain.Manufactory;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Manufactory}.
 */
public interface ManufactoryService {

    /**
     * Save a manufactory.
     *
     * @param manufactory the entity to save.
     * @return the persisted entity.
     */
    Manufactory save(Manufactory manufactory);

    /**
     * Get all the manufactories.
     *
     * @return the list of entities.
     */
    List<Manufactory> findAll();


    /**
     * Get the "id" manufactory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Manufactory> findOne(Long id);

    /**
     * Delete the "id" manufactory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
