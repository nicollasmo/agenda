/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utfpr.agenda.dao;

import java.util.List;
import javax.persistence.Query;
import utfpr.agenda.entity.Contato;

/**
 *
 * @author Samsung
 */
public class ContatoDAO extends GenericDAO{
    
    public List<Contato> listar(){
        try {
            em = getEntityManager();
            Query q = em.createNamedQuery("Contato.findAll");
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


}
