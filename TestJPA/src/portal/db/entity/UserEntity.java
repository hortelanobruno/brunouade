/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import portal.vo.PlanVO;
import portal.vo.ServiceVO;
import portal.vo.UserVO;

/**
 *
 * @author brunoli
 */
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private UserPersonalInformationEntity personalInformation;
    @OneToOne(cascade = CascadeType.ALL)
    private WalletEntity wallet;
    @OneToOne(cascade = CascadeType.ALL)
    private ProvisioningEntity provisioning;
    @OneToMany
    private Collection<ServiceEntity> servicesTested;
    @OneToMany
    private Collection<ServiceEntity> servicesSubscripted;
    @OneToMany
    private Collection<PlanEntity> plansTested;

    public UserEntity() {
        servicesTested = new ArrayList<ServiceEntity>();
        servicesSubscripted = new ArrayList<ServiceEntity>();
        plansTested = new ArrayList<PlanEntity>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPersonalInformationEntity getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(UserPersonalInformationEntity personalInformation) {
        this.personalInformation = personalInformation;
    }

    public Collection<PlanEntity> getPlansTested() {
        return plansTested;
    }

    public void setPlansTested(Collection<PlanEntity> plansTested) {
        this.plansTested = plansTested;
    }

    public ProvisioningEntity getProvisioning() {
        return provisioning;
    }

    public void setProvisioning(ProvisioningEntity provisioning) {
        this.provisioning = provisioning;
    }

    public Collection<ServiceEntity> getServicesSubscripted() {
        return servicesSubscripted;
    }

    public void setServicesSubscripted(Collection<ServiceEntity> servicesSubscripted) {
        this.servicesSubscripted = servicesSubscripted;
    }

    public Collection<ServiceEntity> getServicesTested() {
        return servicesTested;
    }

    public void setServicesTested(Collection<ServiceEntity> servicesTested) {
        this.servicesTested = servicesTested;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public WalletEntity getWallet() {
        return wallet;
    }

    public void setWallet(WalletEntity wallet) {
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserVO getVO() {
        UserVO vo = new UserVO();
        vo.setId(id);
        vo.setPassword(password);
        vo.setUsername(username);
        vo.setProvisioning(provisioning.getVO());
        vo.setWallet(wallet.getVO());
        vo.setUserPersonalInformation(personalInformation.getVO());
        for (ServiceEntity serviceEntity : servicesTested) {
            vo.getServicesTested().add(serviceEntity.getVO());
        }
        for (ServiceEntity serviceEntity : servicesSubscripted) {
            vo.getServicesSubscripted().add(serviceEntity.getVO());
        }
        for (PlanEntity planEntity : plansTested) {
            vo.getPlansTested().add(planEntity.getVO());
        }
        return vo;
    }

    public void setVO(UserVO vo) {
        this.setUsername(vo.getUsername());
        this.setPassword(vo.getPassword());
        this.personalInformation = new UserPersonalInformationEntity();
        this.personalInformation.setVO(vo.getUserPersonalInformation());
        this.wallet = new WalletEntity();
        this.wallet.setVO(vo.getWallet());
        this.provisioning = new ProvisioningEntity();
        this.provisioning.setVO(vo.getProvisioning());
        PlanEntity p;
        for (PlanVO plan : vo.getPlansTested()) {
            p = new PlanEntity();
            p.setVO(plan);
            this.plansTested.add(p);
        }
        ServiceEntity s;
        for (ServiceVO service : vo.getServicesSubscripted()) {
            s = new ServiceEntity();
            s.setVO(service);
            this.servicesSubscripted.add(s);
        }
        for (ServiceVO service : vo.getServicesTested()) {
            s = new ServiceEntity();
            s.setVO(service);
            this.servicesTested.add(s);
        }
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
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "portal.db.entity.UserEntity[id=" + id + "]";
    }
}
