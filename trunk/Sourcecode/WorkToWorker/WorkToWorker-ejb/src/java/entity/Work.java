/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Thanh-Shark
 */
@Entity
@Table(name = "work", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Work.findAll", query = "SELECT w FROM Work w"),
    @NamedQuery(name = "Work.findByWorkID", query = "SELECT w FROM Work w WHERE w.workID = :workID"),
    @NamedQuery(name = "Work.findBySkillName", query = "SELECT w FROM Work w WHERE w.skillName = :skillName"),
    @NamedQuery(name = "Work.findByWorkAddress", query = "SELECT w FROM Work w WHERE w.workAddress = :workAddress"),
    @NamedQuery(name = "Work.findByWorkCountry", query = "SELECT w FROM Work w WHERE w.workCountry = :workCountry"),
    @NamedQuery(name = "Work.findByWorkCity", query = "SELECT w FROM Work w WHERE w.workCity = :workCity"),
    @NamedQuery(name = "Work.findByWorkLoc", query = "SELECT w FROM Work w WHERE w.workLoc = :workLoc"),
    @NamedQuery(name = "Work.findByStartDate", query = "SELECT w FROM Work w WHERE w.startDate = :startDate"),
    @NamedQuery(name = "Work.findByEndDate", query = "SELECT w FROM Work w WHERE w.endDate = :endDate"),
    @NamedQuery(name = "Work.findByStartTime", query = "SELECT w FROM Work w WHERE w.startTime = :startTime"),
    @NamedQuery(name = "Work.findByEndTime", query = "SELECT w FROM Work w WHERE w.endTime = :endTime"),
    @NamedQuery(name = "Work.findByStatus", query = "SELECT w FROM Work w WHERE w.status = :status"),
    @NamedQuery(name = "Work.findByFeedback", query = "SELECT w FROM Work w WHERE w.feedback = :feedback"),
    @NamedQuery(name = "Work.findByWorkDetails", query = "SELECT w FROM Work w WHERE w.workDetails = :workDetails"),
    @NamedQuery(name = "Work.findByChargesHrs", query = "SELECT w FROM Work w WHERE w.chargesHrs = :chargesHrs"),
    @NamedQuery(name = "Work.findByChargesDaily", query = "SELECT w FROM Work w WHERE w.chargesDaily = :chargesDaily"),
    @NamedQuery(name = "Work.findByIsDelete", query = "SELECT w FROM Work w WHERE w.isDelete = :isDelete")})
public class Work implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "workID", nullable = false)
    private Integer workID;
    @Size(max = 50)
    @Column(name = "skillName", length = 50)
    private String skillName;
    @Size(max = 2147483647)
    @Column(name = "workAddress", length = 2147483647)
    private String workAddress;
    @Size(max = 250)
    @Column(name = "workCountry", length = 250)
    private String workCountry;
    @Size(max = 250)
    @Column(name = "workCity", length = 250)
    private String workCity;
    @Size(max = 250)
    @Column(name = "workLoc", length = 250)
    private String workLoc;
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Column(name = "status")
    private Integer status;
    @Size(max = 4000)
    @Column(name = "feedback", length = 4000)
    private String feedback;
    @Size(max = 50)
    @Column(name = "workDetails", length = 50)
    private String workDetails;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "chargesHrs", precision = 19, scale = 4)
    private BigDecimal chargesHrs;
    @Column(name = "chargesDaily", precision = 19, scale = 4)
    private BigDecimal chargesDaily;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Login username;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "work")
    private Collection<WorkDaily> workDailyCollection;
    @OneToMany(mappedBy = "workID")
    private Collection<MediaWork> mediaWorkCollection;

    public Work() {
    }

    public Work(Integer workID) {
        this.workID = workID;
    }

    public Integer getWorkID() {
        return workID;
    }

    public void setWorkID(Integer workID) {
        this.workID = workID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getWorkCountry() {
        return workCountry;
    }

    public void setWorkCountry(String workCountry) {
        this.workCountry = workCountry;
    }

    public String getWorkCity() {
        return workCity;
    }

    public void setWorkCity(String workCity) {
        this.workCity = workCity;
    }

    public String getWorkLoc() {
        return workLoc;
    }

    public void setWorkLoc(String workLoc) {
        this.workLoc = workLoc;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getWorkDetails() {
        return workDetails;
    }

    public void setWorkDetails(String workDetails) {
        this.workDetails = workDetails;
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

    public Login getUsername() {
        return username;
    }

    public void setUsername(Login username) {
        this.username = username;
    }

    @XmlTransient
    public Collection<WorkDaily> getWorkDailyCollection() {
        return workDailyCollection;
    }

    public void setWorkDailyCollection(Collection<WorkDaily> workDailyCollection) {
        this.workDailyCollection = workDailyCollection;
    }

    @XmlTransient
    public Collection<MediaWork> getMediaWorkCollection() {
        return mediaWorkCollection;
    }

    public void setMediaWorkCollection(Collection<MediaWork> mediaWorkCollection) {
        this.mediaWorkCollection = mediaWorkCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workID != null ? workID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Work)) {
            return false;
        }
        Work other = (Work) object;
        if ((this.workID == null && other.workID != null) || (this.workID != null && !this.workID.equals(other.workID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Work[ workID=" + workID + " ]";
    }
    
}
