/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Worker;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Son
 */
@Local
public interface WorkerFacadeLocal {

    void create(Worker worker);

    void edit(Worker worker);

    void remove(Worker worker);

    Worker find(Object id);

    List<Worker> findAll();

    List<Worker> findRange(int[] range);

    int count();
    
}
