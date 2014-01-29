/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "radacct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Radacct.findAll", query = "SELECT r FROM Radacct r"),
    @NamedQuery(name = "Radacct.findByRadAcctId", query = "SELECT r FROM Radacct r WHERE r.radAcctId = :radAcctId"),
    @NamedQuery(name = "Radacct.findByAcctSessionId", query = "SELECT r FROM Radacct r WHERE r.acctSessionId = :acctSessionId"),
    @NamedQuery(name = "Radacct.findByAcctUniqueId", query = "SELECT r FROM Radacct r WHERE r.acctUniqueId = :acctUniqueId"),
    @NamedQuery(name = "Radacct.findByUserName", query = "SELECT r FROM Radacct r WHERE r.userName = :userName"),
    @NamedQuery(name = "Radacct.findByRealm", query = "SELECT r FROM Radacct r WHERE r.realm = :realm"),
    @NamedQuery(name = "Radacct.findByNASIPAddress", query = "SELECT r FROM Radacct r WHERE r.nASIPAddress = :nASIPAddress"),
    @NamedQuery(name = "Radacct.findByNASPortId", query = "SELECT r FROM Radacct r WHERE r.nASPortId = :nASPortId"),
    @NamedQuery(name = "Radacct.findByNASPortType", query = "SELECT r FROM Radacct r WHERE r.nASPortType = :nASPortType"),
    @NamedQuery(name = "Radacct.findByAcctStartTime", query = "SELECT r FROM Radacct r WHERE r.acctStartTime = :acctStartTime"),
    @NamedQuery(name = "Radacct.findByAcctStopTime", query = "SELECT r FROM Radacct r WHERE r.acctStopTime = :acctStopTime"),
    @NamedQuery(name = "Radacct.findByAcctSessionTime", query = "SELECT r FROM Radacct r WHERE r.acctSessionTime = :acctSessionTime"),
    @NamedQuery(name = "Radacct.findByAcctAuthentic", query = "SELECT r FROM Radacct r WHERE r.acctAuthentic = :acctAuthentic"),
    @NamedQuery(name = "Radacct.findByConnectInfostart", query = "SELECT r FROM Radacct r WHERE r.connectInfostart = :connectInfostart"),
    @NamedQuery(name = "Radacct.findByConnectInfostop", query = "SELECT r FROM Radacct r WHERE r.connectInfostop = :connectInfostop"),
    @NamedQuery(name = "Radacct.findByAcctInputOctets", query = "SELECT r FROM Radacct r WHERE r.acctInputOctets = :acctInputOctets"),
    @NamedQuery(name = "Radacct.findByAcctOutputOctets", query = "SELECT r FROM Radacct r WHERE r.acctOutputOctets = :acctOutputOctets"),
    @NamedQuery(name = "Radacct.findByCalledStationId", query = "SELECT r FROM Radacct r WHERE r.calledStationId = :calledStationId"),
    @NamedQuery(name = "Radacct.findByCallingStationId", query = "SELECT r FROM Radacct r WHERE r.callingStationId = :callingStationId"),
    @NamedQuery(name = "Radacct.findByAcctTerminateCause", query = "SELECT r FROM Radacct r WHERE r.acctTerminateCause = :acctTerminateCause"),
    @NamedQuery(name = "Radacct.findByServiceType", query = "SELECT r FROM Radacct r WHERE r.serviceType = :serviceType"),
    @NamedQuery(name = "Radacct.findByFramedProtocol", query = "SELECT r FROM Radacct r WHERE r.framedProtocol = :framedProtocol"),
    @NamedQuery(name = "Radacct.findByFramedIPAddress", query = "SELECT r FROM Radacct r WHERE r.framedIPAddress = :framedIPAddress"),
    @NamedQuery(name = "Radacct.findByAcctStartDelay", query = "SELECT r FROM Radacct r WHERE r.acctStartDelay = :acctStartDelay"),
    @NamedQuery(name = "Radacct.findByAcctStopDelay", query = "SELECT r FROM Radacct r WHERE r.acctStopDelay = :acctStopDelay"),
    @NamedQuery(name = "Radacct.findByXAscendSessionSvrKey", query = "SELECT r FROM Radacct r WHERE r.xAscendSessionSvrKey = :xAscendSessionSvrKey"),
    @NamedQuery(name = "Radacct.findByCiscoAVPair", query = "SELECT r FROM Radacct r WHERE r.ciscoAVPair = :ciscoAVPair"),
    @NamedQuery(name = "Radacct.findByCiscoServiceInfo", query = "SELECT r FROM Radacct r WHERE r.ciscoServiceInfo = :ciscoServiceInfo"),
    @NamedQuery(name = "Radacct.findByCiscoControlInfo", query = "SELECT r FROM Radacct r WHERE r.ciscoControlInfo = :ciscoControlInfo"),
    @NamedQuery(name = "Radacct.findByLogintype", query = "SELECT r FROM Radacct r WHERE r.logintype = :logintype"),
    @NamedQuery(name = "Radacct.findByUseragent", query = "SELECT r FROM Radacct r WHERE r.useragent = :useragent"),
    @NamedQuery(name = "Radacct.findByDevice", query = "SELECT r FROM Radacct r WHERE r.device = :device"),
    @NamedQuery(name = "Radacct.findByOs", query = "SELECT r FROM Radacct r WHERE r.os = :os"),
    @NamedQuery(name = "Radacct.findByOsVersion", query = "SELECT r FROM Radacct r WHERE r.osVersion = :osVersion"),
    @NamedQuery(name = "Radacct.findByBrowser", query = "SELECT r FROM Radacct r WHERE r.browser = :browser"),
    @NamedQuery(name = "Radacct.findByBrowserVersion", query = "SELECT r FROM Radacct r WHERE r.browserVersion = :browserVersion"),
    @NamedQuery(name = "Radacct.findByFrameIPAndAcctStopTimeNull", query = "SELECT r.userName,r.ciscoServiceInfo,r.acctStartTime FROM Radacct r WHERE r.framedIPAddress = :framedIPAddress and r.ciscoServiceInfo not like '' and r.acctStopTime is NULL")})
public class Radacct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RadAcctId")
    private Long radAcctId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "AcctSessionId")
    private String acctSessionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "AcctUniqueId")
    private String acctUniqueId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "UserName")
    private String userName;
    @Size(max = 64)
    @Column(name = "Realm")
    private String realm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NASIPAddress")
    private String nASIPAddress;
    @Size(max = 15)
    @Column(name = "NASPortId")
    private String nASPortId;
    @Size(max = 32)
    @Column(name = "NASPortType")
    private String nASPortType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AcctStartTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acctStartTime;
    @Column(name = "AcctStopTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acctStopTime;
    @Column(name = "AcctSessionTime")
    private Integer acctSessionTime;
    @Size(max = 32)
    @Column(name = "AcctAuthentic")
    private String acctAuthentic;
    @Size(max = 50)
    @Column(name = "ConnectInfo_start")
    private String connectInfostart;
    @Size(max = 50)
    @Column(name = "ConnectInfo_stop")
    private String connectInfostop;
    @Column(name = "AcctInputOctets")
    private BigInteger acctInputOctets;
    @Column(name = "AcctOutputOctets")
    private BigInteger acctOutputOctets;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CalledStationId")
    private String calledStationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CallingStationId")
    private String callingStationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "AcctTerminateCause")
    private String acctTerminateCause;
    @Size(max = 32)
    @Column(name = "ServiceType")
    private String serviceType;
    @Size(max = 32)
    @Column(name = "FramedProtocol")
    private String framedProtocol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "FramedIPAddress")
    private String framedIPAddress;
    @Column(name = "AcctStartDelay")
    private Integer acctStartDelay;
    @Column(name = "AcctStopDelay")
    private Integer acctStopDelay;
    @Size(max = 10)
    @Column(name = "XAscendSessionSvrKey")
    private String xAscendSessionSvrKey;
    @Size(max = 200)
    @Column(name = "ciscoAVPair")
    private String ciscoAVPair;
    @Size(max = 60)
    @Column(name = "ciscoServiceInfo")
    private String ciscoServiceInfo;
    @Size(max = 60)
    @Column(name = "ciscoControlInfo")
    private String ciscoControlInfo;
    @Size(max = 45)
    @Column(name = "logintype")
    private String logintype;
    @Size(max = 500)
    @Column(name = "useragent")
    private String useragent;
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

    public Radacct() {
    }

    public Radacct(Long radAcctId) {
        this.radAcctId = radAcctId;
    }

    public Radacct(Long radAcctId, String acctSessionId, String acctUniqueId, String userName, String nASIPAddress, Date acctStartTime, String calledStationId, String callingStationId, String acctTerminateCause, String framedIPAddress) {
        this.radAcctId = radAcctId;
        this.acctSessionId = acctSessionId;
        this.acctUniqueId = acctUniqueId;
        this.userName = userName;
        this.nASIPAddress = nASIPAddress;
        this.acctStartTime = acctStartTime;
        this.calledStationId = calledStationId;
        this.callingStationId = callingStationId;
        this.acctTerminateCause = acctTerminateCause;
        this.framedIPAddress = framedIPAddress;
    }

    public Long getRadAcctId() {
        return radAcctId;
    }

    public void setRadAcctId(Long radAcctId) {
        this.radAcctId = radAcctId;
    }

    public String getAcctSessionId() {
        return acctSessionId;
    }

    public void setAcctSessionId(String acctSessionId) {
        this.acctSessionId = acctSessionId;
    }

    public String getAcctUniqueId() {
        return acctUniqueId;
    }

    public void setAcctUniqueId(String acctUniqueId) {
        this.acctUniqueId = acctUniqueId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getNASIPAddress() {
        return nASIPAddress;
    }

    public void setNASIPAddress(String nASIPAddress) {
        this.nASIPAddress = nASIPAddress;
    }

    public String getNASPortId() {
        return nASPortId;
    }

    public void setNASPortId(String nASPortId) {
        this.nASPortId = nASPortId;
    }

    public String getNASPortType() {
        return nASPortType;
    }

    public void setNASPortType(String nASPortType) {
        this.nASPortType = nASPortType;
    }

    public Date getAcctStartTime() {
        return acctStartTime;
    }

    public void setAcctStartTime(Date acctStartTime) {
        this.acctStartTime = acctStartTime;
    }

    public Date getAcctStopTime() {
        return acctStopTime;
    }

    public void setAcctStopTime(Date acctStopTime) {
        this.acctStopTime = acctStopTime;
    }

    public Integer getAcctSessionTime() {
        return acctSessionTime;
    }

    public void setAcctSessionTime(Integer acctSessionTime) {
        this.acctSessionTime = acctSessionTime;
    }

    public String getAcctAuthentic() {
        return acctAuthentic;
    }

    public void setAcctAuthentic(String acctAuthentic) {
        this.acctAuthentic = acctAuthentic;
    }

    public String getConnectInfostart() {
        return connectInfostart;
    }

    public void setConnectInfostart(String connectInfostart) {
        this.connectInfostart = connectInfostart;
    }

    public String getConnectInfostop() {
        return connectInfostop;
    }

    public void setConnectInfostop(String connectInfostop) {
        this.connectInfostop = connectInfostop;
    }

    public BigInteger getAcctInputOctets() {
        return acctInputOctets;
    }

    public void setAcctInputOctets(BigInteger acctInputOctets) {
        this.acctInputOctets = acctInputOctets;
    }

    public BigInteger getAcctOutputOctets() {
        return acctOutputOctets;
    }

    public void setAcctOutputOctets(BigInteger acctOutputOctets) {
        this.acctOutputOctets = acctOutputOctets;
    }

    public String getCalledStationId() {
        return calledStationId;
    }

    public void setCalledStationId(String calledStationId) {
        this.calledStationId = calledStationId;
    }

    public String getCallingStationId() {
        return callingStationId;
    }

    public void setCallingStationId(String callingStationId) {
        this.callingStationId = callingStationId;
    }

    public String getAcctTerminateCause() {
        return acctTerminateCause;
    }

    public void setAcctTerminateCause(String acctTerminateCause) {
        this.acctTerminateCause = acctTerminateCause;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getFramedProtocol() {
        return framedProtocol;
    }

    public void setFramedProtocol(String framedProtocol) {
        this.framedProtocol = framedProtocol;
    }

    public String getFramedIPAddress() {
        return framedIPAddress;
    }

    public void setFramedIPAddress(String framedIPAddress) {
        this.framedIPAddress = framedIPAddress;
    }

    public Integer getAcctStartDelay() {
        return acctStartDelay;
    }

    public void setAcctStartDelay(Integer acctStartDelay) {
        this.acctStartDelay = acctStartDelay;
    }

    public Integer getAcctStopDelay() {
        return acctStopDelay;
    }

    public void setAcctStopDelay(Integer acctStopDelay) {
        this.acctStopDelay = acctStopDelay;
    }

    public String getXAscendSessionSvrKey() {
        return xAscendSessionSvrKey;
    }

    public void setXAscendSessionSvrKey(String xAscendSessionSvrKey) {
        this.xAscendSessionSvrKey = xAscendSessionSvrKey;
    }

    public String getCiscoAVPair() {
        return ciscoAVPair;
    }

    public void setCiscoAVPair(String ciscoAVPair) {
        this.ciscoAVPair = ciscoAVPair;
    }

    public String getCiscoServiceInfo() {
        return ciscoServiceInfo;
    }

    public void setCiscoServiceInfo(String ciscoServiceInfo) {
        this.ciscoServiceInfo = ciscoServiceInfo;
    }

    public String getCiscoControlInfo() {
        return ciscoControlInfo;
    }

    public void setCiscoControlInfo(String ciscoControlInfo) {
        this.ciscoControlInfo = ciscoControlInfo;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (radAcctId != null ? radAcctId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Radacct)) {
            return false;
        }
        Radacct other = (Radacct) object;
        if ((this.radAcctId == null && other.radAcctId != null) || (this.radAcctId != null && !this.radAcctId.equals(other.radAcctId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Radacct[ radAcctId=" + radAcctId + " ]";
    }
}
