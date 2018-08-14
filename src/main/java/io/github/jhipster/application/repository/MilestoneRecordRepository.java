package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MilestoneRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MilestoneRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MilestoneRecordRepository extends JpaRepository<MilestoneRecord, Long> {

}
