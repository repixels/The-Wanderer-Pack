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
import pojo.Interest;
import pojo.Address;
import pojo.User;

/**
 *
 * @author Ehab
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws PreexistingEntityException, Exception {
        if (user.getUserOrderList() == null) {
            user.setUserOrderList(new ArrayList<UserOrder>());
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
            List<UserOrder> attachedUserOrderList = new ArrayList<UserOrder>();
            for (UserOrder userOrderListUserOrderToAttach : user.getUserOrderList()) {
                userOrderListUserOrderToAttach = em.getReference(userOrderListUserOrderToAttach.getClass(), userOrderListUserOrderToAttach.getOrderId());
                attachedUserOrderList.add(userOrderListUserOrderToAttach);
            }
            user.setUserOrderList(attachedUserOrderList);
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
            for (UserOrder userOrderListUserOrder : user.getUserOrderList()) {
                userOrderListUserOrder.getUserList().add(user);
                userOrderListUserOrder = em.merge(userOrderListUserOrder);
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
        } catch (Exception ex) {
            if (findUser(user.getUserId()) != null) {
                throw new PreexistingEntityException("User " + user + " already exists.", ex);
            }
            throw ex;
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
            List<UserOrder> userOrderListOld = persistentUser.getUserOrderList();
            List<UserOrder> userOrderListNew = user.getUserOrderList();
            List<Interest> interestListOld = persistentUser.getInterestList();
            List<Interest> interestListNew = user.getInterestList();
            List<Address> addressListOld = persistentUser.getAddressList();
            List<Address> addressListNew = user.getAddressList();
            List<UserOrder> attachedUserOrderListNew = new ArrayList<UserOrder>();
            for (UserOrder userOrderListNewUserOrderToAttach : userOrderListNew) {
                userOrderListNewUserOrderToAttach = em.getReference(userOrderListNewUserOrderToAttach.getClass(), userOrderListNewUserOrderToAttach.getOrderId());
                attachedUserOrderListNew.add(userOrderListNewUserOrderToAttach);
            }
            userOrderListNew = attachedUserOrderListNew;
            user.setUserOrderList(userOrderListNew);
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
            for (UserOrder userOrderListOldUserOrder : userOrderListOld) {
                if (!userOrderListNew.contains(userOrderListOldUserOrder)) {
                    userOrderListOldUserOrder.getUserList().remove(user);
                    userOrderListOldUserOrder = em.merge(userOrderListOldUserOrder);
                }
            }
            for (UserOrder userOrderListNewUserOrder : userOrderListNew) {
                if (!userOrderListOld.contains(userOrderListNewUserOrder)) {
                    userOrderListNewUserOrder.getUserList().add(user);
                    userOrderListNewUserOrder = em.merge(userOrderListNewUserOrder);
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
            List<UserOrder> userOrderList = user.getUserOrderList();
            for (UserOrder userOrderListUserOrder : userOrderList) {
                userOrderListUserOrder.getUserList().remove(user);
                userOrderListUserOrder = em.merge(userOrderListUserOrder);
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
