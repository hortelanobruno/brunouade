/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_quota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quota.findAll", query = "SELECT q FROM Quota q"),
    @NamedQuery(name = "Quota.findById", query = "SELECT q FROM Quota q WHERE q.id = :id"),
    @NamedQuery(name = "Quota.findByUsername", query = "SELECT q FROM Quota q WHERE q.username = :username"),
    @NamedQuery(name = "Quota.findByUsernameAndService", query = "SELECT q FROM Quota q WHERE q.username = :username and q.service = :service"),
    @NamedQuery(name = "Quota.findByUsernameByType", query = "SELECT q FROM Quota q WHERE q.username = :username and q.type = :type"),
    @NamedQuery(name = "Quota.findByUsernameByTypeByService", query = "SELECT q FROM Quota q WHERE q.username = :username and q.type = :type and q.service = :service"),
    @NamedQuery(name = "Quota.findByAttribute", query = "SELECT q FROM Quota q WHERE q.attribute = :attribute"),
    @NamedQuery(name = "Quota.findByOp", query = "SELECT q FROM Quota q WHERE q.op = :op"),
    @NamedQuery(name = "Quota.findByValue", query = "SELECT q FROM Quota q WHERE q.value = :value"),
    @NamedQuery(name = "Quota.findByType", query = "SELECT q FROM Quota q WHERE q.type = :type")})
public class Quota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 30)
    @Column(name = "attribute")
    private String attribute;
    @Size(max = 5)
    @Column(name = "op")
    private String op;
    @Column(name = "value")
    private BigInteger value;
    @Size(max = 10)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "service")
    private String service;
    @Column(name = "session_quota")
    private BigInteger sessionQuota = new BigInteger("0");

    public Quota() {
    }

    public Quota(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Quota)) {
            return false;
        }
        Quota other = (Quota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Quota[ id=" + id + " ]";
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public BigInteger getSessionQuota() {
        return sessionQuota;
    }

    public void setSessionQuota(BigInteger sessionQuota) {
        this.sessionQuota = sessionQuota;
    }
}
