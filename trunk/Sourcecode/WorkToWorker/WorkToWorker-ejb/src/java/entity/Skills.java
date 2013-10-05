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
@Table(name = "skills", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skills.findAll", query = "SELECT s FROM Skills s"),
    @NamedQuery(name = "Skills.findBySkillID", query = "SELECT s FROM Skills s WHERE s.skillID = :skillID"),
    @NamedQuery(name = "Skills.findBySkillName", query = "SELECT s FROM Skills s WHERE s.skillName = :skillName"),
    @NamedQuery(name = "Skills.findByDescription", query = "SELECT s FROM Skills s WHERE s.description = :description"),
    @NamedQuery(name = "Skills.findByIsDelete", query = "SELECT s FROM Skills s WHERE s.isDelete = :isDelete")})
public class Skills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "skillID", nullable = false)
    private Integer skillID;
    @Size(max = 250)
    @Column(name = "skillName", length = 250)
    private String skillName;
    @Size(max = 2147483647)
    @Column(name = "description", length = 2147483647)
    private String description;
    @Column(name = "isDelete")
    private Boolean isDelete;

    public Skills() {
    }

    public Skills(Integer skillID) {
        this.skillID = skillID;
    }

    public Integer getSkillID() {
        return skillID;
    }

    public void setSkillID(Integer skillID) {
        this.skillID = skillID;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (skillID != null ? skillID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skills)) {
            return false;
        }
        Skills other = (Skills) object;
        if ((this.skillID == null && other.skillID != null) || (this.skillID != null && !this.skillID.equals(other.skillID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Skills[ skillID=" + skillID + " ]";
    }
    
}
