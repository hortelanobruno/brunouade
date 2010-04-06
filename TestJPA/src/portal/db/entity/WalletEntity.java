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
import portal.vo.WalletVO;

/**
 *
 * @author brunoli
 */
@Entity
@Table(name = "WALLETS")
public class WalletEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "WALLET_VALUE")
    private Double walletValue;

    public Double getWalletValue() {
        return walletValue;
    }

    public void setWalletValue(Double walletValue) {
        this.walletValue = walletValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WalletVO getVO() {
        WalletVO vo = new WalletVO();
        vo.setValue(walletValue);
        vo.setId(id);
        return vo;
    }

    public void setVO(WalletVO vo) {
        this.setId(vo.getId());
        this.setWalletValue(vo.getValue());
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
        if (!(object instanceof WalletEntity)) {
            return false;
        }
        WalletEntity other = (WalletEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "portal.db.entity.WalletEntity[id=" + id + "]";
    }
}
