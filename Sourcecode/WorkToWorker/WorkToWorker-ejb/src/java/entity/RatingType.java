/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Thanh-Shark
 */
@Entity
@Table(name = "ratingType", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RatingType.findAll", query = "SELECT r FROM RatingType r"),
    @NamedQuery(name = "RatingType.findByRatingID", query = "SELECT r FROM RatingType r WHERE r.ratingID = :ratingID"),
    @NamedQuery(name = "RatingType.findByRatingName", query = "SELECT r FROM RatingType r WHERE r.ratingName = :ratingName"),
    @NamedQuery(name = "RatingType.findByIsDelete", query = "SELECT r FROM RatingType r WHERE r.isDelete = :isDelete")})
public class RatingType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratingID", nullable = false)
    private Integer ratingID;
    @Size(max = 250)
    @Column(name = "ratingName", length = 250)
    private String ratingName;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @OneToMany(mappedBy = "ratingID")
    private Collection<Rating> ratingCollection;

    public RatingType() {
    }

    public RatingType(Integer ratingID) {
        this.ratingID = ratingID;
    }

    public Integer getRatingID() {
        return ratingID;
    }

    public void setRatingID(Integer ratingID) {
        this.ratingID = ratingID;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @XmlTransient
    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ratingID != null ? ratingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RatingType)) {
            return false;
        }
        RatingType other = (RatingType) object;
        if ((this.ratingID == null && other.ratingID != null) || (this.ratingID != null && !this.ratingID.equals(other.ratingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RatingType[ ratingID=" + ratingID + " ]";
    }
    
}
