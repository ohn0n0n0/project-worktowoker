/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.GroupDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Son
 */
@Local
public interface GroupDetailsFacadeLocal {

    void create(GroupDetails groupDetails);

    void edit(GroupDetails groupDetails);

    void remove(GroupDetails groupDetails);

    GroupDetails find(Object id);

    List<GroupDetails> findAll();

    List<GroupDetails> findRange(int[] range);

    int count();
    
}
