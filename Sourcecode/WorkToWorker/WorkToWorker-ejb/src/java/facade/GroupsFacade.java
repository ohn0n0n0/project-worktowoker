/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.GroupsFacadeLocal;
import entity.Groups;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class GroupsFacade extends AbstractFacade<Groups> implements GroupsFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupsFacade() {
        super(Groups.class);
    }
    
}
