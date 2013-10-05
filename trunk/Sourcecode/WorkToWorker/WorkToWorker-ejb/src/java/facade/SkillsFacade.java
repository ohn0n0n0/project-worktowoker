/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.SkillsFacadeLocal;
import entity.Skills;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thanh-Shark
 */
@Stateless
public class SkillsFacade extends AbstractFacade<Skills> implements SkillsFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SkillsFacade() {
        super(Skills.class);
    }
    
}
