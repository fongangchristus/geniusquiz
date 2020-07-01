package com.it4innov.geniusquiz.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A CategorieFormation.
 */
@Entity
@Table(name = "categorie_formation")
public class CategorieFormation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libele")
    private String libele;

    @Column(name = "description")
    private String description;

    @Column(name = "image_couverture")
    private String imageCouverture;

    @Column(name = "code")
    private String code;

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

    public CategorieFormation libele(String libele) {
        this.libele = libele;
        return this;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getDescription() {
        return description;
    }

    public CategorieFormation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public CategorieFormation imageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
        return this;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public String getCode() {
        return code;
    }

    public CategorieFormation code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategorieFormation)) {
            return false;
        }
        return id != null && id.equals(((CategorieFormation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategorieFormation{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
