package com.it4innov.geniusquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Classe.
 */
@Entity
@Table(name = "classe")
public class Classe implements Serializable {

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

    @ManyToOne
    @JsonIgnoreProperties(value = "classes", allowSetters = true)
    private Niveau niveau;

    @ManyToOne
    @JsonIgnoreProperties(value = "classes", allowSetters = true)
    private Option option;

    @ManyToOne
    @JsonIgnoreProperties(value = "classes", allowSetters = true)
    private CategorieFormation categorieFormation;

    @ManyToMany
    @JoinTable(name = "classe_matiere",
               joinColumns = @JoinColumn(name = "classe_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "matiere_id", referencedColumnName = "id"))
    private Set<Matiere> matieres = new HashSet<>();

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

    public Classe libele(String libele) {
        this.libele = libele;
        return this;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public String getDescription() {
        return description;
    }

    public Classe description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageCouverture() {
        return imageCouverture;
    }

    public Classe imageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
        return this;
    }

    public void setImageCouverture(String imageCouverture) {
        this.imageCouverture = imageCouverture;
    }

    public String getCode() {
        return code;
    }

    public Classe code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public Classe niveau(Niveau niveau) {
        this.niveau = niveau;
        return this;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Option getOption() {
        return option;
    }

    public Classe option(Option option) {
        this.option = option;
        return this;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public CategorieFormation getCategorieFormation() {
        return categorieFormation;
    }

    public Classe categorieFormation(CategorieFormation categorieFormation) {
        this.categorieFormation = categorieFormation;
        return this;
    }

    public void setCategorieFormation(CategorieFormation categorieFormation) {
        this.categorieFormation = categorieFormation;
    }

    public Set<Matiere> getMatieres() {
        return matieres;
    }

    public Classe matieres(Set<Matiere> matieres) {
        this.matieres = matieres;
        return this;
    }

    public Classe addMatiere(Matiere matiere) {
        this.matieres.add(matiere);
        matiere.getClasses().add(this);
        return this;
    }

    public Classe removeMatiere(Matiere matiere) {
        this.matieres.remove(matiere);
        matiere.getClasses().remove(this);
        return this;
    }

    public void setMatieres(Set<Matiere> matieres) {
        this.matieres = matieres;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Classe)) {
            return false;
        }
        return id != null && id.equals(((Classe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Classe{" +
            "id=" + getId() +
            ", libele='" + getLibele() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageCouverture='" + getImageCouverture() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
