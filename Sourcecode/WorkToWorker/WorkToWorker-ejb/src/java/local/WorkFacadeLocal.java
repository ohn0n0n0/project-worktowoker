/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Work;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thanh-Shark
 */
@Local
public interface WorkFacadeLocal {

    void create(Work work);

    void edit(Work work);

    void remove(Work work);

    Work find(Object id);

    List<Work> findAll();

    List<Work> findRange(int[] range);

    int count();
    
}
