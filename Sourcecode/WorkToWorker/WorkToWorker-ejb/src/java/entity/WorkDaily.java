/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son
 */
@Entity
@Table(name = "workDaily", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkDaily.findAll", query = "SELECT w FROM WorkDaily w"),
    @NamedQuery(name = "WorkDaily.findByWorkID", query = "SELECT w FROM WorkDaily w WHERE w.workDailyPK.workID = :workID"),
    @NamedQuery(name = "WorkDaily.findByWorkerID", query = "SELECT w FROM WorkDaily w WHERE w.workDailyPK.workerID = :workerID"),
    @NamedQuery(name = "WorkDaily.findByStartDate", query = "SELECT w FROM WorkDaily w WHERE w.startDate = :startDate"),
    @NamedQuery(name = "WorkDaily.findByEndDate", query = "SELECT w FROM WorkDaily w WHERE w.endDate = :endDate"),
    @NamedQuery(name = "WorkDaily.findByMonday", query = "SELECT w FROM WorkDaily w WHERE w.monday = :monday"),
    @NamedQuery(name = "WorkDaily.findByTuesday", query = "SELECT w FROM WorkDaily w WHERE w.tuesday = :tuesday"),
    @NamedQuery(name = "WorkDaily.findByWednesday", query = "SELECT w FROM WorkDaily w WHERE w.wednesday = :wednesday"),
    @NamedQuery(name = "WorkDaily.findByThursday", query = "SELECT w FROM WorkDaily w WHERE w.thursday = :thursday"),
    @NamedQuery(name = "WorkDaily.findByFriday", query = "SELECT w FROM WorkDaily w WHERE w.friday = :friday"),
    @NamedQuery(name = "WorkDaily.findBySaturday", query = "SELECT w FROM WorkDaily w WHERE w.saturday = :saturday"),
    @NamedQuery(name = "WorkDaily.findBySunday", query = "SELECT w FROM WorkDaily w WHERE w.sunday = :sunday"),
    @NamedQuery(name = "WorkDaily.findByIsDelete", query = "SELECT w FROM WorkDaily w WHERE w.isDelete = :isDelete")})
public class WorkDaily implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WorkDailyPK workDailyPK;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Size(max = 10)
    @Column(name = "Monday", length = 10)
    private String monday;
    @Size(max = 10)
    @Column(name = "Tuesday", length = 10)
    private String tuesday;
    @Size(max = 10)
    @Column(name = "Wednesday", length = 10)
    private String wednesday;
    @Size(max = 10)
    @Column(name = "Thursday", length = 10)
    private String thursday;
    @Size(max = 10)
    @Column(name = "Friday", length = 10)
    private String friday;
    @Size(max = 10)
    @Column(name = "Saturday", length = 10)
    private String saturday;
    @Size(max = 10)
    @Column(name = "Sunday", length = 10)
    private String sunday;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JoinColumn(name = "workerID", referencedColumnName = "workerID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Worker worker;
    @JoinColumn(name = "workID", referencedColumnName = "workID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Work work;

    public WorkDaily() {
    }

    public WorkDaily(WorkDailyPK workDailyPK) {
        this.workDailyPK = workDailyPK;
    }

    public WorkDaily(int workID, int workerID) {
        this.workDailyPK = new WorkDailyPK(workID, workerID);
    }

    public WorkDailyPK getWorkDailyPK() {
        return workDailyPK;
    }

    public void setWorkDailyPK(WorkDailyPK workDailyPK) {
        this.workDailyPK = workDailyPK;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
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

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workDailyPK != null ? workDailyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkDaily)) {
            return false;
        }
        WorkDaily other = (WorkDaily) object;
        if ((this.workDailyPK == null && other.workDailyPK != null) || (this.workDailyPK != null && !this.workDailyPK.equals(other.workDailyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WorkDaily[ workDailyPK=" + workDailyPK + " ]";
    }
    
}
