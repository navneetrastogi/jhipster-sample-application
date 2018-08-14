package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Gallery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Gallery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

}
