package com.it4innov.geniusquiz.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.Classe} entity.
 */
public class ClasseDTO implements Serializable {
    
    private Long id;

    private String libele;

    private String description;

    private String imageCouverture;

    private String code;


    private Long niveauId;

    private Long optionId;

    private Long categorieFormationId;
    private Set<MatiereDTO> matieres = new HashSet<>();
    
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

    public Long getNiveauId() {
        return niveauId;
    }

    public void setNiveauId(Long niveauId) {
        this.niveauId = niveauId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getCategorieFormationId() {
        return categorieFormationId;
    }

    public void setCategorieFormationId(Long categorieFormationId) {
        this.categorieFormationId = categorieFormationId;
    }

    public Set<MatiereDTO> getMatieres() {
        return matieres;
    }

    public void setMatieres(Set<MatiereDTO> matieres) {
        this.matieres = matieres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClasseDTO)) {
            return false;
        }

        return id != null && id.equals(((ClasseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClasseDTO{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", code='" + getCode() + "'" +
            ", niveauId=" + getNiveauId() +
            ", optionId=" + getOptionId() +
            ", categorieFormationId=" + getCategorieFormationId() +
            ", matieres='" + getMatieres() + "'" +
            "}";
    }
}
