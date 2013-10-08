/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Information;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Son
 */
@Local
public interface InformationFacadeLocal {

    void create(Information information);

    void edit(Information information);

    void remove(Information information);

    Information find(Object id);

    List<Information> findAll();

    List<Information> findRange(int[] range);

    int count();
    
}
