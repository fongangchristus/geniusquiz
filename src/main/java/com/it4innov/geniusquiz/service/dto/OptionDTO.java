package com.it4innov.geniusquiz.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.Option} entity.
 */
public class OptionDTO implements Serializable {
    
    private Long id;

    private String libele;

    private String description;

    private String imageCouverture;

    private String code;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long cursusId) {
        this.quizId = cursusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OptionDTO)) {
            return false;
        }

        return id != null && id.equals(((OptionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OptionDTO{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", code='" + getCode() + "'" +
            ", quizId=" + getQuizId() +
            "}";
    }
}
