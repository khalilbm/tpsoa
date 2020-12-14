package y.repository;

import y.domain.Manufactory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Manufactory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManufactoryRepository extends JpaRepository<Manufactory, Long> {
}
