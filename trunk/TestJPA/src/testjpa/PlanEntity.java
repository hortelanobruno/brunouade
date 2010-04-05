/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testjpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author brunoli
 */
@Entity
public class PlanEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "PACKAGE_ID")
    private Integer packageId;
    @Column(name = "TEST_PACKAGE_ID")
    private Integer testPackageId;
    @Column(name = "SUBSCRIPTION_PRICE")
    private Double subscriptionPrice;//pesos
    @Column(name = "TEST_TIME")
    private Long testTime;//seconds

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Double getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionPrice(Double subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public Integer getTestPackageId() {
        return testPackageId;
    }

    public void setTestPackageId(Integer testPackageId) {
        this.testPackageId = testPackageId;
    }

    public Long getTestTime() {
        return testTime;
    }

    public void setTestTime(Long testTime) {
        this.testTime = testTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof PlanEntity)) {
            return false;
        }
        PlanEntity other = (PlanEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testjpa.PlanEntity[id=" + id + "]";
    }
}
