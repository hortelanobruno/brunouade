/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_stats")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvStat.findAll", query = "SELECT p FROM PortalcvStat p"),
    @NamedQuery(name = "PortalcvStat.findByIdstats", query = "SELECT p FROM PortalcvStat p WHERE p.idstats = :idstats"),
    @NamedQuery(name = "PortalcvStat.findByTimestamp", query = "SELECT p FROM PortalcvStat p WHERE p.timestamp = :timestamp"),
    @NamedQuery(name = "PortalcvStat.findByAp", query = "SELECT p FROM PortalcvStat p WHERE p.ap = :ap"),
    @NamedQuery(name = "PortalcvStat.findByDevice", query = "SELECT p FROM PortalcvStat p WHERE p.device = :device"),
    @NamedQuery(name = "PortalcvStat.findByUserAgent", query = "SELECT p FROM PortalcvStat p WHERE p.userAgent = :userAgent"),
    @NamedQuery(name = "PortalcvStat.findByConnectedAP", query = "SELECT p FROM PortalcvStat p WHERE p.connectedAP = :connectedAP"),
    @NamedQuery(name = "PortalcvStat.findByRedirectedPortal", query = "SELECT p FROM PortalcvStat p WHERE p.redirectedPortal = :redirectedPortal"),
    @NamedQuery(name = "PortalcvStat.findByClickPremium", query = "SELECT p FROM PortalcvStat p WHERE p.clickPremium = :clickPremium"),
    @NamedQuery(name = "PortalcvStat.findByClickStandard", query = "SELECT p FROM PortalcvStat p WHERE p.clickStandard = :clickStandard"),
    @NamedQuery(name = "PortalcvStat.findByLoggedPremium", query = "SELECT p FROM PortalcvStat p WHERE p.loggedPremium = :loggedPremium"),
    @NamedQuery(name = "PortalcvStat.findByLoggedStandard", query = "SELECT p FROM PortalcvStat p WHERE p.loggedStandard = :loggedStandard")})
public class PortalcvStat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstats")
    private Integer idstats;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Column(name = "ap")
    private Integer ap;
    @Column(name = "device")
    private Integer device;
    @Column(name = "userAgent")
    private Integer userAgent;
    @Column(name = "connectedAP")
    private Integer connectedAP;
    @Column(name = "redirectedPortal")
    private Integer redirectedPortal;
    @Column(name = "clickPremium")
    private Integer clickPremium;
    @Column(name = "clickStandard")
    private Integer clickStandard;
    @Column(name = "loggedPremium")
    private Integer loggedPremium;
    @Column(name = "loggedStandard")
    private Integer loggedStandard;

    public PortalcvStat() {
    }

    public PortalcvStat(Integer idstats) {
        this.idstats = idstats;
    }

    public Integer getIdstats() {
        return idstats;
    }

    public void setIdstats(Integer idstats) {
        this.idstats = idstats;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getAp() {
        return ap;
    }

    public void setAp(Integer ap) {
        this.ap = ap;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public Integer getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(Integer userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getConnectedAP() {
        return connectedAP;
    }

    public void setConnectedAP(Integer connectedAP) {
        this.connectedAP = connectedAP;
    }

    public Integer getRedirectedPortal() {
        return redirectedPortal;
    }

    public void setRedirectedPortal(Integer redirectedPortal) {
        this.redirectedPortal = redirectedPortal;
    }

    public Integer getClickPremium() {
        return clickPremium;
    }

    public void setClickPremium(Integer clickPremium) {
        this.clickPremium = clickPremium;
    }

    public Integer getClickStandard() {
        return clickStandard;
    }

    public void setClickStandard(Integer clickStandard) {
        this.clickStandard = clickStandard;
    }

    public Integer getLoggedPremium() {
        return loggedPremium;
    }

    public void setLoggedPremium(Integer loggedPremium) {
        this.loggedPremium = loggedPremium;
    }

    public Integer getLoggedStandard() {
        return loggedStandard;
    }

    public void setLoggedStandard(Integer loggedStandard) {
        this.loggedStandard = loggedStandard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstats != null ? idstats.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvStat)) {
            return false;
        }
        PortalcvStat other = (PortalcvStat) object;
        if ((this.idstats == null && other.idstats != null) || (this.idstats != null && !this.idstats.equals(other.idstats))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvStat[ idstats=" + idstats + " ]";
    }
    
}
