package com.it4innov.geniusquiz.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class ChapitreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chapitre.class);
        Chapitre chapitre1 = new Chapitre();
        chapitre1.setId(1L);
        Chapitre chapitre2 = new Chapitre();
        chapitre2.setId(chapitre1.getId());
        assertThat(chapitre1).isEqualTo(chapitre2);
        chapitre2.setId(2L);
        assertThat(chapitre1).isNotEqualTo(chapitre2);
        chapitre1.setId(null);
        assertThat(chapitre1).isNotEqualTo(chapitre2);
    }
}
