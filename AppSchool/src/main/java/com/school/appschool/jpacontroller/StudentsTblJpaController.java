/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.school.appschool.jpacontroller;

import com.school.appschool.entities.StudentsTbl;
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
public class StudentsTblJpaController implements Serializable {

    public StudentsTblJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentsTbl studentsTbl) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(studentsTbl);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStudentsTbl(studentsTbl.getCode()) != null) {
                throw new PreexistingEntityException("StudentsTbl " + studentsTbl + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StudentsTbl studentsTbl) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            studentsTbl = em.merge(studentsTbl);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = studentsTbl.getCode();
                if (findStudentsTbl(id) == null) {
                    throw new NonexistentEntityException("The studentsTbl with id " + id + " no longer exists.");
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
            StudentsTbl studentsTbl;
            try {
                studentsTbl = em.getReference(StudentsTbl.class, id);
                studentsTbl.getCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentsTbl with id " + id + " no longer exists.", enfe);
            }
            em.remove(studentsTbl);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StudentsTbl> findStudentsTblEntities() {
        return findStudentsTblEntities(true, -1, -1);
    }

    public List<StudentsTbl> findStudentsTblEntities(int maxResults, int firstResult) {
        return findStudentsTblEntities(false, maxResults, firstResult);
    }

    private List<StudentsTbl> findStudentsTblEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentsTbl.class));
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

    public StudentsTbl findStudentsTbl(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentsTbl.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentsTblCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentsTbl> rt = cq.from(StudentsTbl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
