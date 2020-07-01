package com.it4innov.geniusquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Matiere.
 */
@Entity
@Table(name = "matiere")
public class Matiere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libele")
    private String libele;

    @Column(name = "description")
    private String description;

    @Column(name = "validite")
    private String validite;

    @Column(name = "image_couverture")
    private String imageCouverture;

    @Column(name = "id_organisation")
    private Long idOrganisation;

    @ManyToMany(mappedBy = "matieres")
    @JsonIgnore
    private Set<Classe> classes = new HashSet<>();

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

    public Matiere libele(String libele) {
        this.libele = libele;
        return this;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getDescription() {
        return description;
    }

    public Matiere description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValidite() {
        return validite;
    }

    public Matiere validite(String validite) {
        this.validite = validite;
        return this;
    }

    public void setValidite(String validite) {
        this.validite = validite;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public Matiere imageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
        return this;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public Long getIdOrganisation() {
        return idOrganisation;
    }

    public Matiere idOrganisation(Long idOrganisation) {
        this.idOrganisation = idOrganisation;
        return this;
    }

    public void setIdOrganisation(Long idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    public Set<Classe> getClasses() {
        return classes;
    }

    public Matiere classes(Set<Classe> classes) {
        this.classes = classes;
        return this;
    }

    public Matiere addClasse(Classe classe) {
        this.classes.add(classe);
        classe.getMatieres().add(this);
        return this;
    }

    public Matiere removeClasse(Classe classe) {
        this.classes.remove(classe);
        classe.getMatieres().remove(this);
        return this;
    }

    public void setClasses(Set<Classe> classes) {
        this.classes = classes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Matiere)) {
            return false;
        }
        return id != null && id.equals(((Matiere) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Matiere{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", description='" + getDescription() + "'" +
            ", validite='" + getValidite() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", idOrganisation=" + getIdOrganisation() +
            "}";
    }
}
