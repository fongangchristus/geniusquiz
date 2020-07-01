package com.it4innov.geniusquiz.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CategorieFormationMapperTest {

    private CategorieFormationMapper categorieFormationMapper;

    @BeforeEach
    public void setUp() {
        categorieFormationMapper = new CategorieFormationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(categorieFormationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorieFormationMapper.fromId(null)).isNull();
    }
}
