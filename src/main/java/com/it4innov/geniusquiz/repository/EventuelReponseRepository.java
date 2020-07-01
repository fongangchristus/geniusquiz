package com.it4innov.geniusquiz.repository;

import com.it4innov.geniusquiz.domain.EventuelReponse;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EventuelReponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventuelReponseRepository extends JpaRepository<EventuelReponse, Long> {
}
