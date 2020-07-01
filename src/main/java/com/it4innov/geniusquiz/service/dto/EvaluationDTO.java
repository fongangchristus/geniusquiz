package com.it4innov.geniusquiz.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.Evaluation} entity.
 */
public class EvaluationDTO implements Serializable {
    
    private Long id;

    private String lieu;

    private Double note;

    private Long idUser;

    @NotNull
    private Instant dateEvaluation;


    private Long quizId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Instant getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(Instant dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationDTO)) {
            return false;
        }

        return id != null && id.equals(((EvaluationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationDTO{" +
            "id=" + getId() +
            ", lieu='" + getLieu() + "'" +
            ", note=" + getNote() +
            ", idUser=" + getIdUser() +
            ", dateEvaluation='" + getDateEvaluation() + "'" +
            ", quizId=" + getQuizId() +
            "}";
    }
}
