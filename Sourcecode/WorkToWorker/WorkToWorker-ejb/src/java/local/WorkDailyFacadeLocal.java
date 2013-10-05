/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.WorkDaily;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thanh-Shark
 */
@Local
public interface WorkDailyFacadeLocal {

    void create(WorkDaily workDaily);

    void edit(WorkDaily workDaily);

    void remove(WorkDaily workDaily);

    WorkDaily find(Object id);

    List<WorkDaily> findAll();

    List<WorkDaily> findRange(int[] range);

    int count();
    
}
