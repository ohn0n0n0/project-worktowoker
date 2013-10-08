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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son
 */
@Entity
@Table(name = "mediaWork", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MediaWork.findAll", query = "SELECT m FROM MediaWork m"),
    @NamedQuery(name = "MediaWork.findByMediaID", query = "SELECT m FROM MediaWork m WHERE m.mediaID = :mediaID"),
    @NamedQuery(name = "MediaWork.findByUrl", query = "SELECT m FROM MediaWork m WHERE m.url = :url"),
    @NamedQuery(name = "MediaWork.findByIsDelete", query = "SELECT m FROM MediaWork m WHERE m.isDelete = :isDelete")})
public class MediaWork implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "mediaID", nullable = false)
    private Integer mediaID;
    @Size(max = 250)
    @Column(name = "url", length = 250)
    private String url;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JoinColumn(name = "workID", referencedColumnName = "workID")
    @ManyToOne
    private Work workID;

    public MediaWork() {
    }

    public MediaWork(Integer mediaID) {
        this.mediaID = mediaID;
    }

    public Integer getMediaID() {
        return mediaID;
    }

    public void setMediaID(Integer mediaID) {
        this.mediaID = mediaID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Work getWorkID() {
        return workID;
    }

    public void setWorkID(Work workID) {
        this.workID = workID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mediaID != null ? mediaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MediaWork)) {
            return false;
        }
        MediaWork other = (MediaWork) object;
        if ((this.mediaID == null && other.mediaID != null) || (this.mediaID != null && !this.mediaID.equals(other.mediaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MediaWork[ mediaID=" + mediaID + " ]";
    }
    
}
