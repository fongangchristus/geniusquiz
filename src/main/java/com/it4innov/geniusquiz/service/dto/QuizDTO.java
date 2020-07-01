package com.it4innov.geniusquiz.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.Quiz} entity.
 */
public class QuizDTO implements Serializable {
    
    private Long id;

    private String type;

    private Long idMatiere;

    private String entete;

    private String description;

    @NotNull
    private String libele;

    private String imageCouverture;

    private Instant duree;

    private Integer nbrQuestion;

    private Instant datePublication;

    private Instant dateExpiration;


    private Long matiereId;
    private Set<QuestionDTO> questions = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getEntete() {
        return entete;
    }

    public void setEntete(String entete) {
        this.entete = entete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public Instant getDuree() {
        return duree;
    }

    public void setDuree(Instant duree) {
        this.duree = duree;
    }

    public Integer getNbrQuestion() {
        return nbrQuestion;
    }

    public void setNbrQuestion(Integer nbrQuestion) {
        this.nbrQuestion = nbrQuestion;
    }

    public Instant getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Instant datePublication) {
        this.datePublication = datePublication;
    }

    public Instant getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Instant dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Long getMatiereId() {
        return matiereId;
    }

    public void setMatiereId(Long matiereId) {
        this.matiereId = matiereId;
    }

    public Set<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuizDTO)) {
            return false;
        }

        return id != null && id.equals(((QuizDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuizDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", idMatiere=" + getIdMatiere() +
            ", entete='" + getEntete() + "'" +
            ", description='" + getDescription() + "'" +
            ", libele='" + getLibele() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", duree='" + getDuree() + "'" +
            ", nbrQuestion=" + getNbrQuestion() +
            ", datePublication='" + getDatePublication() + "'" +
            ", dateExpiration='" + getDateExpiration() + "'" +
            ", matiereId=" + getMatiereId() +
            ", questions='" + getQuestions() + "'" +
            "}";
    }
}
