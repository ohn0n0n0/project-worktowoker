/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.MediaWork;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Son
 */
@Local
public interface MediaWorkFacadeLocal {

    void create(MediaWork mediaWork);

    void edit(MediaWork mediaWork);

    void remove(MediaWork mediaWork);

    MediaWork find(Object id);

    List<MediaWork> findAll();

    List<MediaWork> findRange(int[] range);

    int count();
    
}
