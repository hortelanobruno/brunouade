/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "portalcv_isgs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvIsg.findAll", query = "SELECT p FROM PortalcvIsg p"),
    @NamedQuery(name = "PortalcvIsg.findByIdportalcvIsgs", query = "SELECT p FROM PortalcvIsg p WHERE p.idportalcvIsgs = :idportalcvIsgs"),
    @NamedQuery(name = "PortalcvIsg.findByName", query = "SELECT p FROM PortalcvIsg p WHERE p.name = :name"),
    @NamedQuery(name = "PortalcvIsg.findByIp", query = "SELECT p FROM PortalcvIsg p WHERE p.ip = :ip"),
    @NamedQuery(name = "PortalcvIsg.findBySecret", query = "SELECT p FROM PortalcvIsg p WHERE p.secret = :secret")})
public class PortalcvIsg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idportalcv_isgs")
    private Integer idportalcvIsgs;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "ip")
    private String ip;
    @Size(max = 45)
    @Column(name = "secret")
    private String secret;

    public PortalcvIsg() {
    }

    public PortalcvIsg(Integer idportalcvIsgs) {
        this.idportalcvIsgs = idportalcvIsgs;
    }

    public Integer getIdportalcvIsgs() {
        return idportalcvIsgs;
    }

    public void setIdportalcvIsgs(Integer idportalcvIsgs) {
        this.idportalcvIsgs = idportalcvIsgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idportalcvIsgs != null ? idportalcvIsgs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvIsg)) {
            return false;
        }
        PortalcvIsg other = (PortalcvIsg) object;
        if ((this.idportalcvIsgs == null && other.idportalcvIsgs != null) || (this.idportalcvIsgs != null && !this.idportalcvIsgs.equals(other.idportalcvIsgs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvIsg[ idportalcvIsgs=" + idportalcvIsgs + " ]";
    }
    
}
