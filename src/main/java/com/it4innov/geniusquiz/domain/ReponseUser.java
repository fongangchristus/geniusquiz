package com.it4innov.geniusquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A ReponseUser.
 */
@Entity
@Table(name = "reponse_user")
public class ReponseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_reponse")
    private Instant dateReponse;

    @Column(name = "libele")
    private String libele;

    @OneToOne
    @JoinColumn(unique = true)
    private Question quizQuestion;

    @ManyToOne
    @JsonIgnoreProperties(value = "reponseUsers", allowSetters = true)
    private Evaluation evaluation;

    @ManyToMany
    @JoinTable(name = "reponse_user_eventuel_reponse",
               joinColumns = @JoinColumn(name = "reponse_user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "eventuel_reponse_id", referencedColumnName = "id"))
    private Set<EventuelReponse> eventuelReponses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateReponse() {
        return dateReponse;
    }

    public ReponseUser dateReponse(Instant dateReponse) {
        this.dateReponse = dateReponse;
        return this;
    }

    public void setDateReponse(Instant dateReponse) {
        this.dateReponse = dateReponse;
    }

    public String getLibele() {
        return libele;
    }

    public ReponseUser libele(String libele) {
        this.libele = libele;
        return this;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public Question getQuizQuestion() {
        return quizQuestion;
    }

    public ReponseUser quizQuestion(Question question) {
        this.quizQuestion = question;
        return this;
    }

    public void setQuizQuestion(Question question) {
        this.quizQuestion = question;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public ReponseUser evaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
        return this;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Set<EventuelReponse> getEventuelReponses() {
        return eventuelReponses;
    }

    public ReponseUser eventuelReponses(Set<EventuelReponse> eventuelReponses) {
        this.eventuelReponses = eventuelReponses;
        return this;
    }

    public ReponseUser addEventuelReponse(EventuelReponse eventuelReponse) {
        this.eventuelReponses.add(eventuelReponse);
        eventuelReponse.getReponseUsers().add(this);
        return this;
    }

    public ReponseUser removeEventuelReponse(EventuelReponse eventuelReponse) {
        this.eventuelReponses.remove(eventuelReponse);
        eventuelReponse.getReponseUsers().remove(this);
        return this;
    }

    public void setEventuelReponses(Set<EventuelReponse> eventuelReponses) {
        this.eventuelReponses = eventuelReponses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReponseUser)) {
            return false;
        }
        return id != null && id.equals(((ReponseUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReponseUser{" +
            "id=" + getId() +
            ", dateReponse='" + getDateReponse() + "'" +
            ", libele='" + getLibele() + "'" +
            "}";
    }
}
