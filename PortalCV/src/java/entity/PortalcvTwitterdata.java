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
@Table(name = "portalcv_twitterdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvTwitterdata.findAll", query = "SELECT p FROM PortalcvTwitterdata p"),
    @NamedQuery(name = "PortalcvTwitterdata.findByIdtwitterdata", query = "SELECT p FROM PortalcvTwitterdata p WHERE p.idtwitterdata = :idtwitterdata"),
    @NamedQuery(name = "PortalcvTwitterdata.findById", query = "SELECT p FROM PortalcvTwitterdata p WHERE p.id = :id"),
    @NamedQuery(name = "PortalcvTwitterdata.findByName", query = "SELECT p FROM PortalcvTwitterdata p WHERE p.name = :name"),
    @NamedQuery(name = "PortalcvTwitterdata.findByScreenName", query = "SELECT p FROM PortalcvTwitterdata p WHERE p.screenName = :screenName"),
    @NamedQuery(name = "PortalcvTwitterdata.findByLocation", query = "SELECT p FROM PortalcvTwitterdata p WHERE p.location = :location"),
    @NamedQuery(name = "PortalcvTwitterdata.findByProfileImageUrl", query = "SELECT p FROM PortalcvTwitterdata p WHERE p.profileImageUrl = :profileImageUrl"),
    @NamedQuery(name = "PortalcvTwitterdata.findByUrl", query = "SELECT p FROM PortalcvTwitterdata p WHERE p.url = :url"),
    @NamedQuery(name = "PortalcvTwitterdata.findByFollowersCount", query = "SELECT p FROM PortalcvTwitterdata p WHERE p.followersCount = :followersCount")})
public class PortalcvTwitterdata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtwitterdata")
    private Integer idtwitterdata;
    @Column(name = "id")
    private BigInteger id;
    @Size(max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "screenName")
    private String screenName;
    @Size(max = 200)
    @Column(name = "location")
    private String location;
    @Size(max = 200)
    @Column(name = "profileImageUrl")
    private String profileImageUrl;
    @Size(max = 200)
    @Column(name = "url")
    private String url;
    @Column(name = "followersCount")
    private Integer followersCount;

    public PortalcvTwitterdata() {
    }

    public PortalcvTwitterdata(Integer idtwitterdata) {
        this.idtwitterdata = idtwitterdata;
    }

    public Integer getIdtwitterdata() {
        return idtwitterdata;
    }

    public void setIdtwitterdata(Integer idtwitterdata) {
        this.idtwitterdata = idtwitterdata;
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

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtwitterdata != null ? idtwitterdata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvTwitterdata)) {
            return false;
        }
        PortalcvTwitterdata other = (PortalcvTwitterdata) object;
        if ((this.idtwitterdata == null && other.idtwitterdata != null) || (this.idtwitterdata != null && !this.idtwitterdata.equals(other.idtwitterdata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvTwitterdata[ idtwitterdata=" + idtwitterdata + " ]";
    }
    
}
