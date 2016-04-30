package xyz.codingmentor.theme.park.entity;

import xyz.codingmentor.theme.park.dto.State;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import xyz.codingmentor.theme.park.dto.VisitorDTO;

@Entity
public class Visitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "visitor_state")
    private State state = State.REST;

    @Column(name = "pocket_money")
    private Integer pocketMoney;

    @Temporal(TemporalType.DATE)
    @Column(name = "time_of_enter")
    private Date timeOfEnter;

    private Integer age;

    private Boolean activity = false;

    @ManyToOne
    @JoinTable(name = "theme_park_visitor",
            joinColumns = @JoinColumn(name = "theme_park_fk"),
            inverseJoinColumns = @JoinColumn(name = "visitor_fk"))
    private ThemePark actualPark;
    
    @ManyToOne
    @JoinTable(name = "machine_visitor",
            joinColumns = @JoinColumn(name = "machine_fk"),
            inverseJoinColumns = @JoinColumn(name = "visitor_fk"))
    private Machine machine;


    public Visitor() {
        // default constructor
    }

    public Visitor(Long id, Integer pocketMoney, Date timeOfEnter, Integer age, ThemePark actualPark, Machine machine) {
        this.id = id;
        this.pocketMoney = pocketMoney;
        this.timeOfEnter = timeOfEnter;
        this.age = age;
        this.actualPark = actualPark;
        this.machine = machine;
    }
    
    public Visitor(VisitorDTO visitor) {
        this.id = visitor.getId();
        this.state = visitor.getState();
        this.pocketMoney = visitor.getPocketMoney();
        this.timeOfEnter = visitor.getTimeOfEnter();
        this.age = visitor.getAge();
        this.activity = visitor.getActivity();
        if(visitor.getActualPark() == null){
            this.actualPark = null;
        }else{
            this.actualPark = new ThemePark(visitor.getActualPark());
        }
        if(visitor.getMachine() == null){
            this.machine = null;
        }else{
            this.machine = new Machine(visitor.getMachine());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getPocketMoney() {
        return pocketMoney;
    }

    public void setPocketMoney(Integer pocketMoney) {
        this.pocketMoney = pocketMoney;
    }

    public Date getTimeOfEnter() {
        return timeOfEnter;
    }

    public void setTimeOfEnter(Date timeOfEnter) {
        this.timeOfEnter = timeOfEnter;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getActivity() {
        return activity;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    public ThemePark getActualPark() {
        return actualPark;
    }

    public void setActualPark(ThemePark actualPark) {
        this.actualPark = actualPark;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    @Override
    public String toString() {
        return "Visitor{" + "id=" + id + ", state=" + state + ", pocketMoney=" + pocketMoney + ", timeOfEnter=" + timeOfEnter + ", age=" + age + ", activity=" + activity + ", actualPark=" + actualPark + '}';
    }

}
