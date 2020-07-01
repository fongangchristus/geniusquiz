package com.it4innov.geniusquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Quiz.
 */
@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "id_matiere")
    private Long idMatiere;

    @Column(name = "entete")
    private String entete;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "libele", nullable = false)
    private String libele;

    @Column(name = "image_couverture")
    private String imageCouverture;

    @Column(name = "duree")
    private Instant duree;

    @Column(name = "nbr_question")
    private Integer nbrQuestion;

    @Column(name = "date_publication")
    private Instant datePublication;

    @Column(name = "date_expiration")
    private Instant dateExpiration;

    @ManyToOne
    @JsonIgnoreProperties(value = "quizzes", allowSetters = true)
    private Matiere matiere;

    @ManyToMany
    @NotNull
    @JoinTable(name = "quiz_question",
               joinColumns = @JoinColumn(name = "quiz_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question> questions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Quiz type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdMatiere() {
        return idMatiere;
    }

    public Quiz idMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
        return this;
    }

    public void setIdMatiere(Long idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getEntete() {
        return entete;
    }

    public Quiz entete(String entete) {
        this.entete = entete;
        return this;
    }

    public void setEntete(String entete) {
        this.entete = entete;
    }

    public String getDescription() {
        return description;
    }

    public Quiz description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibele() {
        return libele;
    }

    public Quiz libele(String libele) {
        this.libele = libele;
        return this;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public Quiz imageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
        return this;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public Instant getDuree() {
        return duree;
    }

    public Quiz duree(Instant duree) {
        this.duree = duree;
        return this;
    }

    public void setDuree(Instant duree) {
        this.duree = duree;
    }

    public Integer getNbrQuestion() {
        return nbrQuestion;
    }

    public Quiz nbrQuestion(Integer nbrQuestion) {
        this.nbrQuestion = nbrQuestion;
        return this;
    }

    public void setNbrQuestion(Integer nbrQuestion) {
        this.nbrQuestion = nbrQuestion;
    }

    public Instant getDatePublication() {
        return datePublication;
    }

    public Quiz datePublication(Instant datePublication) {
        this.datePublication = datePublication;
        return this;
    }

    public void setDatePublication(Instant datePublication) {
        this.datePublication = datePublication;
    }

    public Instant getDateExpiration() {
        return dateExpiration;
    }

    public Quiz dateExpiration(Instant dateExpiration) {
        this.dateExpiration = dateExpiration;
        return this;
    }

    public void setDateExpiration(Instant dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public Quiz matiere(Matiere matiere) {
        this.matiere = matiere;
        return this;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Quiz questions(Set<Question> questions) {
        this.questions = questions;
        return this;
    }

    public Quiz addQuestion(Question question) {
        this.questions.add(question);
        question.getQuizzes().add(this);
        return this;
    }

    public Quiz removeQuestion(Question question) {
        this.questions.remove(question);
        question.getQuizzes().remove(this);
        return this;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quiz)) {
            return false;
        }
        return id != null && id.equals(((Quiz) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Quiz{" +
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
            "}";
    }
}
