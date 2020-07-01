package com.it4innov.geniusquiz.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReponseUserMapperTest {

    private ReponseUserMapper reponseUserMapper;

    @BeforeEach
    public void setUp() {
        reponseUserMapper = new ReponseUserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(reponseUserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(reponseUserMapper.fromId(null)).isNull();
    }
}
