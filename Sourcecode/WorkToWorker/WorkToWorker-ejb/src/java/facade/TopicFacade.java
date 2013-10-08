/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.TopicFacadeLocal;
import entity.Topic;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class TopicFacade extends AbstractFacade<Topic> implements TopicFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TopicFacade() {
        super(Topic.class);
    }
    
}
