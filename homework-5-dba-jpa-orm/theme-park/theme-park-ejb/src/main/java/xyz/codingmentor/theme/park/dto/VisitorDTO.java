
package xyz.codingmentor.theme.park.dto;

import java.util.Date;
import javax.validation.constraints.Min;
import xyz.codingmentor.theme.park.entity.Visitor;


public class VisitorDTO {
    
    private Long id;

    private State state;

    @Min(0)
    private Integer pocketMoney;

    private Date timeOfEnter;

    @Min(1)
    private Integer age;

    private Boolean activity;

    private ThemeParkDTO actualPark;

    private MachineDTO machine;

    public VisitorDTO() {
        // default constructor
    }

    public VisitorDTO(Long id, State state, Integer pocketMoney, Date timeOfEnter, Integer age, Boolean activity, ThemeParkDTO actualPark, MachineDTO machine) {
        this.id = id;
        this.state = state;
        this.pocketMoney = pocketMoney;
        this.timeOfEnter = timeOfEnter;
        this.age = age;
        this.activity = activity;
        this.actualPark = actualPark;
        this.machine = machine;
    }

    public VisitorDTO(Visitor visitor) {
        this.id = visitor.getId();
        this.state = visitor.getState();
        this.pocketMoney = visitor.getPocketMoney();
        this.timeOfEnter = visitor.getTimeOfEnter();
        this.age = visitor.getAge();
        this.activity = visitor.getActivity();
        if(visitor.getActualPark() == null){
            this.actualPark = null;
        }else{
            this.actualPark = new ThemeParkDTO(visitor.getActualPark());
        }
        if(visitor.getMachine() == null){
            this.machine = null;
        }else{
            this.machine = new MachineDTO(visitor.getMachine());
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

    public ThemeParkDTO getActualPark() {
        return actualPark;
    }

    public void setActualPark(ThemeParkDTO actualPark) {
        this.actualPark = actualPark;
    }

    public MachineDTO getMachine() {
        return machine;
    }

    public void setMachine(MachineDTO machine) {
        this.machine = machine;
    }
    
    @Override
    public String toString() {
        return "VisitorDTO{" + "id=" + id + ", state=" + state + ", pocketMoney=" + pocketMoney + ", timeOfEnter=" + timeOfEnter + ", age=" + age + ", activity=" + activity + ", actualPark=" + actualPark + '}';
    }
}
