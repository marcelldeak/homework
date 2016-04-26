
package hu.codingmentor.working.simulation.service;

import hu.codingmentor.working.simulation.bean.StatisticsBean;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class StatisticsService {
    
    private StatisticsBean statisticsBean;
    
    public Map<Long,Boolean> getStatistics(){
        return StatisticsBean.getStatistics();
    }
}
