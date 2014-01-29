/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_quota_config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuotaConfig.findAll", query = "SELECT q FROM QuotaConfig q"),
    @NamedQuery(name = "QuotaConfig.findById", query = "SELECT q FROM QuotaConfig q WHERE q.id = :id"),
    @NamedQuery(name = "QuotaConfig.findByPlan", query = "SELECT q FROM QuotaConfig q WHERE q.plan = :plan"),
    @NamedQuery(name = "QuotaConfig.findByPlanByService", query = "SELECT q FROM QuotaConfig q WHERE q.plan = :plan and q.service = :service"),
    @NamedQuery(name = "QuotaConfig.findByPlanByServiceByType", query = "SELECT q FROM QuotaConfig q WHERE q.plan = :plan and q.service = :service and q.type = :type"),
    @NamedQuery(name = "QuotaConfig.findByQuota", query = "SELECT q FROM QuotaConfig q WHERE q.quota = :quota"),
    @NamedQuery(name = "QuotaConfig.findByDosage", query = "SELECT q FROM QuotaConfig q WHERE q.dosage = :dosage"),
    @NamedQuery(name = "QuotaConfig.findAllPlanNameByService", query = "SELECT q.plan FROM QuotaConfig q WHERE q.service = :service"),
    @NamedQuery(name = "QuotaConfig.findByType", query = "SELECT q FROM QuotaConfig q WHERE q.type = :type")})
public class QuotaConfig implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "plan")
    private String plan;
    @Column(name = "quota")
    private BigInteger quota;
    @Column(name = "dosage")
    private BigInteger dosage;
    @Size(max = 10)
    @Column(name = "type")
    private String type;
    @Size(max = 45)
    @Column(name = "service")
    private String service;

    public QuotaConfig() {
    }

    public QuotaConfig(Integer id) {
        this.id = id;
    }

    public QuotaConfig(Integer id, String plan) {
        this.id = id;
        this.plan = plan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public BigInteger getQuota() {
        return quota;
    }

    public void setQuota(BigInteger quota) {
        this.quota = quota;
    }

    public BigInteger getDosage() {
        return dosage;
    }

    public void setDosage(BigInteger dosage) {
        this.dosage = dosage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof QuotaConfig)) {
            return false;
        }
        QuotaConfig other = (QuotaConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.QuotaConfig[ id=" + id + " ]";
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    
}
