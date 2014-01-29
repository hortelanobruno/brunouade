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
@Table(name = "radcheck")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Radcheck.findAll", query = "SELECT r FROM Radcheck r"),
    @NamedQuery(name = "Radcheck.findById", query = "SELECT r FROM Radcheck r WHERE r.id = :id"),
    @NamedQuery(name = "Radcheck.findByUserName", query = "SELECT r FROM Radcheck r WHERE r.userName = :userName"),
    @NamedQuery(name = "Radcheck.findByUserNameAndAttribute", query = "SELECT r FROM Radcheck r WHERE r.userName = :userName and r.attribute = :attribute"),
    @NamedQuery(name = "Radcheck.findByAttribute", query = "SELECT r FROM Radcheck r WHERE r.attribute = :attribute"),
    @NamedQuery(name = "Radcheck.findByValue", query = "SELECT r FROM Radcheck r WHERE r.value = :value"),
    @NamedQuery(name = "Radcheck.findByOp", query = "SELECT r FROM Radcheck r WHERE r.op = :op")})
public class Radcheck implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "UserName")
    private String userName;
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

    public Radcheck() {
    }

    public Radcheck(Integer id) {
        this.id = id;
    }

    public Radcheck(Integer id, String userName, String attribute) {
        this.id = id;
        this.userName = userName;
        this.attribute = attribute;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        if (!(object instanceof Radcheck)) {
            return false;
        }
        Radcheck other = (Radcheck) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Radcheck[ id=" + id + " ]";
    }
    
}
