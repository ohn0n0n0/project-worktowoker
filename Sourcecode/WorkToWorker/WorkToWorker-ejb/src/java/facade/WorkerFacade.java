/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.WorkerFacadeLocal;
import entity.Worker;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class WorkerFacade extends AbstractFacade<Worker> implements WorkerFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkerFacade() {
        super(Worker.class);
    }
    
}
