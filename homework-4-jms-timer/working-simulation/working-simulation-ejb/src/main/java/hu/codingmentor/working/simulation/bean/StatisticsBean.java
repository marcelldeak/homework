package hu.codingmentor.working.simulation.bean;

import hu.codingmentor.dto.Statistic;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "dzsobTopik")
public class StatisticsBean implements MessageListener {
    
    private static final Map<Long, Boolean> statistics = new HashMap<>();
    
    private static final Map<Long, Long> statTmp = new HashMap<>();

    @Inject
    private Logger logger;

    @Override
    public void onMessage(Message message) {
        Statistic statistic = new Statistic();
        try {
            statistic = message.getBody(Statistic.class);
        } catch (JMSException ex) {
            logger.log(Level.SEVERE, ex.toString());
        }
        if (statistics.containsKey(statistic.getJobId())) {
            if (statistic.getTimeStamp() <= 5) {
                statistics.replace(statistic.getJobId(), true);
                logger.info(statistic.getJobId() + " job was successfully");
            }
        } else {
            statistics.put(statistic.getJobId(), false);
            statTmp.put(statistic.getJobId(), statistic.getTimeStamp());
        }

        logger.info("StatisticBean get " + statistic + " map:/n" + statistics);
    }

    public static Map<Long, Boolean> getStatistics() {
        return statistics;
    }
}
