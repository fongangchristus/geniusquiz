package com.it4innov.geniusquiz.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChapitreMapperTest {

    private ChapitreMapper chapitreMapper;

    @BeforeEach
    public void setUp() {
        chapitreMapper = new ChapitreMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(chapitreMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chapitreMapper.fromId(null)).isNull();
    }
}
