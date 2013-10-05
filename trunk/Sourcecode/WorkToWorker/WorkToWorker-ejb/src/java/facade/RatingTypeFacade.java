/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.RatingTypeFacadeLocal;
import entity.RatingType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thanh-Shark
 */
@Stateless
public class RatingTypeFacade extends AbstractFacade<RatingType> implements RatingTypeFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingTypeFacade() {
        super(RatingType.class);
    }
    
}
