package com.it4innov.geniusquiz.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.it4innov.geniusquiz.domain.enumeration.TypeNotification;

/**
 * A DTO for the {@link com.it4innov.geniusquiz.domain.Notification} entity.
 */
public class NotificationDTO implements Serializable {
    
    private Long id;

    private String titre;

    private String sousTitre;

    private String details;

    private Instant dateNotif;

    private String code;

    private TypeNotification typeNotification;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getDateNotif() {
        return dateNotif;
    }

    public void setDateNotif(Instant dateNotif) {
        this.dateNotif = dateNotif;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationDTO)) {
            return false;
        }

        return id != null && id.equals(((NotificationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotificationDTO{" +
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
