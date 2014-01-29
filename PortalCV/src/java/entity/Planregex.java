/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@Entity
@Table(name = "portalcv_planregex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planregex.findAll", query = "SELECT p FROM Planregex p"),
    @NamedQuery(name = "Planregex.findByIdplanregex", query = "SELECT p FROM Planregex p WHERE p.idplanregex = :idplanregex"),
    @NamedQuery(name = "Planregex.findByRegex", query = "SELECT p FROM Planregex p WHERE p.regex = :regex"),
    @NamedQuery(name = "Planregex.findPlanNameByRegex", query = "SELECT p.planname FROM Planregex p WHERE :regex like CONCAT('%',p.regex,'%')"),
    @NamedQuery(name = "Planregex.findByPlanname", query = "SELECT p FROM Planregex p WHERE p.planname = :planname")})
public class Planregex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idplanregex")
    private Integer idplanregex;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "regex")
    private String regex;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "planname")
    private String planname;

    public Planregex() {
    }

    public Planregex(Integer idplanregex) {
        this.idplanregex = idplanregex;
    }

    public Planregex(Integer idplanregex, String regex, String planname) {
        this.idplanregex = idplanregex;
        this.regex = regex;
        this.planname = planname;
    }

    public Integer getIdplanregex() {
        return idplanregex;
    }

    public void setIdplanregex(Integer idplanregex) {
        this.idplanregex = idplanregex;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanregex != null ? idplanregex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planregex)) {
            return false;
        }
        Planregex other = (Planregex) object;
        if ((this.idplanregex == null && other.idplanregex != null) || (this.idplanregex != null && !this.idplanregex.equals(other.idplanregex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Planregex[ idplanregex=" + idplanregex + " ]";
    }
}
