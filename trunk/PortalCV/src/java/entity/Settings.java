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
@Table(name = "portalcv_settings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Settings.findAll", query = "SELECT s FROM Settings s"),
    @NamedQuery(name = "Settings.findByIdsettings", query = "SELECT s FROM Settings s WHERE s.idsettings = :idsettings"),
    @NamedQuery(name = "Settings.findByName", query = "SELECT s FROM Settings s WHERE s.name = :name"),
    @NamedQuery(name = "Settings.findByValue", query = "SELECT s FROM Settings s WHERE s.value = :value")})
public class Settings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsettings")
    private Integer idsettings;
    @Basic(optional = false)
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Basic(optional = true)
    @Column(name = "value")
    private String value;

    public Settings() {
    }

    public Settings(Integer idsettings) {
        this.idsettings = idsettings;
    }

    public Settings(Integer idsettings, String name, String value) {
        this.idsettings = idsettings;
        this.name = name;
        this.value = value;
    }

    public Integer getIdsettings() {
        return idsettings;
    }

    public void setIdsettings(Integer idsettings) {
        this.idsettings = idsettings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsettings != null ? idsettings.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Settings)) {
            return false;
        }
        Settings other = (Settings) object;
        if ((this.idsettings == null && other.idsettings != null) || (this.idsettings != null && !this.idsettings.equals(other.idsettings))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Settings[ idsettings=" + idsettings + " ]";
    }
}
