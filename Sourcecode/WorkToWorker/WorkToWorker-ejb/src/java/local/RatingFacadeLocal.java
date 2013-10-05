/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.Rating;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thanh-Shark
 */
@Local
public interface RatingFacadeLocal {

    void create(Rating rating);

    void edit(Rating rating);

    void remove(Rating rating);

    Rating find(Object id);

    List<Rating> findAll();

    List<Rating> findRange(int[] range);

    int count();
    
}
