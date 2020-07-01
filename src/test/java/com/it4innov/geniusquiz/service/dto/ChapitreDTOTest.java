package com.it4innov.geniusquiz.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class ChapitreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChapitreDTO.class);
        ChapitreDTO chapitreDTO1 = new ChapitreDTO();
        chapitreDTO1.setId(1L);
        ChapitreDTO chapitreDTO2 = new ChapitreDTO();
        assertThat(chapitreDTO1).isNotEqualTo(chapitreDTO2);
        chapitreDTO2.setId(chapitreDTO1.getId());
        assertThat(chapitreDTO1).isEqualTo(chapitreDTO2);
        chapitreDTO2.setId(2L);
        assertThat(chapitreDTO1).isNotEqualTo(chapitreDTO2);
        chapitreDTO1.setId(null);
        assertThat(chapitreDTO1).isNotEqualTo(chapitreDTO2);
    }
}
