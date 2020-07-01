package com.it4innov.geniusquiz.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class EventuelReponseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventuelReponseDTO.class);
        EventuelReponseDTO eventuelReponseDTO1 = new EventuelReponseDTO();
        eventuelReponseDTO1.setId(1L);
        EventuelReponseDTO eventuelReponseDTO2 = new EventuelReponseDTO();
        assertThat(eventuelReponseDTO1).isNotEqualTo(eventuelReponseDTO2);
        eventuelReponseDTO2.setId(eventuelReponseDTO1.getId());
        assertThat(eventuelReponseDTO1).isEqualTo(eventuelReponseDTO2);
        eventuelReponseDTO2.setId(2L);
        assertThat(eventuelReponseDTO1).isNotEqualTo(eventuelReponseDTO2);
        eventuelReponseDTO1.setId(null);
        assertThat(eventuelReponseDTO1).isNotEqualTo(eventuelReponseDTO2);
    }
}
