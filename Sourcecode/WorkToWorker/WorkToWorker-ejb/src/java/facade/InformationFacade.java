/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.InformationFacadeLocal;
import entity.Information;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class InformationFacade extends AbstractFacade<Information> implements InformationFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformationFacade() {
        super(Information.class);
    }
    
}
