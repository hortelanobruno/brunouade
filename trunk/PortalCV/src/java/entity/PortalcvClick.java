/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_clicks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvClick.findAll", query = "SELECT p FROM PortalcvClick p"),
    @NamedQuery(name = "PortalcvClick.findByIdclicks", query = "SELECT p FROM PortalcvClick p WHERE p.idclicks = :idclicks"),
    @NamedQuery(name = "PortalcvClick.findByIp", query = "SELECT p FROM PortalcvClick p WHERE p.ip = :ip"),
    @NamedQuery(name = "PortalcvClick.findByMainCount", query = "SELECT p FROM PortalcvClick p WHERE p.mainCount = :mainCount"),
    @NamedQuery(name = "PortalcvClick.findByMainTime", query = "SELECT p FROM PortalcvClick p WHERE p.mainTime = :mainTime"),
    @NamedQuery(name = "PortalcvClick.findByStdButtonCount", query = "SELECT p FROM PortalcvClick p WHERE p.stdButtonCount = :stdButtonCount"),
    @NamedQuery(name = "PortalcvClick.findByStdButtonTime", query = "SELECT p FROM PortalcvClick p WHERE p.stdButtonTime = :stdButtonTime"),
    @NamedQuery(name = "PortalcvClick.findByPremiumButtonCount", query = "SELECT p FROM PortalcvClick p WHERE p.premiumButtonCount = :premiumButtonCount"),
    @NamedQuery(name = "PortalcvClick.findByPremiumButtonTime", query = "SELECT p FROM PortalcvClick p WHERE p.premiumButtonTime = :premiumButtonTime"),
    @NamedQuery(name = "PortalcvClick.findBySucursalVirtualReturnTime", query = "SELECT p FROM PortalcvClick p WHERE p.sucursalVirtualReturnTime = :sucursalVirtualReturnTime"),
    @NamedQuery(name = "PortalcvClick.findByCoaLoginDelay", query = "SELECT p FROM PortalcvClick p WHERE p.coaLoginDelay = :coaLoginDelay"),
    @NamedQuery(name = "PortalcvClick.findByCoaLoginTime", query = "SELECT p FROM PortalcvClick p WHERE p.coaLoginTime = :coaLoginTime"),
    @NamedQuery(name = "PortalcvClick.findByLoggedInTime", query = "SELECT p FROM PortalcvClick p WHERE p.loggedInTime = :loggedInTime"),
    @NamedQuery(name = "PortalcvClick.findByCoaLogoutDelay", query = "SELECT p FROM PortalcvClick p WHERE p.coaLogoutDelay = :coaLogoutDelay"),
    @NamedQuery(name = "PortalcvClick.findByCoaLogoutTime", query = "SELECT p FROM PortalcvClick p WHERE p.coaLogoutTime = :coaLogoutTime"),
    @NamedQuery(name = "PortalcvClick.findByLoggedOutTime", query = "SELECT p FROM PortalcvClick p WHERE p.loggedOutTime = :loggedOutTime")})
public class PortalcvClick implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclicks")
    private Integer idclicks;
    @Size(max = 45)
    @Column(name = "IP")
    private String ip;
    @Column(name = "mainCount")
    private Integer mainCount;
    @Column(name = "mainTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mainTime;
    @Column(name = "stdButtonCount")
    private Integer stdButtonCount;
    @Column(name = "stdButtonTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stdButtonTime;
    @Column(name = "premiumButtonCount")
    private Integer premiumButtonCount;
    @Column(name = "premiumButtonTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date premiumButtonTime;
    @Column(name = "sucursalVirtualReturnTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sucursalVirtualReturnTime;
    @Column(name = "coaLoginDelay")
    private BigInteger coaLoginDelay;
    @Column(name = "coaLoginTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date coaLoginTime;
    @Column(name = "loggedInTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggedInTime;
    @Column(name = "coaLogoutDelay")
    private BigInteger coaLogoutDelay;
    @Column(name = "coaLogoutTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date coaLogoutTime;
    @Column(name = "loggedOutTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggedOutTime;

    public PortalcvClick() {
    }

    public PortalcvClick(Integer idclicks) {
        this.idclicks = idclicks;
    }

    public Integer getIdclicks() {
        return idclicks;
    }

    public void setIdclicks(Integer idclicks) {
        this.idclicks = idclicks;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getMainCount() {
        return mainCount;
    }

    public void setMainCount(Integer mainCount) {
        this.mainCount = mainCount;
    }

    public Date getMainTime() {
        return mainTime;
    }

    public void setMainTime(Date mainTime) {
        this.mainTime = mainTime;
    }

    public Integer getStdButtonCount() {
        return stdButtonCount;
    }

    public void setStdButtonCount(Integer stdButtonCount) {
        this.stdButtonCount = stdButtonCount;
    }

    public Date getStdButtonTime() {
        return stdButtonTime;
    }

    public void setStdButtonTime(Date stdButtonTime) {
        this.stdButtonTime = stdButtonTime;
    }

    public Integer getPremiumButtonCount() {
        return premiumButtonCount;
    }

    public void setPremiumButtonCount(Integer premiumButtonCount) {
        this.premiumButtonCount = premiumButtonCount;
    }

    public Date getPremiumButtonTime() {
        return premiumButtonTime;
    }

    public void setPremiumButtonTime(Date premiumButtonTime) {
        this.premiumButtonTime = premiumButtonTime;
    }

    public Date getSucursalVirtualReturnTime() {
        return sucursalVirtualReturnTime;
    }

    public void setSucursalVirtualReturnTime(Date sucursalVirtualReturnTime) {
        this.sucursalVirtualReturnTime = sucursalVirtualReturnTime;
    }

    public BigInteger getCoaLoginDelay() {
        return coaLoginDelay;
    }

    public void setCoaLoginDelay(BigInteger coaLoginDelay) {
        this.coaLoginDelay = coaLoginDelay;
    }

    public Date getCoaLoginTime() {
        return coaLoginTime;
    }

    public void setCoaLoginTime(Date coaLoginTime) {
        this.coaLoginTime = coaLoginTime;
    }

    public Date getLoggedInTime() {
        return loggedInTime;
    }

    public void setLoggedInTime(Date loggedInTime) {
        this.loggedInTime = loggedInTime;
    }

    public BigInteger getCoaLogoutDelay() {
        return coaLogoutDelay;
    }

    public void setCoaLogoutDelay(BigInteger coaLogoutDelay) {
        this.coaLogoutDelay = coaLogoutDelay;
    }

    public Date getCoaLogoutTime() {
        return coaLogoutTime;
    }

    public void setCoaLogoutTime(Date coaLogoutTime) {
        this.coaLogoutTime = coaLogoutTime;
    }

    public Date getLoggedOutTime() {
        return loggedOutTime;
    }

    public void setLoggedOutTime(Date loggedOutTime) {
        this.loggedOutTime = loggedOutTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclicks != null ? idclicks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvClick)) {
            return false;
        }
        PortalcvClick other = (PortalcvClick) object;
        if ((this.idclicks == null && other.idclicks != null) || (this.idclicks != null && !this.idclicks.equals(other.idclicks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvClick[ idclicks=" + idclicks + " ]";
    }
    
}
