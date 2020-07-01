package com.it4innov.geniusquiz.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class ReponseUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReponseUserDTO.class);
        ReponseUserDTO reponseUserDTO1 = new ReponseUserDTO();
        reponseUserDTO1.setId(1L);
        ReponseUserDTO reponseUserDTO2 = new ReponseUserDTO();
        assertThat(reponseUserDTO1).isNotEqualTo(reponseUserDTO2);
        reponseUserDTO2.setId(reponseUserDTO1.getId());
        assertThat(reponseUserDTO1).isEqualTo(reponseUserDTO2);
        reponseUserDTO2.setId(2L);
        assertThat(reponseUserDTO1).isNotEqualTo(reponseUserDTO2);
        reponseUserDTO1.setId(null);
        assertThat(reponseUserDTO1).isNotEqualTo(reponseUserDTO2);
    }
}
