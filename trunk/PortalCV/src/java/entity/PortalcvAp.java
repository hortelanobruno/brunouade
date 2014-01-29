/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_aps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvAp.findAll", query = "SELECT p FROM PortalcvAp p"),
    @NamedQuery(name = "PortalcvAp.findByIdAP", query = "SELECT p FROM PortalcvAp p WHERE p.idAP = :idAP"),
    @NamedQuery(name = "PortalcvAp.findByMac", query = "SELECT p FROM PortalcvAp p WHERE p.mac = :mac"),
    @NamedQuery(name = "PortalcvAp.findByName", query = "SELECT p FROM PortalcvAp p WHERE p.name = :name"),
    @NamedQuery(name = "PortalcvAp.findByMacAndName", query = "SELECT p FROM PortalcvAp p WHERE p.mac = :mac and p.name = :name"),
    @NamedQuery(name = "PortalcvAp.findByDescripcion", query = "SELECT p FROM PortalcvAp p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PortalcvAp.findByLatitud", query = "SELECT p FROM PortalcvAp p WHERE p.latitud = :latitud"),
    @NamedQuery(name = "PortalcvAp.findByLongitud", query = "SELECT p FROM PortalcvAp p WHERE p.longitud = :longitud"),
    @NamedQuery(name = "PortalcvAp.findByDireccion", query = "SELECT p FROM PortalcvAp p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PortalcvAp.findBySNMPAPIndex", query = "SELECT p FROM PortalcvAp p WHERE p.sNMPAPIndex = :sNMPAPIndex")})
public class PortalcvAp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAP")
    private Integer idAP;
    @Size(max = 45)
    @Column(name = "MAC")
    private String mac;
    @Size(max = 200)
    @Column(name = "Name")
    private String name;
    @Size(max = 200)
    @Column(name = "Descripcion")
    private String descripcion;
    @Size(max = 200)
    @Column(name = "Latitud")
    private String latitud;
    @Size(max = 200)
    @Column(name = "Longitud")
    private String longitud;
    @Size(max = 200)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "SNMPAPIndex")
    private String sNMPAPIndex;
    @JoinColumn(name = "idapgroups", referencedColumnName = "idapgroups")
    @ManyToOne(optional = false)
    private PortalcvApgroup idapgroups;

    public PortalcvAp() {
    }

    public PortalcvAp(Integer idAP) {
        this.idAP = idAP;
    }

    public Integer getIdAP() {
        return idAP;
    }

    public void setIdAP(Integer idAP) {
        this.idAP = idAP;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSNMPAPIndex() {
        return sNMPAPIndex;
    }

    public void setSNMPAPIndex(String sNMPAPIndex) {
        this.sNMPAPIndex = sNMPAPIndex;
    }

    public PortalcvApgroup getIdapgroups() {
        return idapgroups;
    }

    public void setIdapgroups(PortalcvApgroup idapgroups) {
        this.idapgroups = idapgroups;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAP != null ? idAP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvAp)) {
            return false;
        }
        PortalcvAp other = (PortalcvAp) object;
        if ((this.idAP == null && other.idAP != null) || (this.idAP != null && !this.idAP.equals(other.idAP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvAp[ idAP=" + idAP + " ]";
    }

    public String toStringAll() {
        return "PortalcvAp{" + "idAP=" + idAP + ", mac=" + mac + ", name=" + name + ", descripcion=" + descripcion + ", latitud=" + latitud + ", longitud=" + longitud + ", direccion=" + direccion + ", sNMPAPIndex=" + sNMPAPIndex + ", idapgroups=" + idapgroups + '}';
    }
}
