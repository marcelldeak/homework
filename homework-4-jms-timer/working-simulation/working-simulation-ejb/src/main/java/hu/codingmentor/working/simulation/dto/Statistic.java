package hu.codingmentor.working.simulation.dto;

import java.io.Serializable;

public class Statistic implements Serializable {
    
    private Long timeStamp;
    
    private Long jobId;

    public Statistic() {
        // default constructor
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Statistic timeStamp(Long timeStamp){
        this.timeStamp = timeStamp;
        return this;
    }
    
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    
    public Statistic jobId(Long jobId){
        this.jobId = jobId;
        return this;
    }

    @Override
    public String toString() {
        return "Statistic{" + "timeStamp=" + timeStamp + ", jobId=" + jobId + '}';
    }
    
}
