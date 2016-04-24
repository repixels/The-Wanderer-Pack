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
import pojo.Interest;
import pojo.Address;
import pojo.User;

/**
 *
 * @author Mohammed
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getOrder1List() == null) {
            user.setOrder1List(new ArrayList<UserOrder>());
        }
        if (user.getInterestList() == null) {
            user.setInterestList(new ArrayList<Interest>());
        }
        if (user.getAddressList() == null) {
            user.setAddressList(new ArrayList<Address>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UserOrder> attachedOrder1List = new ArrayList<UserOrder>();
            for (UserOrder order1ListUserOrderToAttach : user.getOrder1List()) {
                order1ListUserOrderToAttach = em.getReference(order1ListUserOrderToAttach.getClass(), order1ListUserOrderToAttach.getOrderId());
                attachedOrder1List.add(order1ListUserOrderToAttach);
            }
            user.setOrder1List(attachedOrder1List);
            List<Interest> attachedInterestList = new ArrayList<Interest>();
            for (Interest interestListInterestToAttach : user.getInterestList()) {
                interestListInterestToAttach = em.getReference(interestListInterestToAttach.getClass(), interestListInterestToAttach.getInterestId());
                attachedInterestList.add(interestListInterestToAttach);
            }
            user.setInterestList(attachedInterestList);
            List<Address> attachedAddressList = new ArrayList<Address>();
            for (Address addressListAddressToAttach : user.getAddressList()) {
                addressListAddressToAttach = em.getReference(addressListAddressToAttach.getClass(), addressListAddressToAttach.getAddressId());
                attachedAddressList.add(addressListAddressToAttach);
            }
            user.setAddressList(attachedAddressList);
            em.persist(user);
            for (UserOrder order1ListUserOrder : user.getOrder1List()) {
                order1ListUserOrder.getUserList().add(user);
                order1ListUserOrder = em.merge(order1ListUserOrder);
            }
            for (Interest interestListInterest : user.getInterestList()) {
                interestListInterest.getUserList().add(user);
                interestListInterest = em.merge(interestListInterest);
            }
            for (Address addressListAddress : user.getAddressList()) {
                addressListAddress.getUserList().add(user);
                addressListAddress = em.merge(addressListAddress);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getUserId());
            List<UserOrder> order1ListOld = persistentUser.getOrder1List();
            List<UserOrder> order1ListNew = user.getOrder1List();
            List<Interest> interestListOld = persistentUser.getInterestList();
            List<Interest> interestListNew = user.getInterestList();
            List<Address> addressListOld = persistentUser.getAddressList();
            List<Address> addressListNew = user.getAddressList();
            List<UserOrder> attachedOrder1ListNew = new ArrayList<UserOrder>();
            for (UserOrder order1ListNewUserOrderToAttach : order1ListNew) {
                order1ListNewUserOrderToAttach = em.getReference(order1ListNewUserOrderToAttach.getClass(), order1ListNewUserOrderToAttach.getOrderId());
                attachedOrder1ListNew.add(order1ListNewUserOrderToAttach);
            }
            order1ListNew = attachedOrder1ListNew;
            user.setOrder1List(order1ListNew);
            List<Interest> attachedInterestListNew = new ArrayList<Interest>();
            for (Interest interestListNewInterestToAttach : interestListNew) {
                interestListNewInterestToAttach = em.getReference(interestListNewInterestToAttach.getClass(), interestListNewInterestToAttach.getInterestId());
                attachedInterestListNew.add(interestListNewInterestToAttach);
            }
            interestListNew = attachedInterestListNew;
            user.setInterestList(interestListNew);
            List<Address> attachedAddressListNew = new ArrayList<Address>();
            for (Address addressListNewAddressToAttach : addressListNew) {
                addressListNewAddressToAttach = em.getReference(addressListNewAddressToAttach.getClass(), addressListNewAddressToAttach.getAddressId());
                attachedAddressListNew.add(addressListNewAddressToAttach);
            }
            addressListNew = attachedAddressListNew;
            user.setAddressList(addressListNew);
            user = em.merge(user);
            for (UserOrder order1ListOldUserOrder : order1ListOld) {
                if (!order1ListNew.contains(order1ListOldUserOrder)) {
                    order1ListOldUserOrder.getUserList().remove(user);
                    order1ListOldUserOrder = em.merge(order1ListOldUserOrder);
                }
            }
            for (UserOrder order1ListNewUserOrder : order1ListNew) {
                if (!order1ListOld.contains(order1ListNewUserOrder)) {
                    order1ListNewUserOrder.getUserList().add(user);
                    order1ListNewUserOrder = em.merge(order1ListNewUserOrder);
                }
            }
            for (Interest interestListOldInterest : interestListOld) {
                if (!interestListNew.contains(interestListOldInterest)) {
                    interestListOldInterest.getUserList().remove(user);
                    interestListOldInterest = em.merge(interestListOldInterest);
                }
            }
            for (Interest interestListNewInterest : interestListNew) {
                if (!interestListOld.contains(interestListNewInterest)) {
                    interestListNewInterest.getUserList().add(user);
                    interestListNewInterest = em.merge(interestListNewInterest);
                }
            }
            for (Address addressListOldAddress : addressListOld) {
                if (!addressListNew.contains(addressListOldAddress)) {
                    addressListOldAddress.getUserList().remove(user);
                    addressListOldAddress = em.merge(addressListOldAddress);
                }
            }
            for (Address addressListNewAddress : addressListNew) {
                if (!addressListOld.contains(addressListNewAddress)) {
                    addressListNewAddress.getUserList().add(user);
                    addressListNewAddress = em.merge(addressListNewAddress);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getUserId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<UserOrder> order1List = user.getOrder1List();
            for (UserOrder order1ListUserOrder : order1List) {
                order1ListUserOrder.getUserList().remove(user);
                order1ListUserOrder = em.merge(order1ListUserOrder);
            }
            List<Interest> interestList = user.getInterestList();
            for (Interest interestListInterest : interestList) {
                interestListInterest.getUserList().remove(user);
                interestListInterest = em.merge(interestListInterest);
            }
            List<Address> addressList = user.getAddressList();
            for (Address addressListAddress : addressList) {
                addressListAddress.getUserList().remove(user);
                addressListAddress = em.merge(addressListAddress);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
