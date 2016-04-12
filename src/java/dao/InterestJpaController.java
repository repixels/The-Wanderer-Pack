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
import pojo.Interest;

/**
 *
 * @author Mohammed
 */
public class InterestJpaController implements Serializable {

    public InterestJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Interest interest) {
        if (interest.getUserList() == null) {
            interest.setUserList(new ArrayList<User>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<User> attachedUserList = new ArrayList<User>();
            for (User userListUserToAttach : interest.getUserList()) {
                userListUserToAttach = em.getReference(userListUserToAttach.getClass(), userListUserToAttach.getUserId());
                attachedUserList.add(userListUserToAttach);
            }
            interest.setUserList(attachedUserList);
            em.persist(interest);
            for (User userListUser : interest.getUserList()) {
                userListUser.getInterestList().add(interest);
                userListUser = em.merge(userListUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Interest interest) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Interest persistentInterest = em.find(Interest.class, interest.getInterestId());
            List<User> userListOld = persistentInterest.getUserList();
            List<User> userListNew = interest.getUserList();
            List<User> attachedUserListNew = new ArrayList<User>();
            for (User userListNewUserToAttach : userListNew) {
                userListNewUserToAttach = em.getReference(userListNewUserToAttach.getClass(), userListNewUserToAttach.getUserId());
                attachedUserListNew.add(userListNewUserToAttach);
            }
            userListNew = attachedUserListNew;
            interest.setUserList(userListNew);
            interest = em.merge(interest);
            for (User userListOldUser : userListOld) {
                if (!userListNew.contains(userListOldUser)) {
                    userListOldUser.getInterestList().remove(interest);
                    userListOldUser = em.merge(userListOldUser);
                }
            }
            for (User userListNewUser : userListNew) {
                if (!userListOld.contains(userListNewUser)) {
                    userListNewUser.getInterestList().add(interest);
                    userListNewUser = em.merge(userListNewUser);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = interest.getInterestId();
                if (findInterest(id) == null) {
                    throw new NonexistentEntityException("The interest with id " + id + " no longer exists.");
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
            Interest interest;
            try {
                interest = em.getReference(Interest.class, id);
                interest.getInterestId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The interest with id " + id + " no longer exists.", enfe);
            }
            List<User> userList = interest.getUserList();
            for (User userListUser : userList) {
                userListUser.getInterestList().remove(interest);
                userListUser = em.merge(userListUser);
            }
            em.remove(interest);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Interest> findInterestEntities() {
        return findInterestEntities(true, -1, -1);
    }

    public List<Interest> findInterestEntities(int maxResults, int firstResult) {
        return findInterestEntities(false, maxResults, firstResult);
    }

    private List<Interest> findInterestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Interest.class));
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

    public Interest findInterest(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Interest.class, id);
        } finally {
            em.close();
        }
    }

    public int getInterestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Interest> rt = cq.from(Interest.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
