package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.CategorieFormationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategorieFormation} and its DTO {@link CategorieFormationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieFormationMapper extends EntityMapper<CategorieFormationDTO, CategorieFormation> {



    default CategorieFormation fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategorieFormation categorieFormation = new CategorieFormation();
        categorieFormation.setId(id);
        return categorieFormation;
    }
}
