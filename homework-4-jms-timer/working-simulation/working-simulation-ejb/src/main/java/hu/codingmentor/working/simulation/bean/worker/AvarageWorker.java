package hu.codingmentor.working.simulation.bean.worker;

import hu.codingmentor.dto.Job;
import hu.codingmentor.dto.Statistic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven(mappedName = "dzsobKju")
public class AvarageWorker implements MessageListener {

    @Inject
    private Logger logger;
    
    @Inject
    private JMSContext jmsContext;
    
    @Resource(lookup = "dzsobTopik")
    private Destination statisticsTopic;

    public AvarageWorker() {
        // default constructor
    }

    @Override
    public void onMessage(Message message) {
        JMSProducer producer = jmsContext.createProducer();
        Job job = new Job();
        Statistic jobStatistic = new Statistic();
        try {
            job = message.getBody(Job.class);
            Long startTime = System.currentTimeMillis();
            Thread.sleep(job.getDuration() * 1000);
            Long workDuration = (System.currentTimeMillis() - startTime)/1000;
             jobStatistic = jobStatistic.jobId(job.getId())
                                        .timeStamp(workDuration);
        } catch (JMSException | InterruptedException ex) {
            logger.log(Level.SEVERE, ex.toString());
        }
        producer.send(statisticsTopic, jmsContext.createObjectMessage(jobStatistic));
        logger.info("AvarageWorker do " + job + " and send " + jobStatistic);
    }

}
