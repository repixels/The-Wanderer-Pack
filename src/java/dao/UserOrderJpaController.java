/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pojo.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Product;
import pojo.UserOrder;

/**
 *
 * @author Mohammed
 */
public class UserOrderJpaController implements Serializable {

    public UserOrderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserOrder userOrder) {
        if (userOrder.getUserList() == null) {
            userOrder.setUserList(new ArrayList<User>());
        }
        if (userOrder.getProductList() == null) {
            userOrder.setProductList(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<User> attachedUserList = new ArrayList<User>();
            for (User userListUserToAttach : userOrder.getUserList()) {
                userListUserToAttach = em.getReference(userListUserToAttach.getClass(), userListUserToAttach.getUserId());
                attachedUserList.add(userListUserToAttach);
            }
            userOrder.setUserList(attachedUserList);
            List<Product> attachedProductList = new ArrayList<Product>();
            for (Product productListProductToAttach : userOrder.getProductList()) {
                productListProductToAttach = em.getReference(productListProductToAttach.getClass(), productListProductToAttach.getProductId());
                attachedProductList.add(productListProductToAttach);
            }
            userOrder.setProductList(attachedProductList);
            em.persist(userOrder);
            for (User userListUser : userOrder.getUserList()) {
                userListUser.getOrder1List().add(userOrder);
                userListUser = em.merge(userListUser);
            }
            for (Product productListProduct : userOrder.getProductList()) {
                productListProduct.getOrder1List().add(userOrder);
                productListProduct = em.merge(productListProduct);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserOrder userOrder) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserOrder persistentUserOrder = em.find(UserOrder.class, userOrder.getOrderId());
            List<User> userListOld = persistentUserOrder.getUserList();
            List<User> userListNew = userOrder.getUserList();
            List<Product> productListOld = persistentUserOrder.getProductList();
            List<Product> productListNew = userOrder.getProductList();
            List<User> attachedUserListNew = new ArrayList<User>();
            for (User userListNewUserToAttach : userListNew) {
                userListNewUserToAttach = em.getReference(userListNewUserToAttach.getClass(), userListNewUserToAttach.getUserId());
                attachedUserListNew.add(userListNewUserToAttach);
            }
            userListNew = attachedUserListNew;
            userOrder.setUserList(userListNew);
            List<Product> attachedProductListNew = new ArrayList<Product>();
            for (Product productListNewProductToAttach : productListNew) {
                productListNewProductToAttach = em.getReference(productListNewProductToAttach.getClass(), productListNewProductToAttach.getProductId());
                attachedProductListNew.add(productListNewProductToAttach);
            }
            productListNew = attachedProductListNew;
            userOrder.setProductList(productListNew);
            userOrder = em.merge(userOrder);
            for (User userListOldUser : userListOld) {
                if (!userListNew.contains(userListOldUser)) {
                    userListOldUser.getOrder1List().remove(userOrder);
                    userListOldUser = em.merge(userListOldUser);
                }
            }
            for (User userListNewUser : userListNew) {
                if (!userListOld.contains(userListNewUser)) {
                    userListNewUser.getOrder1List().add(userOrder);
                    userListNewUser = em.merge(userListNewUser);
                }
            }
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    productListOldProduct.getOrder1List().remove(userOrder);
                    productListOldProduct = em.merge(productListOldProduct);
                }
            }
            for (Product productListNewProduct : productListNew) {
                if (!productListOld.contains(productListNewProduct)) {
                    productListNewProduct.getOrder1List().add(userOrder);
                    productListNewProduct = em.merge(productListNewProduct);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userOrder.getOrderId();
                if (findUserOrder(id) == null) {
                    throw new NonexistentEntityException("The userOrder with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserOrder userOrder;
            try {
                userOrder = em.getReference(UserOrder.class, id);
                userOrder.getOrderId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userOrder with id " + id + " no longer exists.", enfe);
            }
            List<User> userList = userOrder.getUserList();
            for (User userListUser : userList) {
                userListUser.getOrder1List().remove(userOrder);
                userListUser = em.merge(userListUser);
            }
            List<Product> productList = userOrder.getProductList();
            for (Product productListProduct : productList) {
                productListProduct.getOrder1List().remove(userOrder);
                productListProduct = em.merge(productListProduct);
            }
            em.remove(userOrder);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserOrder> findUserOrderEntities() {
        return findUserOrderEntities(true, -1, -1);
    }

    public List<UserOrder> findUserOrderEntities(int maxResults, int firstResult) {
        return findUserOrderEntities(false, maxResults, firstResult);
    }

    private List<UserOrder> findUserOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserOrder.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public UserOrder findUserOrder(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserOrder> rt = cq.from(UserOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
