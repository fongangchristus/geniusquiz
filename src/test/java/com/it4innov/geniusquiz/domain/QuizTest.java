package com.it4innov.geniusquiz.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.it4innov.geniusquiz.web.rest.TestUtil;

public class QuizTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Quiz.class);
        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        Quiz quiz2 = new Quiz();
        quiz2.setId(quiz1.getId());
        assertThat(quiz1).isEqualTo(quiz2);
        quiz2.setId(2L);
        assertThat(quiz1).isNotEqualTo(quiz2);
        quiz1.setId(null);
        assertThat(quiz1).isNotEqualTo(quiz2);
    }
}
