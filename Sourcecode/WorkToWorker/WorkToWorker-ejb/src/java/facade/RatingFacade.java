/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.RatingFacadeLocal;
import entity.Rating;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class RatingFacade extends AbstractFacade<Rating> implements RatingFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingFacade() {
        super(Rating.class);
    }
    
}
