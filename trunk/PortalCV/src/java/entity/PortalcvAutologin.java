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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "portalcv_autologin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvAutologin.findAll", query = "SELECT p FROM PortalcvAutologin p"),
    @NamedQuery(name = "PortalcvAutologin.findByIdautologin", query = "SELECT p FROM PortalcvAutologin p WHERE p.idautologin = :idautologin"),
    @NamedQuery(name = "PortalcvAutologin.findByUsername", query = "SELECT p FROM PortalcvAutologin p WHERE p.username = :username"),
    @NamedQuery(name = "PortalcvAutologin.findByCpeMac", query = "SELECT p FROM PortalcvAutologin p WHERE p.cpeMac = :cpeMac"),
    @NamedQuery(name = "PortalcvAutologin.findByLogintype", query = "SELECT p FROM PortalcvAutologin p WHERE p.logintype = :logintype"),
    @NamedQuery(name = "PortalcvAutologin.findByTimestamp", query = "SELECT p FROM PortalcvAutologin p WHERE p.timestamp = :timestamp")})
public class PortalcvAutologin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idautologin")
    private Integer idautologin;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 45)
    @Column(name = "cpeMac")
    private String cpeMac;
    @Size(max = 45)
    @Column(name = "logintype")
    private String logintype;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public PortalcvAutologin() {
    }

    public PortalcvAutologin(Integer idautologin) {
        this.idautologin = idautologin;
    }

    public Integer getIdautologin() {
        return idautologin;
    }

    public void setIdautologin(Integer idautologin) {
        this.idautologin = idautologin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpeMac() {
        return cpeMac;
    }

    public void setCpeMac(String cpeMac) {
        this.cpeMac = cpeMac;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idautologin != null ? idautologin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvAutologin)) {
            return false;
        }
        PortalcvAutologin other = (PortalcvAutologin) object;
        if ((this.idautologin == null && other.idautologin != null) || (this.idautologin != null && !this.idautologin.equals(other.idautologin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvAutologin[ idautologin=" + idautologin + " ]";
    }
    
}
