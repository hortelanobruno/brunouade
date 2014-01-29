/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_registered_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegisteredUser.findAll", query = "SELECT r FROM RegisteredUser r"),
    @NamedQuery(name = "RegisteredUser.findById", query = "SELECT r FROM RegisteredUser r WHERE r.id = :id"),
    @NamedQuery(name = "RegisteredUser.findByName", query = "SELECT r FROM RegisteredUser r WHERE r.name = :name"),
    @NamedQuery(name = "RegisteredUser.findByLastname", query = "SELECT r FROM RegisteredUser r WHERE r.lastname = :lastname"),
    @NamedQuery(name = "RegisteredUser.findByEmail", query = "SELECT r FROM RegisteredUser r WHERE r.email = :email"),
    @NamedQuery(name = "RegisteredUser.findByEmailAndPassword", query = "SELECT r FROM RegisteredUser r WHERE r.email = :email and r.password = :password"),
    @NamedQuery(name = "RegisteredUser.findByPassword", query = "SELECT r FROM RegisteredUser r WHERE r.password = :password"),
    @NamedQuery(name = "RegisteredUser.findByActivated", query = "SELECT r FROM RegisteredUser r WHERE r.activated = :activated"),
    @NamedQuery(name = "RegisteredUser.findByDateCreated", query = "SELECT r FROM RegisteredUser r WHERE r.dateCreated = :dateCreated"),
    @NamedQuery(name = "RegisteredUser.findByDateValidated", query = "SELECT r FROM RegisteredUser r WHERE r.dateValidated = :dateValidated")})
public class RegisteredUser implements Serializable {
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "activated")
    private boolean activated;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "serviceprovider")
    private String serviceprovider;
    @Basic(optional = false)
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_validated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidated;

    public RegisteredUser() {
    }

    public RegisteredUser(Integer id) {
        this.id = id;
    }

    public RegisteredUser(Integer id, String name, String lastname, String email, String password, boolean activated, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.activated = activated;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(Date dateValidated) {
        this.dateValidated = dateValidated;
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
        if (!(object instanceof RegisteredUser)) {
            return false;
        }
        RegisteredUser other = (RegisteredUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RegisteredUser[ id=" + id + " ]";
    }

    public String getServiceprovider() {
        return serviceprovider;
    }

    public void setServiceprovider(String serviceprovider) {
        this.serviceprovider = serviceprovider;
    }

    public Boolean expired(int defaultTime) {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        Calendar cal = Calendar.getInstance();
        if(dateValidated!=null){
            cal.setTime(dateValidated);
        }else{
            cal.setTime(dateCreated);
        }
        long aux = cal.getTimeInMillis();
        if (timestamp - aux < 1000 * 60 * defaultTime){
            return false;
        }else{
            return true;
        }
    }

}
