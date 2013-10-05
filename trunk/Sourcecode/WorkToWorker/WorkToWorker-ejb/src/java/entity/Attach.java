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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thanh-Shark
 */
@Entity
@Table(name = "attach", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attach.findAll", query = "SELECT a FROM Attach a"),
    @NamedQuery(name = "Attach.findByAttachID", query = "SELECT a FROM Attach a WHERE a.attachID = :attachID"),
    @NamedQuery(name = "Attach.findByPostID", query = "SELECT a FROM Attach a WHERE a.postID = :postID"),
    @NamedQuery(name = "Attach.findByAttachURL", query = "SELECT a FROM Attach a WHERE a.attachURL = :attachURL"),
    @NamedQuery(name = "Attach.findByIsDelete", query = "SELECT a FROM Attach a WHERE a.isDelete = :isDelete")})
public class Attach implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "attachID", nullable = false)
    private Integer attachID;
    @Column(name = "postID")
    private Integer postID;
    @Size(max = 250)
    @Column(name = "attachURL", length = 250)
    private String attachURL;
    @Column(name = "isDelete")
    private Boolean isDelete;

    public Attach() {
    }

    public Attach(Integer attachID) {
        this.attachID = attachID;
    }

    public Integer getAttachID() {
        return attachID;
    }

    public void setAttachID(Integer attachID) {
        this.attachID = attachID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getAttachURL() {
        return attachURL;
    }

    public void setAttachURL(String attachURL) {
        this.attachURL = attachURL;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attachID != null ? attachID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attach)) {
            return false;
        }
        Attach other = (Attach) object;
        if ((this.attachID == null && other.attachID != null) || (this.attachID != null && !this.attachID.equals(other.attachID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Attach[ attachID=" + attachID + " ]";
    }
    
}
