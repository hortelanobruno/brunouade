/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_client_login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientLogin.findAll", query = "SELECT c FROM ClientLogin c"),
    @NamedQuery(name = "ClientLogin.findByIdportalcvClientLogin", query = "SELECT c FROM ClientLogin c WHERE c.idportalcvClientLogin = :idportalcvClientLogin"),
    @NamedQuery(name = "ClientLogin.findByEmail", query = "SELECT c FROM ClientLogin c WHERE c.email = :email"),
    @NamedQuery(name = "ClientLogin.findByAuthKey", query = "SELECT c FROM ClientLogin c WHERE c.authKey = :authKey"),
    @NamedQuery(name = "ClientLogin.findByDateLastLogin", query = "SELECT c FROM ClientLogin c WHERE c.dateLastLogin = :dateLastLogin")})
public class ClientLogin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idportalcv_client_login")
    private Integer idportalcvClientLogin;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "auth_key")
    private String authKey;
    @Basic(optional = false)
    @Column(name = "date_last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastLogin;

    public ClientLogin() {
    }

    public ClientLogin(Integer idportalcvClientLogin) {
        this.idportalcvClientLogin = idportalcvClientLogin;
    }

    public ClientLogin(Integer idportalcvClientLogin, String email, String authKey, Date dateLastLogin) {
        this.idportalcvClientLogin = idportalcvClientLogin;
        this.email = email;
        this.authKey = authKey;
        this.dateLastLogin = dateLastLogin;
    }

    public Integer getIdportalcvClientLogin() {
        return idportalcvClientLogin;
    }

    public void setIdportalcvClientLogin(Integer idportalcvClientLogin) {
        this.idportalcvClientLogin = idportalcvClientLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public Date getDateLastLogin() {
        return dateLastLogin;
    }

    public void setDateLastLogin(Date dateLastLogin) {
        this.dateLastLogin = dateLastLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idportalcvClientLogin != null ? idportalcvClientLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientLogin)) {
            return false;
        }
        ClientLogin other = (ClientLogin) object;
        if ((this.idportalcvClientLogin == null && other.idportalcvClientLogin != null) || (this.idportalcvClientLogin != null && !this.idportalcvClientLogin.equals(other.idportalcvClientLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientLogin{" + "idportalcvClientLogin=" + idportalcvClientLogin + ", email=" + email + ", authKey=" + authKey + ", dateLastLogin=" + dateLastLogin + '}';
    }

   
    
}
