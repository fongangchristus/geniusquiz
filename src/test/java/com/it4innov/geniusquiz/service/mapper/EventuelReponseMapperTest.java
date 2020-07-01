package com.it4innov.geniusquiz.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EventuelReponseMapperTest {

    private EventuelReponseMapper eventuelReponseMapper;

    @BeforeEach
    public void setUp() {
        eventuelReponseMapper = new EventuelReponseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(eventuelReponseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(eventuelReponseMapper.fromId(null)).isNull();
    }
}
