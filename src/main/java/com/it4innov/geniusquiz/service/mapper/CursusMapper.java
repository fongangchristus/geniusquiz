package com.it4innov.geniusquiz.service.mapper;


import com.it4innov.geniusquiz.domain.*;
import com.it4innov.geniusquiz.service.dto.CursusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cursus} and its DTO {@link CursusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CursusMapper extends EntityMapper<CursusDTO, Cursus> {



    default Cursus fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cursus cursus = new Cursus();
        cursus.setId(id);
        return cursus;
    }
}
