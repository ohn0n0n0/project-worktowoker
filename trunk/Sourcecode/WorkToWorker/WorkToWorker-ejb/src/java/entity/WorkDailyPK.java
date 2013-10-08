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
 * @author Son
 */
@Embeddable
public class WorkDailyPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "workID", nullable = false)
    private int workID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "workerID", nullable = false)
    private int workerID;

    public WorkDailyPK() {
    }

    public WorkDailyPK(int workID, int workerID) {
        this.workID = workID;
        this.workerID = workerID;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
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
        hash += (int) workID;
        hash += (int) workerID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkDailyPK)) {
            return false;
        }
        WorkDailyPK other = (WorkDailyPK) object;
        if (this.workID != other.workID) {
            return false;
        }
        if (this.workerID != other.workerID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WorkDailyPK[ workID=" + workID + ", workerID=" + workerID + " ]";
    }
    
}
