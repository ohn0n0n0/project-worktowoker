/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "worker", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Worker.findAll", query = "SELECT w FROM Worker w"),
    @NamedQuery(name = "Worker.findByWorkerID", query = "SELECT w FROM Worker w WHERE w.workerID = :workerID"),
    @NamedQuery(name = "Worker.findByQualification", query = "SELECT w FROM Worker w WHERE w.qualification = :qualification"),
    @NamedQuery(name = "Worker.findBySkillName", query = "SELECT w FROM Worker w WHERE w.skillName = :skillName"),
    @NamedQuery(name = "Worker.findByChargesHrs", query = "SELECT w FROM Worker w WHERE w.chargesHrs = :chargesHrs"),
    @NamedQuery(name = "Worker.findByChargesDaily", query = "SELECT w FROM Worker w WHERE w.chargesDaily = :chargesDaily"),
    @NamedQuery(name = "Worker.findByIsDelete", query = "SELECT w FROM Worker w WHERE w.isDelete = :isDelete")})
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "workerID", nullable = false)
    private Integer workerID;
    @Size(max = 250)
    @Column(name = "qualification", length = 250)
    private String qualification;
    @Size(max = 30)
    @Column(name = "skillName", length = 30)
    private String skillName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "chargesHrs", precision = 19, scale = 4)
    private BigDecimal chargesHrs;
    @Column(name = "chargesDaily", precision = 19, scale = 4)
    private BigDecimal chargesDaily;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker")
    private Collection<WorkDaily> workDailyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "worker")
    private Collection<GroupDetails> groupDetailsCollection;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Login username;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "worker")
    private Rating rating;

    public Worker() {
    }

    public Worker(Integer workerID) {
        this.workerID = workerID;
    }

    public Integer getWorkerID() {
        return workerID;
    }

    public void setWorkerID(Integer workerID) {
        this.workerID = workerID;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public BigDecimal getChargesHrs() {
        return chargesHrs;
    }

    public void setChargesHrs(BigDecimal chargesHrs) {
        this.chargesHrs = chargesHrs;
    }

    public BigDecimal getChargesDaily() {
        return chargesDaily;
    }

    public void setChargesDaily(BigDecimal chargesDaily) {
        this.chargesDaily = chargesDaily;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @XmlTransient
    public Collection<WorkDaily> getWorkDailyCollection() {
        return workDailyCollection;
    }

    public void setWorkDailyCollection(Collection<WorkDaily> workDailyCollection) {
        this.workDailyCollection = workDailyCollection;
    }

    @XmlTransient
    public Collection<GroupDetails> getGroupDetailsCollection() {
        return groupDetailsCollection;
    }

    public void setGroupDetailsCollection(Collection<GroupDetails> groupDetailsCollection) {
        this.groupDetailsCollection = groupDetailsCollection;
    }

    public Login getUsername() {
        return username;
    }

    public void setUsername(Login username) {
        this.username = username;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
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
        if (!(object instanceof Worker)) {
            return false;
        }
        Worker other = (Worker) object;
        if ((this.workerID == null && other.workerID != null) || (this.workerID != null && !this.workerID.equals(other.workerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Worker[ workerID=" + workerID + " ]";
    }
    
}
