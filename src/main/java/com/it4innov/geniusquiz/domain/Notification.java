package com.it4innov.geniusquiz.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.it4innov.geniusquiz.domain.enumeration.TypeNotification;

/**
 * A Notification.
 */
@Entity
@Table(name = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre")
    private String titre;

    @Column(name = "sous_titre")
    private String sousTitre;

    @Column(name = "details")
    private String details;

    @Column(name = "date_notif")
    private Instant dateNotif;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_notification")
    private TypeNotification typeNotification;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public Notification titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public Notification sousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
        return this;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getDetails() {
        return details;
    }

    public Notification details(String details) {
        this.details = details;
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getDateNotif() {
        return dateNotif;
    }

    public Notification dateNotif(Instant dateNotif) {
        this.dateNotif = dateNotif;
        return this;
    }

    public void setDateNotif(Instant dateNotif) {
        this.dateNotif = dateNotif;
    }

    public String getCode() {
        return code;
    }

    public Notification code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public Notification typeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
        return this;
    }

    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notification)) {
            return false;
        }
        return id != null && id.equals(((Notification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", sousTitre='" + getSousTitre() + "'" +
            ", details='" + getDetails() + "'" +
            ", dateNotif='" + getDateNotif() + "'" +
            ", code='" + getCode() + "'" +
            ", typeNotification='" + getTypeNotification() + "'" +
            "}";
    }
}
