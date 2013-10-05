/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import local.PostFacadeLocal;
import entity.Post;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Thanh-Shark
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> implements PostFacadeLocal {
    @PersistenceContext(unitName = "WorkToWorker-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }
    
}
