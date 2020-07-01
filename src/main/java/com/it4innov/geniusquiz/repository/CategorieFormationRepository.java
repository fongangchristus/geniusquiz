package com.it4innov.geniusquiz.repository;

import com.it4innov.geniusquiz.domain.CategorieFormation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CategorieFormation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategorieFormationRepository extends JpaRepository<CategorieFormation, Long> {
}
