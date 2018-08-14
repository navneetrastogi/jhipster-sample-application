package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Kudos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Kudos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KudosRepository extends JpaRepository<Kudos, Long> {

}
