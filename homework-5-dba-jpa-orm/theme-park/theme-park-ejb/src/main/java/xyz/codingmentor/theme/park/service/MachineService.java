
package xyz.codingmentor.theme.park.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.entity.Machine;
import xyz.codingmentor.theme.park.facade.MachineFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class MachineService {
    
    @Inject
    private MachineFacade machineFacade;
    
    public List<Machine> getAllMachine(){
        return machineFacade.getAllMachine();
    }
    
    @Validate
    public Machine addMachine(Machine machine){
        return machineFacade.create(machine);
    }
    
    public Machine getMachineById(String id){
        return machineFacade.getMachineById(Long.parseLong(id));
    }
    
    @Validate
    public Machine editMachine(Machine machine){
        return machineFacade.update(machine);
    }
    
    public Machine deleteMachine(Machine machine){
        return machineFacade.delete(machine);
    }
}
