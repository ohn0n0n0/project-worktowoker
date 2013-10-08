/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local;

import entity.ReportType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Son
 */
@Local
public interface ReportTypeFacadeLocal {

    void create(ReportType reportType);

    void edit(ReportType reportType);

    void remove(ReportType reportType);

    ReportType find(Object id);

    List<ReportType> findAll();

    List<ReportType> findRange(int[] range);

    int count();
    
}
