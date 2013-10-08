/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Attach;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Son
 */
@Local
public interface AttachFacadeLocal {

    void create(Attach attach);

    void edit(Attach attach);

    void remove(Attach attach);

    Attach find(Object id);

    List<Attach> findAll();

    List<Attach> findRange(int[] range);

    int count();
    
}
