/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thanh-Shark
 */
@Entity
@Table(name = "groupDetails", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupDetails.findAll", query = "SELECT g FROM GroupDetails g"),
    @NamedQuery(name = "GroupDetails.findByGroupID", query = "SELECT g FROM GroupDetails g WHERE g.groupDetailsPK.groupID = :groupID"),
    @NamedQuery(name = "GroupDetails.findByWorkerID", query = "SELECT g FROM GroupDetails g WHERE g.groupDetailsPK.workerID = :workerID"),
    @NamedQuery(name = "GroupDetails.findByIsDelete", query = "SELECT g FROM GroupDetails g WHERE g.isDelete = :isDelete")})
public class GroupDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupDetailsPK groupDetailsPK;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JoinColumn(name = "workerID", referencedColumnName = "workerID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Worker worker;
    @JoinColumn(name = "groupID", referencedColumnName = "groupID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Groups groups;

    public GroupDetails() {
    }

    public GroupDetails(GroupDetailsPK groupDetailsPK) {
        this.groupDetailsPK = groupDetailsPK;
    }

    public GroupDetails(int groupID, int workerID) {
        this.groupDetailsPK = new GroupDetailsPK(groupID, workerID);
    }

    public GroupDetailsPK getGroupDetailsPK() {
        return groupDetailsPK;
    }

    public void setGroupDetailsPK(GroupDetailsPK groupDetailsPK) {
        this.groupDetailsPK = groupDetailsPK;
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

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupDetailsPK != null ? groupDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupDetails)) {
            return false;
        }
        GroupDetails other = (GroupDetails) object;
        if ((this.groupDetailsPK == null && other.groupDetailsPK != null) || (this.groupDetailsPK != null && !this.groupDetailsPK.equals(other.groupDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupDetails[ groupDetailsPK=" + groupDetailsPK + " ]";
    }
    
}
