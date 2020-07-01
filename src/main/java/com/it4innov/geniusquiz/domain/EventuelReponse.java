package com.it4innov.geniusquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A EventuelReponse.
 */
@Entity
@Table(name = "eventuel_reponse")
public class EventuelReponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "libele", nullable = false)
    private String libele;

    @Column(name = "code")
    private String code;

    @Column(name = "image_couverture")
    private String imageCouverture;

    @Column(name = "description")
    private String description;

    @Column(name = "correct_answer")
    private Boolean correctAnswer;

    @Column(name = "point")
    private Integer point;

    @ManyToOne
    @JsonIgnoreProperties(value = "eventuelReponses", allowSetters = true)
    private Quiz quiz;

    @ManyToMany(mappedBy = "eventuelReponses")
    @JsonIgnore
    private Set<ReponseUser> reponseUsers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibele() {
        return libele;
    }

    public EventuelReponse libele(String libele) {
        this.libele = libele;
        return this;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getCode() {
        return code;
    }

    public EventuelReponse code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public EventuelReponse imageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
        return this;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public String getDescription() {
        return description;
    }

    public EventuelReponse description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public EventuelReponse correctAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getPoint() {
        return point;
    }

    public EventuelReponse point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public EventuelReponse quiz(Quiz quiz) {
        this.quiz = quiz;
        return this;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Set<ReponseUser> getReponseUsers() {
        return reponseUsers;
    }

    public EventuelReponse reponseUsers(Set<ReponseUser> reponseUsers) {
        this.reponseUsers = reponseUsers;
        return this;
    }

    public EventuelReponse addReponseUser(ReponseUser reponseUser) {
        this.reponseUsers.add(reponseUser);
        reponseUser.getEventuelReponses().add(this);
        return this;
    }

    public EventuelReponse removeReponseUser(ReponseUser reponseUser) {
        this.reponseUsers.remove(reponseUser);
        reponseUser.getEventuelReponses().remove(this);
        return this;
    }

    public void setReponseUsers(Set<ReponseUser> reponseUsers) {
        this.reponseUsers = reponseUsers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventuelReponse)) {
            return false;
        }
        return id != null && id.equals(((EventuelReponse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventuelReponse{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", code='" + getCode() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", description='" + getDescription() + "'" +
            ", correctAnswer='" + isCorrectAnswer() + "'" +
            ", point=" + getPoint() +
            "}";
    }
}
