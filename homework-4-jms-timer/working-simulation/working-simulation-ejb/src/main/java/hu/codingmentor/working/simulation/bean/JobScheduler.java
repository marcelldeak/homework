package hu.codingmentor.working.simulation.bean;

import hu.codingmentor.working.simulation.dto.Job;
import hu.codingmentor.working.simulation.dto.Statistic;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;

@Stateless
public class JobScheduler {
    
    @Inject
    private Logger logger;

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = "java:/dzsobKju")
    private Destination jobQueue;

    @Resource(lookup = "java:/dzsobTopik")
    private Destination statisticsTopic;

    public JobScheduler() {
        // default constructor
    }

    @Schedule(hour = "*", minute = "*", second = "0")
    public void createJobs() {
        JMSProducer producer = jmsContext.createProducer();
        for (int i = 1; i <= 10; i++) {
            Job job = new Job(ThreadLocalRandom.current().nextLong(4) + 1);
            Statistic jobStatistic = new Statistic().jobId(job.getId()).timeStamp(0L);
            
            producer.send(jobQueue, jmsContext.createObjectMessage(job));
            producer.send(statisticsTopic, jmsContext.createObjectMessage(jobStatistic));

            logger.info("JobScheduler sent " + job);
        }
    }

}
