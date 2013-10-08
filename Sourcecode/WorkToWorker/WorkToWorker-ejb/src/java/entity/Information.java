/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Son
 */
@Entity
@Table(name = "information", catalog = "WorkToWorker", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Information.findAll", query = "SELECT i FROM Information i"),
    @NamedQuery(name = "Information.findByUsername", query = "SELECT i FROM Information i WHERE i.username = :username"),
    @NamedQuery(name = "Information.findByFirstName", query = "SELECT i FROM Information i WHERE i.firstName = :firstName"),
    @NamedQuery(name = "Information.findByLastName", query = "SELECT i FROM Information i WHERE i.lastName = :lastName"),
    @NamedQuery(name = "Information.findByGender", query = "SELECT i FROM Information i WHERE i.gender = :gender"),
    @NamedQuery(name = "Information.findByDob", query = "SELECT i FROM Information i WHERE i.dob = :dob"),
    @NamedQuery(name = "Information.findByAddress", query = "SELECT i FROM Information i WHERE i.address = :address"),
    @NamedQuery(name = "Information.findByCity", query = "SELECT i FROM Information i WHERE i.city = :city"),
    @NamedQuery(name = "Information.findByCountry", query = "SELECT i FROM Information i WHERE i.country = :country"),
    @NamedQuery(name = "Information.findByPhone", query = "SELECT i FROM Information i WHERE i.phone = :phone"),
    @NamedQuery(name = "Information.findByProofID", query = "SELECT i FROM Information i WHERE i.proofID = :proofID"),
    @NamedQuery(name = "Information.findBySecurityQuestion", query = "SELECT i FROM Information i WHERE i.securityQuestion = :securityQuestion"),
    @NamedQuery(name = "Information.findBySecurityAnswer", query = "SELECT i FROM Information i WHERE i.securityAnswer = :securityAnswer"),
    @NamedQuery(name = "Information.findByAvatar", query = "SELECT i FROM Information i WHERE i.avatar = :avatar"),
    @NamedQuery(name = "Information.findByIsDelete", query = "SELECT i FROM Information i WHERE i.isDelete = :isDelete")})
public class Information implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "username", nullable = false, length = 30)
    private String username;
    @Size(max = 30)
    @Column(name = "firstName", length = 30)
    private String firstName;
    @Size(max = 30)
    @Column(name = "lastName", length = 30)
    private String lastName;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob;
    @Size(max = 2147483647)
    @Column(name = "address", length = 2147483647)
    private String address;
    @Size(max = 100)
    @Column(name = "city", length = 100)
    private String city;
    @Size(max = 100)
    @Column(name = "country", length = 100)
    private String country;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;
    @Size(max = 50)
    @Column(name = "proofID", length = 50)
    private String proofID;
    @Size(max = 250)
    @Column(name = "securityQuestion", length = 250)
    private String securityQuestion;
    @Size(max = 250)
    @Column(name = "securityAnswer", length = 250)
    private String securityAnswer;
    @Size(max = 250)
    @Column(name = "avatar", length = 250)
    private String avatar;
    @Column(name = "isDelete")
    private Boolean isDelete;
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Login login;

    public Information() {
    }

    public Information(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProofID() {
        return proofID;
    }

    public void setProofID(String proofID) {
        this.proofID = proofID;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Information)) {
            return false;
        }
        Information other = (Information) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Information[ username=" + username + " ]";
    }
    
}
