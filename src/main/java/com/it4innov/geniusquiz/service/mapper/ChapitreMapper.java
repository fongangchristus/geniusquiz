package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.ChapitreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Chapitre} and its DTO {@link ChapitreDTO}.
 */
@Mapper(componentModel = "spring", uses = {MatiereMapper.class})
public interface ChapitreMapper extends EntityMapper<ChapitreDTO, Chapitre> {

    @Mapping(source = "matiere.id", target = "matiereId")
    ChapitreDTO toDto(Chapitre chapitre);

    @Mapping(source = "matiereId", target = "matiere")
    Chapitre toEntity(ChapitreDTO chapitreDTO);

    default Chapitre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chapitre chapitre = new Chapitre();
        chapitre.setId(id);
        return chapitre;
    }
}
