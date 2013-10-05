/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Post;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thanh-Shark
 */
@Local
public interface PostFacadeLocal {

    void create(Post post);

    void edit(Post post);

    void remove(Post post);

    Post find(Object id);

    List<Post> findAll();

    List<Post> findRange(int[] range);

    int count();
    
}
