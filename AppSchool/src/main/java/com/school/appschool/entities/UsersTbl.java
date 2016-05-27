/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.appschool.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sala305
 */
@Entity
@Table(name = "users_tbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersTbl.findAll", query = "SELECT u FROM UsersTbl u"),
    @NamedQuery(name = "UsersTbl.findByEmail", query = "SELECT u FROM UsersTbl u WHERE u.email = :email"),
    @NamedQuery(name = "UsersTbl.findByEmailAndPass", query = "SELECT u FROM UsersTbl u WHERE u.email = :email AND u.pass = :pass"),
    @NamedQuery(name = "UsersTbl.findByPass", query = "SELECT u FROM UsersTbl u WHERE u.pass = :pass")})
public class UsersTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "pass")
    private String pass;

    public UsersTbl() {
    }

    public UsersTbl(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersTbl)) {
            return false;
        }
        UsersTbl other = (UsersTbl) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.school.appschool.entities.UsersTbl[ email=" + email + " ]";
    }
    
}
