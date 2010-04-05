/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testjpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author brunoli
 */
@Entity
@Table(name = "OBJECTO_CON_LISTA")
public class ObjectoConLista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;
    @OneToMany
    private Collection<PlanEntity> planesTesteados;

    public ObjectoConLista() {
        planesTesteados = new ArrayList<PlanEntity>();
    }

    public Collection<PlanEntity> getPlanesTesteados() {
        return planesTesteados;
    }

    public void setPlanesTesteados(Collection<PlanEntity> planesTesteados) {
        this.planesTesteados = planesTesteados;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
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
        if (!(object instanceof ObjectoConLista)) {
            return false;
        }
        ObjectoConLista other = (ObjectoConLista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testjpa.ObjectoConLista[id=" + id + "]";
    }
}
