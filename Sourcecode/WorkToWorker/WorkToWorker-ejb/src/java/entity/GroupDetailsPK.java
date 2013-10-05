/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Thanh-Shark
 */
@Embeddable
public class GroupDetailsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "groupID", nullable = false)
    private int groupID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "workerID", nullable = false)
    private int workerID;

    public GroupDetailsPK() {
    }

    public GroupDetailsPK(int groupID, int workerID) {
        this.groupID = groupID;
        this.workerID = workerID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) groupID;
        hash += (int) workerID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupDetailsPK)) {
            return false;
        }
        GroupDetailsPK other = (GroupDetailsPK) object;
        if (this.groupID != other.groupID) {
            return false;
        }
        if (this.workerID != other.workerID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupDetailsPK[ groupID=" + groupID + ", workerID=" + workerID + " ]";
    }
    
}
