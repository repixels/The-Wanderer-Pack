/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mohammed
 */
@Entity
@Table(name = "wandererpack_db.order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserOrder.findAll", query = "SELECT o FROM UserOrder o"),
    @NamedQuery(name = "UserOrder.findByOrderId", query = "SELECT o FROM UserOrder o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "UserOrder.findByOrderDate", query = "SELECT o FROM UserOrder o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "UserOrder.findByOrderStatus", query = "SELECT o FROM UserOrder o WHERE o.orderStatus = :orderStatus"),
    @NamedQuery(name = "UserOrder.findByOrderAmount", query = "SELECT o FROM UserOrder o WHERE o.orderAmount = :orderAmount")})
public class UserOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "order_status")
    private String orderStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "order_amount")
    private BigDecimal orderAmount;
    @JoinTable(name = "order_has_user", joinColumns = {
        @JoinColumn(name = "order_id", referencedColumnName = "order_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<User> userList;
    @JoinTable(name = "order_has_product", joinColumns = {
        @JoinColumn(name = "order_id", referencedColumnName = "order_id")}, inverseJoinColumns = {
        @JoinColumn(name = "product_id", referencedColumnName = "product_id")})
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Product> productList;

    public UserOrder() {
    }

    public UserOrder(Integer orderId) {
        this.orderId = orderId;
    }

    public UserOrder(Integer orderId, Date orderDate, String orderStatus, BigDecimal orderAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserOrder)) {
            return false;
        }
        UserOrder other = (UserOrder) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Order1[ orderId=" + orderId + " ]";
    }
    
}
