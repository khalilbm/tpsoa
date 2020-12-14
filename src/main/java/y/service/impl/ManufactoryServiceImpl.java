package y.service.impl;

import y.service.ManufactoryService;
import y.domain.Manufactory;
import y.repository.ManufactoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Manufactory}.
 */
@Service
@Transactional
public class ManufactoryServiceImpl implements ManufactoryService {

    private final Logger log = LoggerFactory.getLogger(ManufactoryServiceImpl.class);

    private final ManufactoryRepository manufactoryRepository;

    public ManufactoryServiceImpl(ManufactoryRepository manufactoryRepository) {
        this.manufactoryRepository = manufactoryRepository;
    }

    @Override
    public Manufactory save(Manufactory manufactory) {
        log.debug("Request to save Manufactory : {}", manufactory);
        return manufactoryRepository.save(manufactory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Manufactory> findAll() {
        log.debug("Request to get all Manufactories");
        return manufactoryRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Manufactory> findOne(Long id) {
        log.debug("Request to get Manufactory : {}", id);
        return manufactoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Manufactory : {}", id);
        manufactoryRepository.deleteById(id);
    }
}
