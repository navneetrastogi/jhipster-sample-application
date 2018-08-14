package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ImmunizationRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ImmunizationRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImmunizationRecordRepository extends JpaRepository<ImmunizationRecord, Long> {

}
