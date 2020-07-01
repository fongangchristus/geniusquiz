package com.it4innov.geniusquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Evaluation.
 */
@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "note")
    private Double note;

    @Column(name = "id_user")
    private Long idUser;

    @NotNull
    @Column(name = "date_evaluation", nullable = false)
    private Instant dateEvaluation;

    @ManyToOne
    @JsonIgnoreProperties(value = "evaluations", allowSetters = true)
    private Quiz quiz;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public Evaluation lieu(String lieu) {
        this.lieu = lieu;
        return this;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Double getNote() {
        return note;
    }

    public Evaluation note(Double note) {
        this.note = note;
        return this;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Evaluation idUser(Long idUser) {
        this.idUser = idUser;
        return this;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Instant getDateEvaluation() {
        return dateEvaluation;
    }

    public Evaluation dateEvaluation(Instant dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
        return this;
    }

    public void setDateEvaluation(Instant dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Evaluation quiz(Quiz quiz) {
        this.quiz = quiz;
        return this;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evaluation)) {
            return false;
        }
        return id != null && id.equals(((Evaluation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Evaluation{" +
            "id=" + getId() +
            ", lieu='" + getLieu() + "'" +
            ", note=" + getNote() +
            ", idUser=" + getIdUser() +
            ", dateEvaluation='" + getDateEvaluation() + "'" +
            "}";
    }
}
