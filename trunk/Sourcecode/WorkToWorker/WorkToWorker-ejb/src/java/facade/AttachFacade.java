/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.AttachFacadeLocal;
import entity.Attach;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thanh-Shark
 */
@Stateless
public class AttachFacade extends AbstractFacade<Attach> implements AttachFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttachFacade() {
        super(Attach.class);
    }
    
}
