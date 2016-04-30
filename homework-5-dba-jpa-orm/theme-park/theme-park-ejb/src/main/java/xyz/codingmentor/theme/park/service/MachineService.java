
package xyz.codingmentor.theme.park.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.dto.MachineDTO;
import xyz.codingmentor.theme.park.entity.Machine;
import xyz.codingmentor.theme.park.facade.MachineFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class MachineService {
    
    @Inject
    private MachineFacade machineFacade;
    
    public List<MachineDTO> getAllMachine(){
        List<MachineDTO> result = new ArrayList<>();
        for(Machine machine : machineFacade.getAllMachine()){
            result.add(new MachineDTO(machine));
        }
        return result;
    }
    
    @Validate
    public MachineDTO addMachine(MachineDTO machine){
        return new MachineDTO(machineFacade.create(new Machine(machine)));
    }
    
    public MachineDTO getMachineById(String id){
        return new MachineDTO(machineFacade.getMachineById(Long.parseLong(id)));
    }
    
    @Validate
    public MachineDTO editMachine(String id,MachineDTO machine){
        Machine machineEntity = machineFacade.getMachineById(Long.parseLong(id));
        
        machineEntity.setCost(machine.getCost());
        machineEntity.setName(machine.getName());
        machineEntity.setNumberOfSeats(machine.getNumberOfSeats());
        machineEntity.setRequiredAge(machine.getRequiredAge());
        machineEntity.setSize(machine.getSize());
        machineEntity.setTicketPrice(machine.getTicketPrice());
        machineEntity.setType(machine.getType());
        
        return new MachineDTO(machineFacade.update(machineEntity));
    }
    
    public MachineDTO deleteMachine(String id){
        return new MachineDTO(machineFacade.delete(machineFacade.getMachineById(Long.parseLong(id))));
    }
}
