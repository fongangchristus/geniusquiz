package com.it4innov.geniusquiz.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class CategorieFormationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieFormation.class);
        CategorieFormation categorieFormation1 = new CategorieFormation();
        categorieFormation1.setId(1L);
        CategorieFormation categorieFormation2 = new CategorieFormation();
        categorieFormation2.setId(categorieFormation1.getId());
        assertThat(categorieFormation1).isEqualTo(categorieFormation2);
        categorieFormation2.setId(2L);
        assertThat(categorieFormation1).isNotEqualTo(categorieFormation2);
        categorieFormation1.setId(null);
        assertThat(categorieFormation1).isNotEqualTo(categorieFormation2);
    }
}
