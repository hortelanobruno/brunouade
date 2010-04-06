/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.db.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import portal.vo.UserPersonalInformationVO;

/**
 *
 * @author brunoli
 */
@Entity
@Table(name = "USERS_PERSONAL_INFORMATIONS")
public class UserPersonalInformationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPersonalInformationVO getVO() {
        UserPersonalInformationVO vo = new UserPersonalInformationVO();
        vo.setId(id);
        return vo;
    }

    public void setVO(UserPersonalInformationVO vo) {
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
        if (!(object instanceof UserPersonalInformationEntity)) {
            return false;
        }
        UserPersonalInformationEntity other = (UserPersonalInformationEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "portal.db.entity.UserPersonalInformationEntity[id=" + id + "]";
    }
}
