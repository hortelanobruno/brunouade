/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.vo;

/**
 *
 * @author brunoli
 */
public class PlanVO {

    private Long id;
    private Integer packageId;
    private Integer testPackageId;
    private Double subscriptionPrice;//pesos
    private Long testTime;//seconds

    public PlanVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
