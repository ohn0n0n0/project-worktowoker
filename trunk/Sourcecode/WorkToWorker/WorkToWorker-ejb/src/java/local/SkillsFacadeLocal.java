/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Skills;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Son
 */
@Local
public interface SkillsFacadeLocal {

    void create(Skills skills);

    void edit(Skills skills);

    void remove(Skills skills);

    Skills find(Object id);

    List<Skills> findAll();

    List<Skills> findRange(int[] range);

    int count();
    
}
