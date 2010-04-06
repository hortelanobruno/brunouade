/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package portal.db.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import portal.vo.ProvisioningVO;

/**
 *
 * @author brunoli
 */
@Entity
@Table(name="PROVISIONINGS")
public class ProvisioningEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="EMAIL")
    private String email;
    @Column(name="CLIENT_ID")
    private String clientId;
    @Column(name="CM_MAC")
    private String cmMac;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCmMac() {
        return cmMac;
    }

    public void setCmMac(String cmMac) {
        this.cmMac = cmMac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProvisioningVO getVO(){
        ProvisioningVO vo = new ProvisioningVO();
        vo.setClientId(clientId);
        vo.setCmMac(cmMac);
        vo.setEmail(email);
        vo.setId(id);
        return vo;
    }

    public void setVO(ProvisioningVO vo){
        this.setClientId(vo.getClientId());
        this.setCmMac(vo.getCmMac());
        this.setEmail(vo.getEmail());
        this.setId(vo.getId());
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
        if (!(object instanceof ProvisioningEntity)) {
            return false;
        }
        ProvisioningEntity other = (ProvisioningEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "portal.db.entity.ProvisioningEntity[id=" + id + "]";
    }

}
