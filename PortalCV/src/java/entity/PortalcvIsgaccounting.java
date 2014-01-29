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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "portalcv_isgaccounting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvIsgaccounting.findAll", query = "SELECT p FROM PortalcvIsgaccounting p"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByIdisgaccounting", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.idisgaccounting = :idisgaccounting"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByRadAcctId", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.radAcctId = :radAcctId"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctSessionId", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctSessionId = :acctSessionId"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctUniqueId", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctUniqueId = :acctUniqueId"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByUsername", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.username = :username"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByRealm", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.realm = :realm"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctStart", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctStart = :acctStart"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctStop", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctStop = :acctStop"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctSessiontime", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctSessiontime = :acctSessiontime"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctInputOctets", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctInputOctets = :acctInputOctets"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctOutputOctets", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctOutputOctets = :acctOutputOctets"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctterminateCause", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctterminateCause = :acctterminateCause"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByFramedIPAddress", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.framedIPAddress = :framedIPAddress"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctStartDelay", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctStartDelay = :acctStartDelay"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByAcctStopDelay", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.acctStopDelay = :acctStopDelay"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByCiscoAVPair", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.ciscoAVPair = :ciscoAVPair"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByCiscoServiceInfo", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.ciscoServiceInfo = :ciscoServiceInfo"),
    @NamedQuery(name = "PortalcvIsgaccounting.findByCiscoControlInfo", query = "SELECT p FROM PortalcvIsgaccounting p WHERE p.ciscoControlInfo = :ciscoControlInfo")})
public class PortalcvIsgaccounting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idisgaccounting")
    private Integer idisgaccounting;
    @Column(name = "RadAcctId")
    private BigInteger radAcctId;
    @Size(max = 32)
    @Column(name = "AcctSessionId")
    private String acctSessionId;
    @Size(max = 32)
    @Column(name = "AcctUniqueId")
    private String acctUniqueId;
    @Size(max = 64)
    @Column(name = "Username")
    private String username;
    @Size(max = 64)
    @Column(name = "Realm")
    private String realm;
    @Column(name = "acctStart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acctStart;
    @Column(name = "acctStop")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acctStop;
    @Column(name = "AcctSessiontime")
    private Integer acctSessiontime;
    @Column(name = "AcctInputOctets")
    private BigInteger acctInputOctets;
    @Column(name = "AcctOutputOctets")
    private BigInteger acctOutputOctets;
    @Size(max = 32)
    @Column(name = "AcctterminateCause")
    private String acctterminateCause;
    @Size(max = 15)
    @Column(name = "FramedIPAddress")
    private String framedIPAddress;
    @Column(name = "AcctStartDelay")
    private Integer acctStartDelay;
    @Column(name = "AcctStopDelay")
    private Integer acctStopDelay;
    @Size(max = 200)
    @Column(name = "ciscoAVPair")
    private String ciscoAVPair;
    @Size(max = 60)
    @Column(name = "ciscoServiceInfo")
    private String ciscoServiceInfo;
    @Size(max = 60)
    @Column(name = "ciscoControlInfo")
    private String ciscoControlInfo;
    @JoinColumn(name = "wlcAccounting_idwlcAccounting", referencedColumnName = "idwlcAccounting")
    @ManyToOne(optional = false)
    private PortalcvWlcaccounting wlcAccountingidwlcAccounting;

    public PortalcvIsgaccounting() {
    }

    public PortalcvIsgaccounting(Integer idisgaccounting) {
        this.idisgaccounting = idisgaccounting;
    }

    public Integer getIdisgaccounting() {
        return idisgaccounting;
    }

    public void setIdisgaccounting(Integer idisgaccounting) {
        this.idisgaccounting = idisgaccounting;
    }

    public BigInteger getRadAcctId() {
        return radAcctId;
    }

    public void setRadAcctId(BigInteger radAcctId) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public Date getAcctStart() {
        return acctStart;
    }

    public void setAcctStart(Date acctStart) {
        this.acctStart = acctStart;
    }

    public Date getAcctStop() {
        return acctStop;
    }

    public void setAcctStop(Date acctStop) {
        this.acctStop = acctStop;
    }

    public Integer getAcctSessiontime() {
        return acctSessiontime;
    }

    public void setAcctSessiontime(Integer acctSessiontime) {
        this.acctSessiontime = acctSessiontime;
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

    public String getAcctterminateCause() {
        return acctterminateCause;
    }

    public void setAcctterminateCause(String acctterminateCause) {
        this.acctterminateCause = acctterminateCause;
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

    public PortalcvWlcaccounting getWlcAccountingidwlcAccounting() {
        return wlcAccountingidwlcAccounting;
    }

    public void setWlcAccountingidwlcAccounting(PortalcvWlcaccounting wlcAccountingidwlcAccounting) {
        this.wlcAccountingidwlcAccounting = wlcAccountingidwlcAccounting;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idisgaccounting != null ? idisgaccounting.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvIsgaccounting)) {
            return false;
        }
        PortalcvIsgaccounting other = (PortalcvIsgaccounting) object;
        if ((this.idisgaccounting == null && other.idisgaccounting != null) || (this.idisgaccounting != null && !this.idisgaccounting.equals(other.idisgaccounting))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PortalcvIsgaccounting[ idisgaccounting=" + idisgaccounting + " ]";
    }
    
}
