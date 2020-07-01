package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.NiveauDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Niveau} and its DTO {@link NiveauDTO}.
 */
@Mapper(componentModel = "spring", uses = {CursusMapper.class})
public interface NiveauMapper extends EntityMapper<NiveauDTO, Niveau> {

    @Mapping(source = "curcus.id", target = "curcusId")
    NiveauDTO toDto(Niveau niveau);

    @Mapping(source = "curcusId", target = "curcus")
    Niveau toEntity(NiveauDTO niveauDTO);

    default Niveau fromId(Long id) {
        if (id == null) {
            return null;
        }
        Niveau niveau = new Niveau();
        niveau.setId(id);
        return niveau;
    }
}
