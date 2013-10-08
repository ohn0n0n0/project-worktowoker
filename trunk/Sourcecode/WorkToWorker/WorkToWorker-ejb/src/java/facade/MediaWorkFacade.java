/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.MediaWorkFacadeLocal;
import entity.MediaWork;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class MediaWorkFacade extends AbstractFacade<MediaWork> implements MediaWorkFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MediaWorkFacade() {
        super(MediaWork.class);
    }
    
}
