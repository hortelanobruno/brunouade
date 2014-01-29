/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "portalcv_homes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvHome.findAll", query = "SELECT p FROM PortalcvHome p"),
    @NamedQuery(name = "PortalcvHome.findByIdhomes", query = "SELECT p FROM PortalcvHome p WHERE p.idhomes = :idhomes"),
    @NamedQuery(name = "PortalcvHome.findByDescription", query = "SELECT p FROM PortalcvHome p WHERE p.description = :description")})
public class PortalcvHome implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhomes")
    private Integer idhomes;
    @Basic(optional = false)
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idhomes")
    private List<PortalcvHomeresource> portalcvHomeresourceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idhomes")
    private List<PortalcvApgroup> portalcvApgroupList;

    public PortalcvHome() {
    }

    public PortalcvHome(Integer idhomes) {
        this.idhomes = idhomes;
    }

    public PortalcvHome(Integer idhomes, String description) {
        this.idhomes = idhomes;
        this.description = description;
    }

    public Integer getIdhomes() {
        return idhomes;
    }

    public void setIdhomes(Integer idhomes) {
        this.idhomes = idhomes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    @JsonIgnore
    public List<PortalcvHomeresource> getPortalcvHomeresourceList() {
        return portalcvHomeresourceList;
    }

    public void setPortalcvHomeresourceList(List<PortalcvHomeresource> portalcvHomeresourceList) {
        this.portalcvHomeresourceList = portalcvHomeresourceList;
    }

    @XmlTransient
    @JsonIgnore
    public List<PortalcvApgroup> getPortalcvApgroupList() {
        return portalcvApgroupList;
    }

    public void setPortalcvApgroupList(List<PortalcvApgroup> portalcvApgroupList) {
        this.portalcvApgroupList = portalcvApgroupList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhomes != null ? idhomes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvHome)) {
            return false;
        }
        PortalcvHome other = (PortalcvHome) object;
        if ((this.idhomes == null && other.idhomes != null) || (this.idhomes != null && !this.idhomes.equals(other.idhomes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvHome[ idhomes=" + idhomes + " ]";
    }
    
}
