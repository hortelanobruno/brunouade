/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_useragents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvUseragent.findAll", query = "SELECT p FROM PortalcvUseragent p"),
    @NamedQuery(name = "PortalcvUseragent.findByIdUserAgent", query = "SELECT p FROM PortalcvUseragent p WHERE p.idUserAgent = :idUserAgent"),
    @NamedQuery(name = "PortalcvUseragent.findByDevice", query = "SELECT p FROM PortalcvUseragent p WHERE p.device = :device"),
    @NamedQuery(name = "PortalcvUseragent.findByOs", query = "SELECT p FROM PortalcvUseragent p WHERE p.os = :os"),
    @NamedQuery(name = "PortalcvUseragent.findByBrowser", query = "SELECT p FROM PortalcvUseragent p WHERE p.browser = :browser"),
    @NamedQuery(name = "PortalcvUseragent.findByOsVersion", query = "SELECT p FROM PortalcvUseragent p WHERE p.osVersion = :osVersion"),
    @NamedQuery(name = "PortalcvUseragent.findByBrowserVersion", query = "SELECT p FROM PortalcvUseragent p WHERE p.browserVersion = :browserVersion"),
    @NamedQuery(name = "PortalcvUseragent.findByFullUserAgent", query = "SELECT p FROM PortalcvUseragent p WHERE p.fullUserAgent = :fullUserAgent"),
    @NamedQuery(name = "PortalcvUseragent.findByAllLessFullUserAgent", query = "SELECT p FROM PortalcvUseragent p WHERE p.osVersion = :osVersion and p.browser = :browser and p.os = :os and p.device = :device and p.browserVersion = :browserVersion"),
    @NamedQuery(name = "PortalcvUseragent.findByAll", query = "SELECT p FROM PortalcvUseragent p WHERE p.osVersion = :osVersion and p.browser = :browser and p.os = :os and p.device = :device and p.browserVersion = :browserVersion and p.fullUserAgent = :fullUserAgent")})
public class PortalcvUseragent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUserAgent")
    private Integer idUserAgent;
    @Size(max = 500)
    @Column(name = "DEVICE")
    private String device = "N/A";
    @Size(max = 500)
    @Column(name = "OS")
    private String os = "N/A";
    @Size(max = 500)
    @Column(name = "BROWSER")
    private String browser = "N/A";
    @Size(max = 500)
    @Column(name = "OS_VERSION")
    private String osVersion = "N/A";
    @Size(max = 500)
    @Column(name = "BROWSER_VERSION")
    private String browserVersion = "N/A";
    @Size(max = 500)
    @Column(name = "fullUserAgent")
    private String fullUserAgent = "N/A";

    public PortalcvUseragent() {
    }

    public PortalcvUseragent(Integer idUserAgent) {
        this.idUserAgent = idUserAgent;
    }

    public Integer getIdUserAgent() {
        return idUserAgent;
    }

    public void setIdUserAgent(Integer idUserAgent) {
        this.idUserAgent = idUserAgent;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        if (device == null) {
            this.device = "N/A";
        } else {
            this.device = device;
        }
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        if (os == null) {
            this.os = "N/A";
        } else {
            this.os = os;
        }
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        if (browser == null) {
            this.browser = "N/A";
        } else {
            this.browser = browser;
        }
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        if (osVersion == null) {
            this.osVersion = "N/A";
        } else {
            this.osVersion = osVersion;
        }
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        if (browserVersion == null) {
            this.browserVersion = "N/A";
        } else {
            this.browserVersion = browserVersion;
        }
    }

    public String getFullUserAgent() {
        return fullUserAgent;
    }

    public void setFullUserAgent(String fullUserAgent) {
        if (fullUserAgent == null) {
            this.fullUserAgent = "N/A";
        } else {
            this.fullUserAgent = fullUserAgent;
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserAgent != null ? idUserAgent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvUseragent)) {
            return false;
        }
        PortalcvUseragent other = (PortalcvUseragent) object;
        if ((this.idUserAgent == null && other.idUserAgent != null) || (this.idUserAgent != null && !this.idUserAgent.equals(other.idUserAgent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PortalcvUseragent{" + "idUserAgent=" + idUserAgent + ", device=" + device + ", os=" + os + ", browser=" + browser + ", osVersion=" + osVersion + ", browserVersion=" + browserVersion + ", fullUserAgent=" + fullUserAgent + '}';
    }
}
