package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Timeline;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Timeline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {

}
