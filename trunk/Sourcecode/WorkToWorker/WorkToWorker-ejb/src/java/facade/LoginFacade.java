/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.LoginFacadeLocal;
import entity.Login;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Son
 */
@Stateless
public class LoginFacade extends AbstractFacade<Login> implements LoginFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoginFacade() {
        super(Login.class);
    }
    
}
