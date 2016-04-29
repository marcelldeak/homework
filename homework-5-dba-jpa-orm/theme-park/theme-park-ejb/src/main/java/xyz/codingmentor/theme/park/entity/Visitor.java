package xyz.codingmentor.theme.park.entity;

import xyz.codingmentor.theme.park.dto.State;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

@Entity
@NamedQuery(name = "restingVisitors",
        query = "select v from Visitor v where v.state like 'REST'")
public class Visitor implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "visitor_state")
    private State state = State.REST;

    @Min(0)
    @Column(name = "pocket_money")
    private Integer pocketMoney;

    @Temporal(TemporalType.DATE)
    @Column(name = "time_of_enter")
    private Date timeOfEnter;

    @Min(1)
    private Integer age;

    private Boolean activity = false;

    @OneToOne
    @JoinColumn(name = "theme_park_id")
    private ThemePark actualPark;


    public Visitor() {
        // default constructor
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

    @Override
    public String toString() {
        return "Visitor{" + "id=" + id + ", state=" + state + ", pocketMoney=" + pocketMoney + ", timeOfEnter=" + timeOfEnter + ", age=" + age + ", activity=" + activity + ", actualPark=" + actualPark + '}';
    }

}
