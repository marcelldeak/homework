package hu.codingmentor.working.simulation.bean.worker;

import hu.codingmentor.working.simulation.dto.Job;
import hu.codingmentor.working.simulation.dto.Statistic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "dzsobKju", activationConfig = {
    @ActivationConfigProperty(propertyName = "maxSession", propertyValue = "1"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/dzsobKju")
}
)
public class FastWorker implements MessageListener {

    @Inject
    private Logger logger;

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:/dzsobTopik")
    private Destination statisticsTopic;

    public FastWorker() {
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
            Thread.sleep(job.getDuration() * 500);
            Long workDuration = (System.currentTimeMillis() - startTime) / 1000;
            jobStatistic = jobStatistic.jobId(job.getId())
                    .timeStamp(workDuration);
        } catch (JMSException | InterruptedException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        producer.send(statisticsTopic, jmsContext.createObjectMessage(jobStatistic));
        logger.info("FastWorker do " + job + " and send " + jobStatistic);
    }

}
