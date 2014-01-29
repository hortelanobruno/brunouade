/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Brunoli
 */
@Entity
@Table(name = "portalcv_wlcaccounting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PortalcvWlcaccounting.findAll", query = "SELECT p FROM PortalcvWlcaccounting p"),
    @NamedQuery(name = "PortalcvWlcaccounting.findByIdwlcAccounting", query = "SELECT p FROM PortalcvWlcaccounting p WHERE p.idwlcAccounting = :idwlcAccounting"),
    @NamedQuery(name = "PortalcvWlcaccounting.findByAcctStart", query = "SELECT p FROM PortalcvWlcaccounting p WHERE p.acctStart = :acctStart"),
    @NamedQuery(name = "PortalcvWlcaccounting.findByAcctStop", query = "SELECT p FROM PortalcvWlcaccounting p WHERE p.acctStop = :acctStop"),
    @NamedQuery(name = "PortalcvWlcaccounting.findByIpcpe", query = "SELECT p FROM PortalcvWlcaccounting p WHERE p.ipcpe = :ipcpe"),
    @NamedQuery(name = "PortalcvWlcaccounting.findByMaccpe", query = "SELECT p FROM PortalcvWlcaccounting p WHERE p.maccpe = :maccpe"),
    @NamedQuery(name = "PortalcvWlcaccounting.findByIpcpeAndAcctStopNull", query = "SELECT p FROM PortalcvWlcaccounting p WHERE p.ipcpe = :ipcpe and p.acctStop IS NULL ORDER BY p.idwlcAccounting desc")})
public class PortalcvWlcaccounting implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wlcAccountingidwlcAccounting")
    private Collection<PortalcvIsgaccounting> portalcvIsgaccountingCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idwlcAccounting")
    private Integer idwlcAccounting;
    @Basic(optional = false)
    @Column(name = "acctStart")
    private Timestamp acctStart;
    @Column(name = "acctStop")
    private Timestamp acctStop;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "IPCPE")
    private String ipcpe;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "MACCPE")
    private String maccpe;
    @JoinColumn(name = "idAP", referencedColumnName = "idAP")
    @ManyToOne(optional = false)
    private PortalcvAp idAP;

    public PortalcvWlcaccounting() {
    }

    public PortalcvWlcaccounting(Integer idwlcAccounting) {
        this.idwlcAccounting = idwlcAccounting;
    }

    public PortalcvWlcaccounting(Integer idwlcAccounting, Timestamp acctStart, String ipcpe, String maccpe) {
        this.idwlcAccounting = idwlcAccounting;
        this.acctStart = acctStart;
        this.ipcpe = ipcpe;
        this.maccpe = maccpe;
    }

    public Integer getIdwlcAccounting() {
        return idwlcAccounting;
    }

    public void setIdwlcAccounting(Integer idwlcAccounting) {
        this.idwlcAccounting = idwlcAccounting;
    }

    public Timestamp getAcctStart() {
        return acctStart;
    }

    public void setAcctStart(Timestamp acctStart) {
        this.acctStart = acctStart;
    }

    public Timestamp getAcctStop() {
        return acctStop;
    }

    public void setAcctStop(Timestamp acctStop) {
        this.acctStop = acctStop;
    }

    public String getIpcpe() {
        return ipcpe;
    }

    public void setIpcpe(String ipcpe) {
        this.ipcpe = ipcpe;
    }

    public String getMaccpe() {
        return maccpe;
    }

    public void setMaccpe(String maccpe) {
        this.maccpe = maccpe;
    }

    public PortalcvAp getIdAP() {
        return idAP;
    }

    public void setIdAP(PortalcvAp idAP) {
        this.idAP = idAP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idwlcAccounting != null ? idwlcAccounting.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortalcvWlcaccounting)) {
            return false;
        }
        PortalcvWlcaccounting other = (PortalcvWlcaccounting) object;
        if ((this.idwlcAccounting == null && other.idwlcAccounting != null) || (this.idwlcAccounting != null && !this.idwlcAccounting.equals(other.idwlcAccounting))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PortalcvWlcaccounting{" + "portalcvIsgaccountingCollection=" + portalcvIsgaccountingCollection + ", idwlcAccounting=" + idwlcAccounting + ", acctStart=" + acctStart + ", acctStop=" + acctStop + ", ipcpe=" + ipcpe + ", maccpe=" + maccpe + ", idAP=" + idAP + '}';
    }

    @XmlTransient
    public Collection<PortalcvIsgaccounting> getPortalcvIsgaccountingCollection() {
        return portalcvIsgaccountingCollection;
    }

    public void setPortalcvIsgaccountingCollection(Collection<PortalcvIsgaccounting> portalcvIsgaccountingCollection) {
        this.portalcvIsgaccountingCollection = portalcvIsgaccountingCollection;
    }
}
