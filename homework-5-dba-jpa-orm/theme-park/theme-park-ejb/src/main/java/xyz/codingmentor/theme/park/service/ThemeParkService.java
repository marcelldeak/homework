package xyz.codingmentor.theme.park.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.dto.MachineDTO;
import xyz.codingmentor.theme.park.dto.ThemeParkDTO;
import xyz.codingmentor.theme.park.entity.ThemePark;
import xyz.codingmentor.theme.park.exception.DoesNotHaveMachineException;
import xyz.codingmentor.theme.park.exception.MachineUsedException;
import xyz.codingmentor.theme.park.exception.NotEnoughMoneyException;
import xyz.codingmentor.theme.park.exception.NotEnoughSpaceException;
import xyz.codingmentor.theme.park.facade.ThemeParkFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class ThemeParkService {

    @Inject
    private ThemeParkFacade themeParkFacade;

    @Inject
    private MachineService machineService;

    public List<ThemeParkDTO> getAllThemePark() {
        List<ThemeParkDTO> result = new ArrayList<>();
        for (ThemePark themePark : themeParkFacade.getAllThemePark()) {
            result.add(new ThemeParkDTO(themePark));
        }
        return result;
    }

    @Validate
    public ThemeParkDTO addThemePark(ThemeParkDTO themePark) {
        return new ThemeParkDTO(themeParkFacade.create(new ThemePark(themePark)));
    }

    public ThemeParkDTO getThemeParkById(String id) {
        return new ThemeParkDTO(themeParkFacade.getThemeParkById(Long.parseLong(id)));
    }

    @Validate
    public ThemeParkDTO editThemePark(String id, ThemeParkDTO themePark) {
        ThemePark themeParkEntity = themeParkFacade.getThemeParkById(Long.parseLong(id));

        themeParkEntity.setAddress(themePark.getAddress());
        themeParkEntity.setAreaSize(themePark.getAreaSize());
        themeParkEntity.setName(themePark.getName());
        themeParkEntity.setStock(themePark.getStock());
        themeParkEntity.setTicketPrice(themePark.getTicketPrice());

        return new ThemeParkDTO(themeParkFacade.update(themeParkEntity));
    }

    public ThemeParkDTO deleteThemePark(String id) {
        return new ThemeParkDTO(themeParkFacade.delete(themeParkFacade.getThemeParkById(Long.parseLong(id))));
    }

    public String buyMachine(String machineId, String themeParkId) {
        MachineDTO machine = machineService.getMachineById(machineId);
        ThemeParkDTO themePark = getThemeParkById(themeParkId);

        if (themePark.getAreaSize() >= machine.getSize()) {
            if (themePark.getStock() >= machine.getCost()) {

                themePark.setAreaSize(themePark.getAreaSize() - machine.getSize());
                themePark.setStock(themePark.getStock() - machine.getCost());
                themePark.addMachine(machine);

                themeParkFacade.update(new ThemePark(themePark));
                return themePark.getName() + " bought " + machine.getName();
            } else {
                throw new NotEnoughMoneyException(themePark.getName() + " don't have enough money to buy " + machine.getName());
            }
        } else {
            throw new NotEnoughSpaceException(themePark.getName() + " don't have enough space for " + machine.getName());
        }
    }

    public String sellMachine(String machineId, String themeParkId) {
        ThemeParkDTO themePark = getThemeParkById(themeParkId);

        for (MachineDTO machine : themePark.getOwnedMachines()) {
            if (machine.getId() == Long.parseLong(machineId)) {
                if (machine.getVisitorsRiding().isEmpty()) {
                    themePark.removeMachine(machine);
                    themePark.setAreaSize(themePark.getAreaSize() + machine.getSize());
                    themePark.setStock(themePark.getStock() + machine.getCost());
                    themeParkFacade.update(themePark);
                    return themePark.getName() + " sold " + machine.getName();
                }
                else{
                    throw new MachineUsedException(machine.getName() + " is in use.");
                }
            }
        }
        throw new DoesNotHaveMachineException(themePark.getName() + " doesn't have " + machineService.getMachineById(machineId));
    }

}
