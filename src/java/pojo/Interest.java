/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mohammed
 */
@Entity
@Table(name = "interest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interest.findAll", query = "SELECT i FROM Interest i"),
    @NamedQuery(name = "Interest.findByInterestId", query = "SELECT i FROM Interest i WHERE i.interestId = :interestId"),
    @NamedQuery(name = "Interest.findByInterestName", query = "SELECT i FROM Interest i WHERE i.interestName = :interestName")})
public class Interest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "interest_id")
    private Integer interestId;
    @Column(name = "interest_name")
    private String interestName;
    @JoinTable(name = "user_has_interest", joinColumns = {
        @JoinColumn(name = "interest_id", referencedColumnName = "interest_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany
    private List<User> userList;

    public Interest() {
    }

    public Interest(Integer interestId) {
        this.interestId = interestId;
    }

    public Integer getInterestId() {
        return interestId;
    }

    public void setInterestId(Integer interestId) {
        this.interestId = interestId;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interestId != null ? interestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interest)) {
            return false;
        }
        Interest other = (Interest) object;
        if ((this.interestId == null && other.interestId != null) || (this.interestId != null && !this.interestId.equals(other.interestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Interest[ interestId=" + interestId + " ]";
    }
    
}
