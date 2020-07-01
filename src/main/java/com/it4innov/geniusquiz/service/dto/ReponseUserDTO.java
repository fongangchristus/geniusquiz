package com.it4innov.geniusquiz.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.ReponseUser} entity.
 */
public class ReponseUserDTO implements Serializable {
    
    private Long id;

    private Instant dateReponse;

    private String libele;


    private Long quizQuestionId;

    private Long evaluationId;
    private Set<EventuelReponseDTO> eventuelReponses = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Instant dateReponse) {
        this.dateReponse = dateReponse;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public Long getQuizQuestionId() {
        return quizQuestionId;
    }

    public void setQuizQuestionId(Long questionId) {
        this.quizQuestionId = questionId;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Set<EventuelReponseDTO> getEventuelReponses() {
        return eventuelReponses;
    }

    public void setEventuelReponses(Set<EventuelReponseDTO> eventuelReponses) {
        this.eventuelReponses = eventuelReponses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReponseUserDTO)) {
            return false;
        }

        return id != null && id.equals(((ReponseUserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReponseUserDTO{" +
            "id=" + getId() +
            ", dateReponse='" + getDateReponse() + "'" +
            ", libele='" + getLibele() + "'" +
            ", quizQuestionId=" + getQuizQuestionId() +
            ", evaluationId=" + getEvaluationId() +
            ", eventuelReponses='" + getEventuelReponses() + "'" +
            "}";
    }
}
