/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.appschool.jpacontroller;

import com.school.appschool.entities.UsersTbl;
import com.school.appschool.jpacontroller.exceptions.NonexistentEntityException;
import com.school.appschool.jpacontroller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author sala305
 */
public class UsersTblJpaController implements Serializable {

    public UsersTblJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsersTbl usersTbl) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usersTbl);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsersTbl(usersTbl.getEmail()) != null) {
                throw new PreexistingEntityException("UsersTbl " + usersTbl + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsersTbl usersTbl) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usersTbl = em.merge(usersTbl);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usersTbl.getEmail();
                if (findUsersTbl(id) == null) {
                    throw new NonexistentEntityException("The usersTbl with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsersTbl usersTbl;
            try {
                usersTbl = em.getReference(UsersTbl.class, id);
                usersTbl.getEmail();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usersTbl with id " + id + " no longer exists.", enfe);
            }
            em.remove(usersTbl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsersTbl> findUsersTblEntities() {
        return findUsersTblEntities(true, -1, -1);
    }

    public List<UsersTbl> findUsersTblEntities(int maxResults, int firstResult) {
        return findUsersTblEntities(false, maxResults, firstResult);
    }

    private List<UsersTbl> findUsersTblEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsersTbl.class));
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

    public UsersTbl findUsersTbl(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsersTbl.class, id);
        } finally {
            em.close();
        }
    }

    public UsersTbl findUsersByEmailAndPassTbl(String email, String pass) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("UsersTbl.findByEmailAndPass");
            q.setParameter(email, email);
            q.setParameter(pass, pass);
            return (UsersTbl) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getUsersTblCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsersTbl> rt = cq.from(UsersTbl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
