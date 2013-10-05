/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "post", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findByPostID", query = "SELECT p FROM Post p WHERE p.postID = :postID"),
    @NamedQuery(name = "Post.findByPostTitle", query = "SELECT p FROM Post p WHERE p.postTitle = :postTitle"),
    @NamedQuery(name = "Post.findByPostDateCreate", query = "SELECT p FROM Post p WHERE p.postDateCreate = :postDateCreate"),
    @NamedQuery(name = "Post.findByPostDateEdit", query = "SELECT p FROM Post p WHERE p.postDateEdit = :postDateEdit"),
    @NamedQuery(name = "Post.findByStatus", query = "SELECT p FROM Post p WHERE p.status = :status"),
    @NamedQuery(name = "Post.findByIsDelete", query = "SELECT p FROM Post p WHERE p.isDelete = :isDelete")})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "postID", nullable = false)
    private Integer postID;
    @Size(max = 250)
    @Column(name = "postTitle", length = 250)
    private String postTitle;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "postContent", length = 2147483647)
    private String postContent;
    @Column(name = "postDateCreate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDateCreate;
    @Column(name = "postDateEdit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDateEdit;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JoinColumn(name = "topicID", referencedColumnName = "topicID")
    @ManyToOne
    private Topic topicID;
    @OneToMany(mappedBy = "postParent")
    private Collection<Post> postCollection;
    @JoinColumn(name = "postParent", referencedColumnName = "postID")
    @ManyToOne
    private Post postParent;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Login username;

    public Post() {
    }

    public Post(Integer postID) {
        this.postID = postID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostDateCreate() {
        return postDateCreate;
    }

    public void setPostDateCreate(Date postDateCreate) {
        this.postDateCreate = postDateCreate;
    }

    public Date getPostDateEdit() {
        return postDateEdit;
    }

    public void setPostDateEdit(Date postDateEdit) {
        this.postDateEdit = postDateEdit;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Topic getTopicID() {
        return topicID;
    }

    public void setTopicID(Topic topicID) {
        this.topicID = topicID;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    public Post getPostParent() {
        return postParent;
    }

    public void setPostParent(Post postParent) {
        this.postParent = postParent;
    }

    public Login getUsername() {
        return username;
    }

    public void setUsername(Login username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postID != null ? postID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.postID == null && other.postID != null) || (this.postID != null && !this.postID.equals(other.postID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Post[ postID=" + postID + " ]";
    }
    
}
