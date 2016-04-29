package xyz.codingmentor.theme.park.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.entity.Machine;
import xyz.codingmentor.theme.park.facade.MachineFacade;
import xyz.codingmentor.theme.park.entity.ThemePark;
import xyz.codingmentor.theme.park.exception.DoesNotHaveMachineException;
import xyz.codingmentor.theme.park.exception.NotEnoughMoneyException;
import xyz.codingmentor.theme.park.exception.NotEnoughSpaceException;
import xyz.codingmentor.theme.park.facade.ThemeParkFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class ThemeParkService {

    @Inject
    private ThemeParkFacade themeParkFacade;

    @Inject
    private MachineFacade machineFacade;

    public List<ThemePark> getAllThemePark() {
        return themeParkFacade.getAllThemePark();
    }

    @Validate
    public ThemePark addThemePark(ThemePark themePark) {
        return themeParkFacade.create(themePark);
    }

    public ThemePark getThemeParkById(String id) {
        return themeParkFacade.getThemeParkById(Long.parseLong(id));
    }

    @Validate
    public ThemePark editThemePark(ThemePark themePark) {
        return themeParkFacade.update(themePark);
    }

    public ThemePark deleteThemePark(ThemePark themePark) {
        return themeParkFacade.delete(themePark);
    }

    public String buyMachine(String machineId, String themeParkId) {
        Machine machine = machineFacade.getMachineById(Long.parseLong(machineId));
        ThemePark themePark = themeParkFacade.getThemeParkById(Long.parseLong(themeParkId));
        
        if (themePark.getAreaSize() >= machine.getSize()) {
            if (themePark.getStock() >= machine.getCost()) {
                themePark.addMachine(machine);
                themePark.setAreaSize(themePark.getAreaSize() - machine.getSize());
                themePark.setStock(themePark.getStock() - machine.getCost());
                themeParkFacade.update(themePark);
                return themePark.getName() + " bought " + machine.getName();
            } else {
                throw new NotEnoughMoneyException(themePark.getName() + " don't have enough money to buy " + machine.getName());
            }
        } else {
            throw new NotEnoughSpaceException(themePark.getName() + " don't have enough space for " + machine.getName());
        }
    }

    public String sellMachine(String machineId, String themeParkId) {
        ThemePark themePark = getThemeParkById(themeParkId);
        
        for(Machine machine : themePark.getOwnedMachines()){
            if(machine.getId() == Long.parseLong(machineId)){
                themePark.getOwnedMachines().remove(machine);
                themePark.setAreaSize(themePark.getAreaSize() + machine.getSize());
                themePark.setStock(themePark.getStock() + machine.getCost());
                themeParkFacade.update(themePark);
                return themePark.getName() + " sold " + machine.getName();
            }
        }
        throw new DoesNotHaveMachineException(themePark.getName() + " doesn't have " + machineFacade.getMachineById(Long.parseLong(machineId)));
    }

}
