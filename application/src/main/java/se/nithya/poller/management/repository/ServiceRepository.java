package se.nithya.poller.management.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceModel, Long> {
    Iterable<ServiceModel> findByCreatedBy(@Param("createdBy") String createdBy);
}
