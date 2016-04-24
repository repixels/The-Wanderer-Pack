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
import pojo.Category;
import pojo.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mohammed
 */
public class CategoryJpaController implements Serializable {

    public CategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Category category) {
        if (category.getProductList() == null) {
            category.setProductList(new ArrayList<Product>());
        }
        if (category.getCategoryList() == null) {
            category.setCategoryList(new ArrayList<Category>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category categoryParentId = category.getCategoryParentId();
            if (categoryParentId != null) {
                categoryParentId = em.getReference(categoryParentId.getClass(), categoryParentId.getCategoryId());
                category.setCategoryParentId(categoryParentId);
            }
            List<Product> attachedProductList = new ArrayList<Product>();
            for (Product productListProductToAttach : category.getProductList()) {
                productListProductToAttach = em.getReference(productListProductToAttach.getClass(), productListProductToAttach.getProductId());
                attachedProductList.add(productListProductToAttach);
            }
            category.setProductList(attachedProductList);
            List<Category> attachedCategoryList = new ArrayList<Category>();
            for (Category categoryListCategoryToAttach : category.getCategoryList()) {
                categoryListCategoryToAttach = em.getReference(categoryListCategoryToAttach.getClass(), categoryListCategoryToAttach.getCategoryId());
                attachedCategoryList.add(categoryListCategoryToAttach);
            }
            category.setCategoryList(attachedCategoryList);
            em.persist(category);
            if (categoryParentId != null) {
                categoryParentId.getCategoryList().add(category);
                categoryParentId = em.merge(categoryParentId);
            }
            for (Product productListProduct : category.getProductList()) {
                productListProduct.getCategoryList().add(category);
                productListProduct = em.merge(productListProduct);
            }
            for (Category categoryListCategory : category.getCategoryList()) {
                Category oldCategoryParentIdOfCategoryListCategory = categoryListCategory.getCategoryParentId();
                categoryListCategory.setCategoryParentId(category);
                categoryListCategory = em.merge(categoryListCategory);
                if (oldCategoryParentIdOfCategoryListCategory != null) {
                    oldCategoryParentIdOfCategoryListCategory.getCategoryList().remove(categoryListCategory);
                    oldCategoryParentIdOfCategoryListCategory = em.merge(oldCategoryParentIdOfCategoryListCategory);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Category category) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category persistentCategory = em.find(Category.class, category.getCategoryId());
            Category categoryParentIdOld = persistentCategory.getCategoryParentId();
            Category categoryParentIdNew = category.getCategoryParentId();
            List<Product> productListOld = persistentCategory.getProductList();
            List<Product> productListNew = category.getProductList();
            List<Category> categoryListOld = persistentCategory.getCategoryList();
            List<Category> categoryListNew = category.getCategoryList();
            if (categoryParentIdNew != null) {
                categoryParentIdNew = em.getReference(categoryParentIdNew.getClass(), categoryParentIdNew.getCategoryId());
                category.setCategoryParentId(categoryParentIdNew);
            }
            List<Product> attachedProductListNew = new ArrayList<Product>();
            for (Product productListNewProductToAttach : productListNew) {
                productListNewProductToAttach = em.getReference(productListNewProductToAttach.getClass(), productListNewProductToAttach.getProductId());
                attachedProductListNew.add(productListNewProductToAttach);
            }
            productListNew = attachedProductListNew;
            category.setProductList(productListNew);
            List<Category> attachedCategoryListNew = new ArrayList<Category>();
            for (Category categoryListNewCategoryToAttach : categoryListNew) {
                categoryListNewCategoryToAttach = em.getReference(categoryListNewCategoryToAttach.getClass(), categoryListNewCategoryToAttach.getCategoryId());
                attachedCategoryListNew.add(categoryListNewCategoryToAttach);
            }
            categoryListNew = attachedCategoryListNew;
            category.setCategoryList(categoryListNew);
            category = em.merge(category);
            if (categoryParentIdOld != null && !categoryParentIdOld.equals(categoryParentIdNew)) {
                categoryParentIdOld.getCategoryList().remove(category);
                categoryParentIdOld = em.merge(categoryParentIdOld);
            }
            if (categoryParentIdNew != null && !categoryParentIdNew.equals(categoryParentIdOld)) {
                categoryParentIdNew.getCategoryList().add(category);
                categoryParentIdNew = em.merge(categoryParentIdNew);
            }
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    productListOldProduct.getCategoryList().remove(category);
                    productListOldProduct = em.merge(productListOldProduct);
                }
            }
            for (Product productListNewProduct : productListNew) {
                if (!productListOld.contains(productListNewProduct)) {
                    productListNewProduct.getCategoryList().add(category);
                    productListNewProduct = em.merge(productListNewProduct);
                }
            }
            for (Category categoryListOldCategory : categoryListOld) {
                if (!categoryListNew.contains(categoryListOldCategory)) {
                    categoryListOldCategory.setCategoryParentId(null);
                    categoryListOldCategory = em.merge(categoryListOldCategory);
                }
            }
            for (Category categoryListNewCategory : categoryListNew) {
                if (!categoryListOld.contains(categoryListNewCategory)) {
                    Category oldCategoryParentIdOfCategoryListNewCategory = categoryListNewCategory.getCategoryParentId();
                    categoryListNewCategory.setCategoryParentId(category);
                    categoryListNewCategory = em.merge(categoryListNewCategory);
                    if (oldCategoryParentIdOfCategoryListNewCategory != null && !oldCategoryParentIdOfCategoryListNewCategory.equals(category)) {
                        oldCategoryParentIdOfCategoryListNewCategory.getCategoryList().remove(categoryListNewCategory);
                        oldCategoryParentIdOfCategoryListNewCategory = em.merge(oldCategoryParentIdOfCategoryListNewCategory);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = category.getCategoryId();
                if (findCategory(id) == null) {
                    throw new NonexistentEntityException("The category with id " + id + " no longer exists.");
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
            Category category;
            try {
                category = em.getReference(Category.class, id);
                category.getCategoryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The category with id " + id + " no longer exists.", enfe);
            }
            Category categoryParentId = category.getCategoryParentId();
            if (categoryParentId != null) {
                categoryParentId.getCategoryList().remove(category);
                categoryParentId = em.merge(categoryParentId);
            }
            List<Product> productList = category.getProductList();
            for (Product productListProduct : productList) {
                productListProduct.getCategoryList().remove(category);
                productListProduct = em.merge(productListProduct);
            }
            List<Category> categoryList = category.getCategoryList();
            for (Category categoryListCategory : categoryList) {
                categoryListCategory.setCategoryParentId(null);
                categoryListCategory = em.merge(categoryListCategory);
            }
            em.remove(category);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Category> findCategoryEntities() {
        return findCategoryEntities(true, -1, -1);
    }

    public List<Category> findCategoryEntities(int maxResults, int firstResult) {
        return findCategoryEntities(false, maxResults, firstResult);
    }

    private List<Category> findCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Category.class));
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

    public Category findCategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Category> rt = cq.from(Category.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
