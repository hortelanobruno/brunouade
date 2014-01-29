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
@Table(name = "portalcv_homeresources")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvHomeresource.findAll", query = "SELECT p FROM PortalcvHomeresource p"),
    @NamedQuery(name = "PortalcvHomeresource.findByIdhomeresources", query = "SELECT p FROM PortalcvHomeresource p WHERE p.idhomeresources = :idhomeresources"),
    @NamedQuery(name = "PortalcvHomeresource.findByDescription", query = "SELECT p FROM PortalcvHomeresource p WHERE p.description = :description"),
    @NamedQuery(name = "PortalcvHomeresource.findByUrl", query = "SELECT p FROM PortalcvHomeresource p WHERE p.url = :url"),
    @NamedQuery(name = "PortalcvHomeresource.findByFilePath", query = "SELECT p FROM PortalcvHomeresource p WHERE p.filePath = :filePath"),
    @NamedQuery(name = "PortalcvHomeresource.findByUrlAndIdHome", query = "SELECT p FROM PortalcvHomeresource p WHERE p.url = :url and p.idhomes = :idhomes"),
    @NamedQuery(name = "PortalcvHomeresource.findByUrlAndTypeAndIdhomes", query = "SELECT p FROM PortalcvHomeresource p WHERE p.url = :url and p.type = :type and p.idhomes.idhomes = :idhomes"),
    @NamedQuery(name = "PortalcvHomeresource.findByType", query = "SELECT p FROM PortalcvHomeresource p WHERE p.type = :type")})
public class PortalcvHomeresource implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhomeresources")
    private Integer idhomeresources;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 200)
    @Column(name = "url")
    private String url;
    @Size(max = 200)
    @Column(name = "filePath")
    private String filePath;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "idhomes", referencedColumnName = "idhomes")
    @ManyToOne(optional = false)
    private PortalcvHome idhomes;

    public PortalcvHomeresource() {
    }

    public PortalcvHomeresource(Integer idhomeresources) {
        this.idhomeresources = idhomeresources;
    }

    public Integer getIdhomeresources() {
        return idhomeresources;
    }

    public void setIdhomeresources(Integer idhomeresources) {
        this.idhomeresources = idhomeresources;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (idhomeresources != null ? idhomeresources.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvHomeresource)) {
            return false;
        }
        PortalcvHomeresource other = (PortalcvHomeresource) object;
        if ((this.idhomeresources == null && other.idhomeresources != null) || (this.idhomeresources != null && !this.idhomeresources.equals(other.idhomeresources))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvHomeresource[ idhomeresources=" + idhomeresources + " ]";
    }
    
}
