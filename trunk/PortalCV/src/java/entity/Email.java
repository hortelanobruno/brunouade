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
@Table(name = "portalcv_email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e"),
    @NamedQuery(name = "Email.findByIdemail", query = "SELECT e FROM Email e WHERE e.idemail = :idemail")})
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idemail")
    private Integer idemail;
    @Basic(optional = false)
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "fromMail")
    private String fromMail;

    public Email() {
    }

    public Email(Integer idemail) {
        this.idemail = idemail;
    }

    public Email(Integer idemail, String title, String content) {
        this.idemail = idemail;
        this.title = title;
        this.content = content;
    }

    public Integer getIdemail() {
        return idemail;
    }

    public void setIdemail(Integer idemail) {
        this.idemail = idemail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemail != null ? idemail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Email)) {
            return false;
        }
        Email other = (Email) object;
        if ((this.idemail == null && other.idemail != null) || (this.idemail != null && !this.idemail.equals(other.idemail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Email[ idemail=" + idemail + " ]";
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }
}
