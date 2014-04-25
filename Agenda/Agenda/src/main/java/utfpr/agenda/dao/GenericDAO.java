/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.agenda.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Samsung
 */
public class GenericDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected static EntityManagerFactory emf = null;
    
    protected EntityManager em;
    

    public EntityManagerFactory getEMFactory() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("agendaPU");
        return emf;
    }

    public EntityManager getEntityManager() {
        return getEMFactory().createEntityManager();
    }

    public void inserir(Object objeto) {
        em = this.getEntityManager();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        em.close();
    }

    public void remover(Class classe, long codigo) {
        em = this.getEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(classe, codigo));
        em.getTransaction().commit();
        em.close();
    }

    public void alterar(Object objeto) {
        em = this.getEntityManager();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        em.close();
    }

    public Object pesquisar(Class classe, long codigo) {
        try {
            em = this.getEntityManager();
            return em.find(classe, codigo);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
