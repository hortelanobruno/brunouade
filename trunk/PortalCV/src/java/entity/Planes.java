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
@Table(name = "portalcv_planes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planes.findAll", query = "SELECT p FROM Planes p"),
    @NamedQuery(name = "Planes.findAllServicesByPlan", query = "SELECT SUBSTRING(p.value,2) FROM Planes p WHERE p.groupName = :groupName and p.attribute like 'Cisco-Account-Info'"),
    @NamedQuery(name = "Planes.findById", query = "SELECT p FROM Planes p WHERE p.id = :id"),
    @NamedQuery(name = "Planes.findByGroupName", query = "SELECT p FROM Planes p WHERE p.groupName = :groupName"),
    @NamedQuery(name = "Planes.findByAttribute", query = "SELECT p FROM Planes p WHERE p.attribute = :attribute"),
    @NamedQuery(name = "Planes.findByValue", query = "SELECT p FROM Planes p WHERE p.value = :value"),
    @NamedQuery(name = "Planes.findByGroupNameByAccounting", query = "SELECT p FROM Planes p WHERE p.groupName = :groupName and p.value like :value"),
    @NamedQuery(name = "Planes.findByGroupNameByAttributeByValue", query = "SELECT p FROM Planes p WHERE p.groupName = :groupName and p.attribute = :attribute and p.value = :value"),
    @NamedQuery(name = "Planes.findByOp", query = "SELECT p FROM Planes p WHERE p.op = :op")})
public class Planes implements Serializable {
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "GroupName")
    private String groupName;
    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "Attribute")
    private String attribute;
    @Size(max = 100)
    @Column(name = "Value")
    private String value;
    @Size(max = 5)
    @Column(name = "Op")
    private String op;
    @Column(name = "priority")
    private Integer priority;

    public Planes() {
    }

    public Planes(Integer id) {
        this.id = id;
    }

    public Planes(Integer id, String groupName, String attribute) {
        this.id = id;
        this.groupName = groupName;
        this.attribute = attribute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planes)) {
            return false;
        }
        Planes other = (Planes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Planes[ id=" + id + " ]";
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
