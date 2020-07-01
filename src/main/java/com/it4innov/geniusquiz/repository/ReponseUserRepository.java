package com.it4innov.geniusquiz.repository;

import com.it4innov.geniusquiz.domain.ReponseUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ReponseUser entity.
 */
@Repository
public interface ReponseUserRepository extends JpaRepository<ReponseUser, Long> {

    @Query(value = "select distinct reponseUser from ReponseUser reponseUser left join fetch reponseUser.eventuelReponses",
        countQuery = "select count(distinct reponseUser) from ReponseUser reponseUser")
    Page<ReponseUser> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct reponseUser from ReponseUser reponseUser left join fetch reponseUser.eventuelReponses")
    List<ReponseUser> findAllWithEagerRelationships();

    @Query("select reponseUser from ReponseUser reponseUser left join fetch reponseUser.eventuelReponses where reponseUser.id =:id")
    Optional<ReponseUser> findOneWithEagerRelationships(@Param("id") Long id);
}
