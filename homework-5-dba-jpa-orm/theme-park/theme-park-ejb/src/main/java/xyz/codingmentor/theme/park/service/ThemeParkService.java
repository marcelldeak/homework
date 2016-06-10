package xyz.codingmentor.theme.park.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.dto.GuestBookDTO;
import xyz.codingmentor.theme.park.dto.ThemeParkDTO;
import xyz.codingmentor.theme.park.entity.GuestBook;
import xyz.codingmentor.theme.park.entity.Machine;
import xyz.codingmentor.theme.park.entity.ThemePark;
import xyz.codingmentor.theme.park.entity.Visitor;
import xyz.codingmentor.theme.park.exception.DoesNotHaveMachineException;
import xyz.codingmentor.theme.park.exception.MachineUsedException;
import xyz.codingmentor.theme.park.exception.NotEnoughMoneyException;
import xyz.codingmentor.theme.park.exception.NotEnoughSpaceException;
import xyz.codingmentor.theme.park.facade.MachineFacade;
import xyz.codingmentor.theme.park.facade.ThemeParkFacade;
import xyz.codingmentor.theme.park.facade.VisitorFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class ThemeParkService {

    @Inject
    private ThemeParkFacade themeParkFacade;

    @Inject
    private MachineFacade machineFacade;

    @Inject
    private VisitorFacade visitorFacade;

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
        for (Visitor visitor : visitorFacade.getVisitorsFromThemePark(themePark.getId())) {
            themeParkEntity.getVisitors().add(visitor);
        }
        for (Machine machine : machineFacade.getMachinesFromThemePark(themePark.getId())) {
            themeParkEntity.getOwnedMachines().add(machine);
        }

        return new ThemeParkDTO(themeParkFacade.update(themeParkEntity));
    }

    public ThemeParkDTO deleteThemePark(String id) {
        return new ThemeParkDTO(themeParkFacade.delete(themeParkFacade.getThemeParkById(Long.parseLong(id))));
    }

    public String buyMachine(String machineId, String themeParkId) {
        Machine machine = machineFacade.getMachineById(Long.parseLong(machineId));
        ThemePark themePark = themeParkFacade.getThemeParkById(Long.parseLong(themeParkId));

        if (themePark.getAreaSize() >= machine.getSize()) {
            if (themePark.getStock() >= machine.getCost()) {

                themePark.setAreaSize(themePark.getAreaSize() - machine.getSize());
                themePark.setStock(themePark.getStock() - machine.getCost());
                themePark.addMachine(machine);

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
        ThemePark themePark = themeParkFacade.getThemeParkById(Long.parseLong(themeParkId));

        for (Machine machine : machineFacade.getMachinesFromThemePark(Long.parseLong(themeParkId))) {
            if (machine.getId() == Long.parseLong(machineId)) {
                if (visitorFacade.getVisitorsRidingMachine(Long.parseLong(machineId)).isEmpty()) {
                    themePark.removeMachine(machine);
                    themePark.setAreaSize(themePark.getAreaSize() + machine.getSize());
                    themePark.setStock(themePark.getStock() + machine.getCost());
                    themeParkFacade.update(themePark);
                    return themePark.getName() + " sold " + machine.getName();
                } else {
                    throw new MachineUsedException(machine.getName() + " is in use.");
                }
            }
        }
        throw new DoesNotHaveMachineException(themePark.getName() + " doesn't have " + machineFacade.getMachineById(Long.parseLong(machineId)));
    }

    public List<GuestBookDTO> getGuestBooksOfThemePark(String id) {
        List<GuestBookDTO> result = new ArrayList<>();
        for (GuestBook guestbook : themeParkFacade.getGuestBooksOfThemePark(Long.parseLong(id))) {
            result.add(new GuestBookDTO(guestbook));
        }
        return result;
    }

    public List<ThemeParkDTO> getThemeParkFromCountry(String country) {
        List<ThemeParkDTO> result = new ArrayList<>();
        for (ThemePark themepark : themeParkFacade.getThemeParkFromCountry(country)) {
            result.add(new ThemeParkDTO(themepark));
        }
        return result;
    }
}
