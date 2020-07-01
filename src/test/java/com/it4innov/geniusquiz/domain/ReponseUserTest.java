package com.it4innov.geniusquiz.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class ReponseUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReponseUser.class);
        ReponseUser reponseUser1 = new ReponseUser();
        reponseUser1.setId(1L);
        ReponseUser reponseUser2 = new ReponseUser();
        reponseUser2.setId(reponseUser1.getId());
        assertThat(reponseUser1).isEqualTo(reponseUser2);
        reponseUser2.setId(2L);
        assertThat(reponseUser1).isNotEqualTo(reponseUser2);
        reponseUser1.setId(null);
        assertThat(reponseUser1).isNotEqualTo(reponseUser2);
    }
}
