/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.ReportTypeFacadeLocal;
import entity.ReportType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class ReportTypeFacade extends AbstractFacade<ReportType> implements ReportTypeFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportTypeFacade() {
        super(ReportType.class);
    }
    
}
