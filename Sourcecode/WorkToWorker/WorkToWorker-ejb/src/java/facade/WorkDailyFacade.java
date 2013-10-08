/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.WorkDailyFacadeLocal;
import entity.WorkDaily;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class WorkDailyFacade extends AbstractFacade<WorkDaily> implements WorkDailyFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkDailyFacade() {
        super(WorkDaily.class);
    }
    
}
