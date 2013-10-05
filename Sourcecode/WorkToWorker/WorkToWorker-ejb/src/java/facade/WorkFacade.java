/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.WorkFacadeLocal;
import entity.Work;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thanh-Shark
 */
@Stateless
public class WorkFacade extends AbstractFacade<Work> implements WorkFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkFacade() {
        super(Work.class);
    }
    
}
