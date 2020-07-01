package com.it4innov.geniusquiz.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class CategorieFormationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorieFormationDTO.class);
        CategorieFormationDTO categorieFormationDTO1 = new CategorieFormationDTO();
        categorieFormationDTO1.setId(1L);
        CategorieFormationDTO categorieFormationDTO2 = new CategorieFormationDTO();
        assertThat(categorieFormationDTO1).isNotEqualTo(categorieFormationDTO2);
        categorieFormationDTO2.setId(categorieFormationDTO1.getId());
        assertThat(categorieFormationDTO1).isEqualTo(categorieFormationDTO2);
        categorieFormationDTO2.setId(2L);
        assertThat(categorieFormationDTO1).isNotEqualTo(categorieFormationDTO2);
        categorieFormationDTO1.setId(null);
        assertThat(categorieFormationDTO1).isNotEqualTo(categorieFormationDTO2);
    }
}
