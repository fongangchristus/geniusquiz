package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.MatiereDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Matiere} and its DTO {@link MatiereDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MatiereMapper extends EntityMapper<MatiereDTO, Matiere> {


    @Mapping(target = "classes", ignore = true)
    @Mapping(target = "removeClasse", ignore = true)
    Matiere toEntity(MatiereDTO matiereDTO);

    default Matiere fromId(Long id) {
        if (id == null) {
            return null;
        }
        Matiere matiere = new Matiere();
        matiere.setId(id);
        return matiere;
    }
}
