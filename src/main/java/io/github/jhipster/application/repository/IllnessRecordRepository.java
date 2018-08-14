package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.IllnessRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IllnessRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IllnessRecordRepository extends JpaRepository<IllnessRecord, Long> {

}
