/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.ReportFacadeLocal;
import entity.Report;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class ReportFacade extends AbstractFacade<Report> implements ReportFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportFacade() {
        super(Report.class);
    }
    
}
