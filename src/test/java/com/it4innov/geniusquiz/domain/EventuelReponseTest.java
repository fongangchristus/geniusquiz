package com.it4innov.geniusquiz.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class EventuelReponseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventuelReponse.class);
        EventuelReponse eventuelReponse1 = new EventuelReponse();
        eventuelReponse1.setId(1L);
        EventuelReponse eventuelReponse2 = new EventuelReponse();
        eventuelReponse2.setId(eventuelReponse1.getId());
        assertThat(eventuelReponse1).isEqualTo(eventuelReponse2);
        eventuelReponse2.setId(2L);
        assertThat(eventuelReponse1).isNotEqualTo(eventuelReponse2);
        eventuelReponse1.setId(null);
        assertThat(eventuelReponse1).isNotEqualTo(eventuelReponse2);
    }
}
