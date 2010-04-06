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
import portal.vo.ServiceVO;

/**
 *
 * @author brunoli
 */
@Entity
@Table(name = "SERVICES")
public class ServiceEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NANE")
    private String name;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "DURATION")
    private Long duration;//seconds
    @Column(name = "PACK_VALUE")
    private Long packValue;//kb
    @Column(name = "STOCK")
    private Integer stock;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPackValue() {
        return packValue;
    }

    public void setPackValue(Long packValue) {
        this.packValue = packValue;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceVO getVO() {
        ServiceVO vo = new ServiceVO();
        vo.setDuration(duration);
        vo.setId(id);
        vo.setName(name);
        vo.setPackValue(packValue);
        vo.setPrice(price);
        vo.setStock(stock);
        return vo;
    }

    public void setVO(ServiceVO vo) {
        this.setDuration(vo.getDuration());
        this.setId(vo.getId());
        this.setName(vo.getName());
        this.setPackValue(vo.getPackValue());
        this.setPrice(vo.getPrice());
        this.setStock(vo.getStock());
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
        if (!(object instanceof ServiceEntity)) {
            return false;
        }
        ServiceEntity other = (ServiceEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "portal.db.entity.ServiceEntity[id=" + id + "]";
    }
}
