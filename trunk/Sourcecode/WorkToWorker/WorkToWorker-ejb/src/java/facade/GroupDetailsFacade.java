/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.GroupDetailsFacadeLocal;
import entity.GroupDetails;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thanh-Shark
 */
@Stateless
public class GroupDetailsFacade extends AbstractFacade<GroupDetails> implements GroupDetailsFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupDetailsFacade() {
        super(GroupDetails.class);
    }
    
}
