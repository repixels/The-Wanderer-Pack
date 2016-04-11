/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pojo.UserOrder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Category;
import pojo.Product;

/**
 *
 * @author Ehab
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) throws PreexistingEntityException, Exception {
        if (product.getUserOrderList() == null) {
            product.setUserOrderList(new ArrayList<UserOrder>());
        }
        if (product.getCategoryList() == null) {
            product.setCategoryList(new ArrayList<Category>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UserOrder> attachedUserOrderList = new ArrayList<UserOrder>();
            for (UserOrder userOrderListUserOrderToAttach : product.getUserOrderList()) {
                userOrderListUserOrderToAttach = em.getReference(userOrderListUserOrderToAttach.getClass(), userOrderListUserOrderToAttach.getOrderId());
                attachedUserOrderList.add(userOrderListUserOrderToAttach);
            }
            product.setUserOrderList(attachedUserOrderList);
            List<Category> attachedCategoryList = new ArrayList<Category>();
            for (Category categoryListCategoryToAttach : product.getCategoryList()) {
                categoryListCategoryToAttach = em.getReference(categoryListCategoryToAttach.getClass(), categoryListCategoryToAttach.getCategoryId());
                attachedCategoryList.add(categoryListCategoryToAttach);
            }
            product.setCategoryList(attachedCategoryList);
            em.persist(product);
            for (UserOrder userOrderListUserOrder : product.getUserOrderList()) {
                userOrderListUserOrder.getProductList().add(product);
                userOrderListUserOrder = em.merge(userOrderListUserOrder);
            }
            for (Category categoryListCategory : product.getCategoryList()) {
                categoryListCategory.getProductList().add(product);
                categoryListCategory = em.merge(categoryListCategory);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProduct(product.getProductId()) != null) {
                throw new PreexistingEntityException("Product " + product + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getProductId());
            List<UserOrder> userOrderListOld = persistentProduct.getUserOrderList();
            List<UserOrder> userOrderListNew = product.getUserOrderList();
            List<Category> categoryListOld = persistentProduct.getCategoryList();
            List<Category> categoryListNew = product.getCategoryList();
            List<UserOrder> attachedUserOrderListNew = new ArrayList<UserOrder>();
            for (UserOrder userOrderListNewUserOrderToAttach : userOrderListNew) {
                userOrderListNewUserOrderToAttach = em.getReference(userOrderListNewUserOrderToAttach.getClass(), userOrderListNewUserOrderToAttach.getOrderId());
                attachedUserOrderListNew.add(userOrderListNewUserOrderToAttach);
            }
            userOrderListNew = attachedUserOrderListNew;
            product.setUserOrderList(userOrderListNew);
            List<Category> attachedCategoryListNew = new ArrayList<Category>();
            for (Category categoryListNewCategoryToAttach : categoryListNew) {
                categoryListNewCategoryToAttach = em.getReference(categoryListNewCategoryToAttach.getClass(), categoryListNewCategoryToAttach.getCategoryId());
                attachedCategoryListNew.add(categoryListNewCategoryToAttach);
            }
            categoryListNew = attachedCategoryListNew;
            product.setCategoryList(categoryListNew);
            product = em.merge(product);
            for (UserOrder userOrderListOldUserOrder : userOrderListOld) {
                if (!userOrderListNew.contains(userOrderListOldUserOrder)) {
                    userOrderListOldUserOrder.getProductList().remove(product);
                    userOrderListOldUserOrder = em.merge(userOrderListOldUserOrder);
                }
            }
            for (UserOrder userOrderListNewUserOrder : userOrderListNew) {
                if (!userOrderListOld.contains(userOrderListNewUserOrder)) {
                    userOrderListNewUserOrder.getProductList().add(product);
                    userOrderListNewUserOrder = em.merge(userOrderListNewUserOrder);
                }
            }
            for (Category categoryListOldCategory : categoryListOld) {
                if (!categoryListNew.contains(categoryListOldCategory)) {
                    categoryListOldCategory.getProductList().remove(product);
                    categoryListOldCategory = em.merge(categoryListOldCategory);
                }
            }
            for (Category categoryListNewCategory : categoryListNew) {
                if (!categoryListOld.contains(categoryListNewCategory)) {
                    categoryListNewCategory.getProductList().add(product);
                    categoryListNewCategory = em.merge(categoryListNewCategory);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getProductId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
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
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getProductId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<UserOrder> userOrderList = product.getUserOrderList();
            for (UserOrder userOrderListUserOrder : userOrderList) {
                userOrderListUserOrder.getProductList().remove(product);
                userOrderListUserOrder = em.merge(userOrderListUserOrder);
            }
            List<Category> categoryList = product.getCategoryList();
            for (Category categoryListCategory : categoryList) {
                categoryListCategory.getProductList().remove(product);
                categoryListCategory = em.merge(categoryListCategory);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
