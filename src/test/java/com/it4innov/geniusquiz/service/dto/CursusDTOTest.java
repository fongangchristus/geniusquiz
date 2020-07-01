package com.it4innov.geniusquiz.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class CursusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CursusDTO.class);
        CursusDTO cursusDTO1 = new CursusDTO();
        cursusDTO1.setId(1L);
        CursusDTO cursusDTO2 = new CursusDTO();
        assertThat(cursusDTO1).isNotEqualTo(cursusDTO2);
        cursusDTO2.setId(cursusDTO1.getId());
        assertThat(cursusDTO1).isEqualTo(cursusDTO2);
        cursusDTO2.setId(2L);
        assertThat(cursusDTO1).isNotEqualTo(cursusDTO2);
        cursusDTO1.setId(null);
        assertThat(cursusDTO1).isNotEqualTo(cursusDTO2);
    }
}
