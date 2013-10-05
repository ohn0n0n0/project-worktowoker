/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.RatingType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Thanh-Shark
 */
@Local
public interface RatingTypeFacadeLocal {

    void create(RatingType ratingType);

    void edit(RatingType ratingType);

    void remove(RatingType ratingType);

    RatingType find(Object id);

    List<RatingType> findAll();

    List<RatingType> findRange(int[] range);

    int count();
    
}
