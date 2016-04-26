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
import pojo.UserOrder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pojo.Category;
import pojo.Product;

/**
 *
 * @author Mohammed
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) {
        if (product.getOrder1List() == null) {
            product.setOrder1List(new ArrayList<UserOrder>());
        }
        if (product.getCategoryList() == null) {
            product.setCategoryList(new ArrayList<Category>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UserOrder> attachedOrder1List = new ArrayList<UserOrder>();
            for (UserOrder order1ListUserOrderToAttach : product.getOrder1List()) {
                order1ListUserOrderToAttach = em.getReference(order1ListUserOrderToAttach.getClass(), order1ListUserOrderToAttach.getOrderId());
                attachedOrder1List.add(order1ListUserOrderToAttach);
            }
            product.setOrder1List(attachedOrder1List);
            List<Category> attachedCategoryList = new ArrayList<Category>();
            for (Category categoryListCategoryToAttach : product.getCategoryList()) {
                categoryListCategoryToAttach = em.getReference(categoryListCategoryToAttach.getClass(), categoryListCategoryToAttach.getCategoryId());
                attachedCategoryList.add(categoryListCategoryToAttach);
            }
            product.setCategoryList(attachedCategoryList);
            em.persist(product);
            for (UserOrder order1ListUserOrder : product.getOrder1List()) {
                order1ListUserOrder.getProductList().add(product);
                order1ListUserOrder = em.merge(order1ListUserOrder);
            }
            for (Category categoryListCategory : product.getCategoryList()) {
                categoryListCategory.getProductList().add(product);
                categoryListCategory = em.merge(categoryListCategory);
            }
            em.getTransaction().commit();
        }catch(Exception ex){
            throw ex;
        } 
        finally {
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
            List<UserOrder> order1ListOld = persistentProduct.getOrder1List();
            List<UserOrder> order1ListNew = product.getOrder1List();
            List<Category> categoryListOld = persistentProduct.getCategoryList();
            List<Category> categoryListNew = product.getCategoryList();
            if(order1ListNew!=null&&order1ListNew.size()>0){
                List<UserOrder> attachedOrder1ListNew = new ArrayList<UserOrder>();
                for (UserOrder order1ListNewUserOrderToAttach : order1ListNew) {
                    order1ListNewUserOrderToAttach = em.getReference(order1ListNewUserOrderToAttach.getClass(), order1ListNewUserOrderToAttach.getOrderId());
                    attachedOrder1ListNew.add(order1ListNewUserOrderToAttach);
                }
                order1ListNew = attachedOrder1ListNew;
                product.setOrder1List(order1ListNew);
            }
            else{
            order1ListNew=new ArrayList<UserOrder>();
            }
            List<Category> attachedCategoryListNew = new ArrayList<Category>();
            for (Category categoryListNewCategoryToAttach : categoryListNew) {
                categoryListNewCategoryToAttach = em.getReference(categoryListNewCategoryToAttach.getClass(), categoryListNewCategoryToAttach.getCategoryId());
                attachedCategoryListNew.add(categoryListNewCategoryToAttach);
            }
            categoryListNew = attachedCategoryListNew;
            product.setCategoryList(categoryListNew);
            product = em.merge(product);
            for (UserOrder order1ListOldUserOrder : order1ListOld) {
                if (!order1ListNew.contains(order1ListOldUserOrder)) {
                    order1ListOldUserOrder.getProductList().remove(product);
                    order1ListOldUserOrder = em.merge(order1ListOldUserOrder);
                }
            }
            for (UserOrder order1ListNewUserOrder : order1ListNew) {
                if (!order1ListOld.contains(order1ListNewUserOrder)) {
                    order1ListNewUserOrder.getProductList().add(product);
                    order1ListNewUserOrder = em.merge(order1ListNewUserOrder);
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
            List<UserOrder> order1List = product.getOrder1List();
            for (UserOrder order1ListUserOrder : order1List) {
                order1ListUserOrder.getProductList().remove(product);
                order1ListUserOrder = em.merge(order1ListUserOrder);
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
