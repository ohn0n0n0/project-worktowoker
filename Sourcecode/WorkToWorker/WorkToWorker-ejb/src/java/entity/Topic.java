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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Son
 */
@Entity
@Table(name = "topic", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t"),
    @NamedQuery(name = "Topic.findByTopicID", query = "SELECT t FROM Topic t WHERE t.topicID = :topicID"),
    @NamedQuery(name = "Topic.findByTopicName", query = "SELECT t FROM Topic t WHERE t.topicName = :topicName"),
    @NamedQuery(name = "Topic.findByTopicOrder", query = "SELECT t FROM Topic t WHERE t.topicOrder = :topicOrder"),
    @NamedQuery(name = "Topic.findByIsDelete", query = "SELECT t FROM Topic t WHERE t.isDelete = :isDelete")})
public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "topicID", nullable = false)
    private Integer topicID;
    @Size(max = 250)
    @Column(name = "topicName", length = 250)
    private String topicName;
    @Column(name = "topicOrder")
    private Integer topicOrder;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @OneToMany(mappedBy = "topicParent")
    private Collection<Topic> topicCollection;
    @JoinColumn(name = "topicParent", referencedColumnName = "topicID")
    @ManyToOne
    private Topic topicParent;
    @OneToMany(mappedBy = "topicID")
    private Collection<Post> postCollection;

    public Topic() {
    }

    public Topic(Integer topicID) {
        this.topicID = topicID;
    }

    public Integer getTopicID() {
        return topicID;
    }

    public void setTopicID(Integer topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getTopicOrder() {
        return topicOrder;
    }

    public void setTopicOrder(Integer topicOrder) {
        this.topicOrder = topicOrder;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @XmlTransient
    public Collection<Topic> getTopicCollection() {
        return topicCollection;
    }

    public void setTopicCollection(Collection<Topic> topicCollection) {
        this.topicCollection = topicCollection;
    }

    public Topic getTopicParent() {
        return topicParent;
    }

    public void setTopicParent(Topic topicParent) {
        this.topicParent = topicParent;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (topicID != null ? topicID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.topicID == null && other.topicID != null) || (this.topicID != null && !this.topicID.equals(other.topicID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Topic[ topicID=" + topicID + " ]";
    }
    
}
