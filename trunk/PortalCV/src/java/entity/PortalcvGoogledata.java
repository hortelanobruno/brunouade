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
@Table(name = "portalcv_googledata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvGoogledata.findAll", query = "SELECT p FROM PortalcvGoogledata p"),
    @NamedQuery(name = "PortalcvGoogledata.findByIdgoogledata", query = "SELECT p FROM PortalcvGoogledata p WHERE p.idgoogledata = :idgoogledata"),
    @NamedQuery(name = "PortalcvGoogledata.findByEmail", query = "SELECT p FROM PortalcvGoogledata p WHERE p.email = :email"),
    @NamedQuery(name = "PortalcvGoogledata.findByName", query = "SELECT p FROM PortalcvGoogledata p WHERE p.name = :name"),
    @NamedQuery(name = "PortalcvGoogledata.findByGivenName", query = "SELECT p FROM PortalcvGoogledata p WHERE p.givenName = :givenName"),
    @NamedQuery(name = "PortalcvGoogledata.findByFamilyName", query = "SELECT p FROM PortalcvGoogledata p WHERE p.familyName = :familyName"),
    @NamedQuery(name = "PortalcvGoogledata.findByLink", query = "SELECT p FROM PortalcvGoogledata p WHERE p.link = :link"),
    @NamedQuery(name = "PortalcvGoogledata.findByPicture", query = "SELECT p FROM PortalcvGoogledata p WHERE p.picture = :picture"),
    @NamedQuery(name = "PortalcvGoogledata.findByGender", query = "SELECT p FROM PortalcvGoogledata p WHERE p.gender = :gender"),
    @NamedQuery(name = "PortalcvGoogledata.findByBirthday", query = "SELECT p FROM PortalcvGoogledata p WHERE p.birthday = :birthday"),
    @NamedQuery(name = "PortalcvGoogledata.findById", query = "SELECT p FROM PortalcvGoogledata p WHERE p.id = :id")})
public class PortalcvGoogledata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgoogledata")
    private Integer idgoogledata;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "email")
    private String email;
    @Size(max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "given_name")
    private String givenName;
    @Size(max = 200)
    @Column(name = "family_name")
    private String familyName;
    @Size(max = 200)
    @Column(name = "link")
    private String link;
    @Size(max = 200)
    @Column(name = "picture")
    private String picture;
    @Size(max = 45)
    @Column(name = "gender")
    private String gender;
    @Size(max = 45)
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "id")
    private BigInteger id;

    public PortalcvGoogledata() {
    }

    public PortalcvGoogledata(Integer idgoogledata) {
        this.idgoogledata = idgoogledata;
    }

    public Integer getIdgoogledata() {
        return idgoogledata;
    }

    public void setIdgoogledata(Integer idgoogledata) {
        this.idgoogledata = idgoogledata;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgoogledata != null ? idgoogledata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvGoogledata)) {
            return false;
        }
        PortalcvGoogledata other = (PortalcvGoogledata) object;
        if ((this.idgoogledata == null && other.idgoogledata != null) || (this.idgoogledata != null && !this.idgoogledata.equals(other.idgoogledata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvGoogledata[ idgoogledata=" + idgoogledata + " ]";
    }
    
}
