package com.it4innov.geniusquiz.repository;

import com.it4innov.geniusquiz.domain.Classe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Classe entity.
 */
@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

    @Query(value = "select distinct classe from Classe classe left join fetch classe.matieres",
        countQuery = "select count(distinct classe) from Classe classe")
    Page<Classe> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct classe from Classe classe left join fetch classe.matieres")
    List<Classe> findAllWithEagerRelationships();

    @Query("select classe from Classe classe left join fetch classe.matieres where classe.id =:id")
    Optional<Classe> findOneWithEagerRelationships(@Param("id") Long id);
}
