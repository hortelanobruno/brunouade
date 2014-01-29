/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_facebookdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvFacebookdata.findAll", query = "SELECT p FROM PortalcvFacebookdata p"),
    @NamedQuery(name = "PortalcvFacebookdata.findByIdfacebookdata", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.idfacebookdata = :idfacebookdata"),
    @NamedQuery(name = "PortalcvFacebookdata.findById", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.id = :id"),
    @NamedQuery(name = "PortalcvFacebookdata.findByName", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.name = :name"),
    @NamedQuery(name = "PortalcvFacebookdata.findByGender", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.gender = :gender"),
    @NamedQuery(name = "PortalcvFacebookdata.findByLink", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.link = :link"),
    @NamedQuery(name = "PortalcvFacebookdata.findByAgeRange", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.ageRange = :ageRange"),
    @NamedQuery(name = "PortalcvFacebookdata.findByBirthday", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.birthday = :birthday"),
    @NamedQuery(name = "PortalcvFacebookdata.findByEmail", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.email = :email"),
    @NamedQuery(name = "PortalcvFacebookdata.findByHometown", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.hometown = :hometown"),
    @NamedQuery(name = "PortalcvFacebookdata.findByLocation", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.location = :location"),
    @NamedQuery(name = "PortalcvFacebookdata.findByFirstName", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "PortalcvFacebookdata.findByLastName", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "PortalcvFacebookdata.findByPicture", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.picture = :picture"),
    @NamedQuery(name = "PortalcvFacebookdata.findByAddress", query = "SELECT p FROM PortalcvFacebookdata p WHERE p.address = :address")})
public class PortalcvFacebookdata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfacebookdata")
    private Integer idfacebookdata;
    @Column(name = "id")
    private BigInteger id;
    @Size(max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "gender")
    private String gender;
    @Size(max = 200)
    @Column(name = "link")
    private String link;
    @Size(max = 45)
    @Column(name = "age_range")
    private String ageRange;
    @Size(max = 45)
    @Column(name = "birthday")
    private String birthday;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 200)
    @Column(name = "hometown")
    private String hometown;
    @Size(max = 200)
    @Column(name = "location")
    private String location;
    @Size(max = 200)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 200)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 500)
    @Column(name = "picture")
    private String picture;
    @Size(max = 200)
    @Column(name = "address")
    private String address;

    public PortalcvFacebookdata() {
    }

    public PortalcvFacebookdata(Integer idfacebookdata) {
        this.idfacebookdata = idfacebookdata;
    }

    public Integer getIdfacebookdata() {
        return idfacebookdata;
    }

    public void setIdfacebookdata(Integer idfacebookdata) {
        this.idfacebookdata = idfacebookdata;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfacebookdata != null ? idfacebookdata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvFacebookdata)) {
            return false;
        }
        PortalcvFacebookdata other = (PortalcvFacebookdata) object;
        if ((this.idfacebookdata == null && other.idfacebookdata != null) || (this.idfacebookdata != null && !this.idfacebookdata.equals(other.idfacebookdata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvFacebookdata[ idfacebookdata=" + idfacebookdata + " ]";
    }
    
}
