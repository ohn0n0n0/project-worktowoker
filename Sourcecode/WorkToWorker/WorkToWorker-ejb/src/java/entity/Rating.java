/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son
 */
@Entity
@Table(name = "rating", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r"),
    @NamedQuery(name = "Rating.findByWorkerID", query = "SELECT r FROM Rating r WHERE r.workerID = :workerID"),
    @NamedQuery(name = "Rating.findByScore", query = "SELECT r FROM Rating r WHERE r.score = :score"),
    @NamedQuery(name = "Rating.findByIsDelete", query = "SELECT r FROM Rating r WHERE r.isDelete = :isDelete")})
public class Rating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "workerID", nullable = false)
    private Integer workerID;
    @Column(name = "score")
    private Integer score;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JoinColumn(name = "workerID", referencedColumnName = "workerID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Worker worker;
    @JoinColumn(name = "ratingID", referencedColumnName = "ratingID")
    @ManyToOne
    private RatingType ratingID;

    public Rating() {
    }

    public Rating(Integer workerID) {
        this.workerID = workerID;
    }

    public Integer getWorkerID() {
        return workerID;
    }

    public void setWorkerID(Integer workerID) {
        this.workerID = workerID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public RatingType getRatingID() {
        return ratingID;
    }

    public void setRatingID(RatingType ratingID) {
        this.ratingID = ratingID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workerID != null ? workerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.workerID == null && other.workerID != null) || (this.workerID != null && !this.workerID.equals(other.workerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rating[ workerID=" + workerID + " ]";
    }
    
}
