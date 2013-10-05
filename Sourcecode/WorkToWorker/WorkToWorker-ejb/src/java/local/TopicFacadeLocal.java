/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Topic;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thanh-Shark
 */
@Local
public interface TopicFacadeLocal {

    void create(Topic topic);

    void edit(Topic topic);

    void remove(Topic topic);

    Topic find(Object id);

    List<Topic> findAll();

    List<Topic> findRange(int[] range);

    int count();
    
}
