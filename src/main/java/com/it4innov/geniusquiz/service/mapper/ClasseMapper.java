package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.ClasseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Classe} and its DTO {@link ClasseDTO}.
 */
@Mapper(componentModel = "spring", uses = {NiveauMapper.class, OptionMapper.class, CategorieFormationMapper.class, MatiereMapper.class})
public interface ClasseMapper extends EntityMapper<ClasseDTO, Classe> {

    @Mapping(source = "niveau.id", target = "niveauId")
    @Mapping(source = "option.id", target = "optionId")
    @Mapping(source = "categorieFormation.id", target = "categorieFormationId")
    ClasseDTO toDto(Classe classe);

    @Mapping(source = "niveauId", target = "niveau")
    @Mapping(source = "optionId", target = "option")
    @Mapping(source = "categorieFormationId", target = "categorieFormation")
    @Mapping(target = "removeMatiere", ignore = true)
    Classe toEntity(ClasseDTO classeDTO);

    default Classe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Classe classe = new Classe();
        classe.setId(id);
        return classe;
    }
}
