package com.it4innov.geniusquiz.repository;

import com.it4innov.geniusquiz.domain.Chapitre;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Chapitre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {
}
