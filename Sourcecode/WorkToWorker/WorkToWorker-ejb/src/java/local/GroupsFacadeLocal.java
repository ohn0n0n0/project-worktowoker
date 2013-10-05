/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Groups;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thanh-Shark
 */
@Local
public interface GroupsFacadeLocal {

    void create(Groups groups);

    void edit(Groups groups);

    void remove(Groups groups);

    Groups find(Object id);

    List<Groups> findAll();

    List<Groups> findRange(int[] range);

    int count();
    
}
