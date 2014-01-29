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
@Table(name = "portalcv_subscribers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscribers.findAll", query = "SELECT s FROM Subscribers s"),
    @NamedQuery(name = "Subscribers.findAllNames", query = "SELECT s.userName FROM Subscribers s"),
    @NamedQuery(name = "Subscribers.findById", query = "SELECT s FROM Subscribers s WHERE s.id = :id"),
    @NamedQuery(name = "Subscribers.findByUserName", query = "SELECT s FROM Subscribers s WHERE s.userName = :userName"),
    @NamedQuery(name = "Subscribers.findByGroupName", query = "SELECT s FROM Subscribers s WHERE s.groupName = :groupName"),
    @NamedQuery(name = "Subscribers.findAllSubsByGroupName", query = "SELECT s.userName FROM Subscribers s WHERE s.groupName = :groupName"),
    @NamedQuery(name = "Subscribers.findByPriority", query = "SELECT s FROM Subscribers s WHERE s.priority = :priority")})
public class Subscribers implements Serializable {

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
    @Size(min = 1, max = 45)
    @Column(name = "GroupName")
    private String groupName;
    @Basic(optional = false)
    @Column(name = "priority")
    private int priority;

    public Subscribers() {
    }

    public Subscribers(Integer id) {
        this.id = id;
    }

    public Subscribers(Integer id, String userName, String groupName, int priority) {
        this.id = id;
        this.userName = userName;
        this.groupName = groupName;
        this.priority = priority;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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
        if (!(object instanceof Subscribers)) {
            return false;
        }
        Subscribers other = (Subscribers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Subscribers[ id=" + id + " ]";
    }
}
