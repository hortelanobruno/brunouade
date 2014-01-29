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
@Table(name = "portalcv_userdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdata.findAll", query = "SELECT u FROM Userdata u"),
    @NamedQuery(name = "Userdata.findByIdportalcvUserdata", query = "SELECT u FROM Userdata u WHERE u.idportalcvUserdata = :idportalcvUserdata"),
    @NamedQuery(name = "Userdata.findByEmail", query = "SELECT u FROM Userdata u WHERE u.email = :email"),
    @NamedQuery(name = "Userdata.findByUseragent", query = "SELECT u FROM Userdata u WHERE u.useragent = :useragent"),
    @NamedQuery(name = "Userdata.findByIpaddress", query = "SELECT u FROM Userdata u WHERE u.ipaddress = :ipaddress"),
    @NamedQuery(name = "Userdata.findByCreationdate", query = "SELECT u FROM Userdata u WHERE u.creationdate = :creationdate")})
public class Userdata implements Serializable {

    @Basic(optional = false)
    @Column(name = "creationdate")
    @Temporal(TemporalType.DATE)
    private Date creationdate;
    @Size(max = 500)
    @Column(name = "device")
    private String device;
    @Size(max = 500)
    @Column(name = "os")
    private String os;
    @Size(max = 500)
    @Column(name = "os_version")
    private String osVersion;
    @Size(max = 500)
    @Column(name = "browser")
    private String browser;
    @Size(max = 500)
    @Column(name = "browser_version")
    private String browserVersion;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idportalcv_userdata")
    private Integer idportalcvUserdata;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Size(min = 1, max = 500)
    @Column(name = "useragent")
    private String useragent;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "ipaddress")
    private String ipaddress;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "logintype")
    private String logintype;

    public Userdata() {
    }

    public Userdata(Integer idportalcvUserdata) {
        this.idportalcvUserdata = idportalcvUserdata;
    }

    public Userdata(Integer idportalcvUserdata, String email, String useragent, String ipaddress, Date creationdate) {
        this.idportalcvUserdata = idportalcvUserdata;
        this.email = email;
        this.useragent = useragent;
        this.ipaddress = ipaddress;
        this.creationdate = creationdate;
    }

    public Integer getIdportalcvUserdata() {
        return idportalcvUserdata;
    }

    public void setIdportalcvUserdata(Integer idportalcvUserdata) {
        this.idportalcvUserdata = idportalcvUserdata;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idportalcvUserdata != null ? idportalcvUserdata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdata)) {
            return false;
        }
        Userdata other = (Userdata) object;
        if ((this.idportalcvUserdata == null && other.idportalcvUserdata != null) || (this.idportalcvUserdata != null && !this.idportalcvUserdata.equals(other.idportalcvUserdata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Userdata{" + "creationdate=" + creationdate + ", device=" + device + ", os=" + os + ", osVersion=" + osVersion + ", browser=" + browser + ", browserVersion=" + browserVersion + ", idportalcvUserdata=" + idportalcvUserdata + ", email=" + email + ", useragent=" + useragent + ", ipaddress=" + ipaddress + ", logintype=" + logintype + '}';
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }
}
