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
@Table(name = "portalcv_account_validation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountValidation.findAll", query = "SELECT a FROM AccountValidation a"),
    @NamedQuery(name = "AccountValidation.findById", query = "SELECT a FROM AccountValidation a WHERE a.id = :id"),
    @NamedQuery(name = "AccountValidation.findByEmail", query = "SELECT a FROM AccountValidation a WHERE a.email = :email"),
    @NamedQuery(name = "AccountValidation.findByHashcode", query = "SELECT a FROM AccountValidation a WHERE a.hashcode = :hashcode")})
public class AccountValidation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "hashcode")
    private String hashcode;

    public AccountValidation() {
    }

    public AccountValidation(Integer id) {
        this.id = id;
    }

    public AccountValidation(Integer id, String email, String hashcode) {
        this.id = id;
        this.email = email;
        this.hashcode = hashcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
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
        if (!(object instanceof AccountValidation)) {
            return false;
        }
        AccountValidation other = (AccountValidation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AccountValidation[ id=" + id + " ]";
    }
    
}
