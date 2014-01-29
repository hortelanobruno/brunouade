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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_fibertelusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvFiberteluser.findAll", query = "SELECT p FROM PortalcvFiberteluser p"),
    @NamedQuery(name = "PortalcvFiberteluser.findByIdfibertelusers", query = "SELECT p FROM PortalcvFiberteluser p WHERE p.idfibertelusers = :idfibertelusers"),
    @NamedQuery(name = "PortalcvFiberteluser.findByEmail", query = "SELECT p FROM PortalcvFiberteluser p WHERE p.email = :email")})
public class PortalcvFiberteluser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfibertelusers")
    private Integer idfibertelusers;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @Column(name = "email")
    private String email;

    public PortalcvFiberteluser() {
    }

    public PortalcvFiberteluser(Integer idfibertelusers) {
        this.idfibertelusers = idfibertelusers;
    }

    public Integer getIdfibertelusers() {
        return idfibertelusers;
    }

    public void setIdfibertelusers(Integer idfibertelusers) {
        this.idfibertelusers = idfibertelusers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfibertelusers != null ? idfibertelusers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvFiberteluser)) {
            return false;
        }
        PortalcvFiberteluser other = (PortalcvFiberteluser) object;
        if ((this.idfibertelusers == null && other.idfibertelusers != null) || (this.idfibertelusers != null && !this.idfibertelusers.equals(other.idfibertelusers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvFiberteluser[ idfibertelusers=" + idfibertelusers + " ]";
    }
    
}
