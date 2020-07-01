package com.it4innov.geniusquiz.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CursusMapperTest {

    private CursusMapper cursusMapper;

    @BeforeEach
    public void setUp() {
        cursusMapper = new CursusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cursusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cursusMapper.fromId(null)).isNull();
    }
}
