package hu.codingmentor.working.simulation.dto;

import java.io.Serializable;

public class Job implements Serializable{

    private static Long nextJobId = 0L;
    
    private Long id;

    private Long duration;

    public Job() {
        // default constructor
    }

    public Job(Long duration) {
        this.id = ++nextJobId;
        this.duration = duration;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job id(Long id){
        this.id = id;
        return this;
    }
    
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Job duration(Long duration){
        this.duration = duration;
        return this;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", duration=" + duration + '}';
    }
    
}
