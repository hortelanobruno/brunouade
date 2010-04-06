/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.vo;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author brunoli
 */
public class UserVO {

    private Long id;
    private String username;
    private String password;
    private UserPersonalInformationVO userPersonalInformation;
    private WalletVO wallet;
    private ProvisioningVO provisioning;
    private Collection<ServiceVO> servicesTested;
    private Collection<ServiceVO> servicesSubscripted;
    private Collection<PlanVO> plansTested;

    public UserVO() {
        servicesTested = new ArrayList<ServiceVO>();
        servicesSubscripted = new ArrayList<ServiceVO>();
        plansTested = new ArrayList<PlanVO>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<PlanVO> getPlansTested() {
        return plansTested;
    }

    public void setPlansTested(Collection<PlanVO> plansTested) {
        this.plansTested = plansTested;
    }

    public ProvisioningVO getProvisioning() {
        return provisioning;
    }

    public void setProvisioning(ProvisioningVO provisioning) {
        this.provisioning = provisioning;
    }

    public Collection<ServiceVO> getServicesSubscripted() {
        return servicesSubscripted;
    }

    public void setServicesSubscripted(Collection<ServiceVO> servicesSubscripted) {
        this.servicesSubscripted = servicesSubscripted;
    }

    public Collection<ServiceVO> getServicesTested() {
        return servicesTested;
    }

    public void setServicesTested(Collection<ServiceVO> servicesTested) {
        this.servicesTested = servicesTested;
    }

    public UserPersonalInformationVO getUserPersonalInformation() {
        return userPersonalInformation;
    }

    public void setUserPersonalInformation(UserPersonalInformationVO userPersonalInformation) {
        this.userPersonalInformation = userPersonalInformation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public WalletVO getWallet() {
        return wallet;
    }

    public void setWallet(WalletVO wallet) {
        this.wallet = wallet;
    }
}
