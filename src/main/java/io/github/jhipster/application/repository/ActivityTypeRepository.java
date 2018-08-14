package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ActivityType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivityType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {

}
