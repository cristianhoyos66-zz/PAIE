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
@Table(name = "students_tbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentsTbl.findAll", query = "SELECT s FROM StudentsTbl s"),
    @NamedQuery(name = "StudentsTbl.findByCode", query = "SELECT s FROM StudentsTbl s WHERE s.code = :code"),
    @NamedQuery(name = "StudentsTbl.findByLastname", query = "SELECT s FROM StudentsTbl s WHERE s.lastname = :lastname"),
    @NamedQuery(name = "StudentsTbl.findByName", query = "SELECT s FROM StudentsTbl s WHERE s.name = :name"),
    @NamedQuery(name = "StudentsTbl.findByPhone", query = "SELECT s FROM StudentsTbl s WHERE s.phone = :phone")})
public class StudentsTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;

    public StudentsTbl() {
    }

    public StudentsTbl(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentsTbl)) {
            return false;
        }
        StudentsTbl other = (StudentsTbl) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.school.appschool.entities.StudentsTbl[ code=" + code + " ]";
    }
    
}
