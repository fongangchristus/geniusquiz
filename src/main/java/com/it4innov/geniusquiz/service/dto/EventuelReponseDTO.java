package com.it4innov.geniusquiz.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.EventuelReponse} entity.
 */
public class EventuelReponseDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libele;

    private String code;

    private String imageCouverture;

    private String description;

    private Boolean correctAnswer;

    private Integer point;


    private Long quizId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
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
        if (!(o instanceof EventuelReponseDTO)) {
            return false;
        }

        return id != null && id.equals(((EventuelReponseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventuelReponseDTO{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", code='" + getCode() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", description='" + getDescription() + "'" +
            ", correctAnswer='" + isCorrectAnswer() + "'" +
            ", point=" + getPoint() +
            ", quizId=" + getQuizId() +
            "}";
    }
}
