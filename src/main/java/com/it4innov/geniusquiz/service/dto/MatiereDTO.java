package com.it4innov.geniusquiz.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.Matiere} entity.
 */
public class MatiereDTO implements Serializable {
    
    private Long id;

    private String libele;

    private String description;

    private String validite;

    private String imageCouverture;

    private Long idOrganisation;

    
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

    public String getValidite() {
        return validite;
    }

    public void setValidite(String validite) {
        this.validite = validite;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public Long getIdOrganisation() {
        return idOrganisation;
    }

    public void setIdOrganisation(Long idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MatiereDTO)) {
            return false;
        }

        return id != null && id.equals(((MatiereDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MatiereDTO{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", description='" + getDescription() + "'" +
            ", validite='" + getValidite() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", idOrganisation=" + getIdOrganisation() +
            "}";
    }
}
