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
 * @author Thanh-Shark
 */
@Entity
@Table(name = "report", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByReportID", query = "SELECT r FROM Report r WHERE r.reportID = :reportID"),
    @NamedQuery(name = "Report.findByPostID", query = "SELECT r FROM Report r WHERE r.postID = :postID"),
    @NamedQuery(name = "Report.findByContent", query = "SELECT r FROM Report r WHERE r.content = :content"),
    @NamedQuery(name = "Report.findByIsDelete", query = "SELECT r FROM Report r WHERE r.isDelete = :isDelete")})
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "reportID", nullable = false)
    private Integer reportID;
    @Column(name = "postID")
    private Integer postID;
    @Size(max = 250)
    @Column(name = "content", length = 250)
    private String content;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JoinColumn(name = "reportTypeID", referencedColumnName = "reportTypeID")
    @ManyToOne
    private ReportType reportTypeID;

    public Report() {
    }

    public Report(Integer reportID) {
        this.reportID = reportID;
    }

    public Integer getReportID() {
        return reportID;
    }

    public void setReportID(Integer reportID) {
        this.reportID = reportID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ReportType getReportTypeID() {
        return reportTypeID;
    }

    public void setReportTypeID(ReportType reportTypeID) {
        this.reportTypeID = reportTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportID != null ? reportID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportID == null && other.reportID != null) || (this.reportID != null && !this.reportID.equals(other.reportID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Report[ reportID=" + reportID + " ]";
    }
    
}
