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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "portalcv_apgroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvApgroup.findAll", query = "SELECT p FROM PortalcvApgroup p"),
    @NamedQuery(name = "PortalcvApgroup.findByIdapgroups", query = "SELECT p FROM PortalcvApgroup p WHERE p.idapgroups = :idapgroups"),
    @NamedQuery(name = "PortalcvApgroup.findByDescription", query = "SELECT p FROM PortalcvApgroup p WHERE p.description = :description")})
public class PortalcvApgroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idapgroups")
    private Integer idapgroups;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "idhomes", referencedColumnName = "idhomes")
    @ManyToOne(optional = false)
    private PortalcvHome idhomes;

    public PortalcvApgroup() {
    }

    public PortalcvApgroup(Integer idapgroups) {
        this.idapgroups = idapgroups;
    }

    public Integer getIdapgroups() {
        return idapgroups;
    }

    public void setIdapgroups(Integer idapgroups) {
        this.idapgroups = idapgroups;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PortalcvHome getIdhomes() {
        return idhomes;
    }

    public void setIdhomes(PortalcvHome idhomes) {
        this.idhomes = idhomes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idapgroups != null ? idapgroups.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvApgroup)) {
            return false;
        }
        PortalcvApgroup other = (PortalcvApgroup) object;
        if ((this.idapgroups == null && other.idapgroups != null) || (this.idapgroups != null && !this.idapgroups.equals(other.idapgroups))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvApgroup[ idapgroups=" + idapgroups + " ]";
    }
    
}
