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
@Table(name = "reportType", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportType.findAll", query = "SELECT r FROM ReportType r"),
    @NamedQuery(name = "ReportType.findByReportTypeID", query = "SELECT r FROM ReportType r WHERE r.reportTypeID = :reportTypeID"),
    @NamedQuery(name = "ReportType.findByReportTypeName", query = "SELECT r FROM ReportType r WHERE r.reportTypeName = :reportTypeName"),
    @NamedQuery(name = "ReportType.findByIsDelete", query = "SELECT r FROM ReportType r WHERE r.isDelete = :isDelete")})
public class ReportType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "reportTypeID", nullable = false)
    private Integer reportTypeID;
    @Size(max = 250)
    @Column(name = "reportTypeName", length = 250)
    private String reportTypeName;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @OneToMany(mappedBy = "reportTypeID")
    private Collection<Report> reportCollection;

    public ReportType() {
    }

    public ReportType(Integer reportTypeID) {
        this.reportTypeID = reportTypeID;
    }

    public Integer getReportTypeID() {
        return reportTypeID;
    }

    public void setReportTypeID(Integer reportTypeID) {
        this.reportTypeID = reportTypeID;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportTypeID != null ? reportTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportType)) {
            return false;
        }
        ReportType other = (ReportType) object;
        if ((this.reportTypeID == null && other.reportTypeID != null) || (this.reportTypeID != null && !this.reportTypeID.equals(other.reportTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReportType[ reportTypeID=" + reportTypeID + " ]";
    }
    
}
