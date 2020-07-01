package com.it4innov.geniusquiz.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.Chapitre} entity.
 */
public class ChapitreDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libele;

    private String description;

    private String imageCouverture;

    private String fichierCours;


    private Long matiereId;
    
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

    public String getFichierCours() {
        return fichierCours;
    }

    public void setFichierCours(String fichierCours) {
        this.fichierCours = fichierCours;
    }

    public Long getMatiereId() {
        return matiereId;
    }

    public void setMatiereId(Long matiereId) {
        this.matiereId = matiereId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChapitreDTO)) {
            return false;
        }

        return id != null && id.equals(((ChapitreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChapitreDTO{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", fichierCours='" + getFichierCours() + "'" +
            ", matiereId=" + getMatiereId() +
            "}";
    }
}
