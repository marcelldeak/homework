package xyz.codingmentor.theme.park.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.dto.GuestBookDTO;
import xyz.codingmentor.theme.park.dto.MachineDTO;
import xyz.codingmentor.theme.park.dto.State;
import xyz.codingmentor.theme.park.dto.ThemeParkDTO;
import xyz.codingmentor.theme.park.dto.VisitorDTO;
import xyz.codingmentor.theme.park.exception.NotEnoughMoneyException;
import xyz.codingmentor.theme.park.entity.Visitor;
import xyz.codingmentor.theme.park.exception.DoesNotHaveMachineException;
import xyz.codingmentor.theme.park.exception.NotEnoughSpaceException;
import xyz.codingmentor.theme.park.exception.VisitorIsNotInAnyThemeParkException;
import xyz.codingmentor.theme.park.exception.VisitorTooYoungException;
import xyz.codingmentor.theme.park.facade.VisitorFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class VisitorService {

    @Inject
    private VisitorFacade visitorFacade;

    @Inject
    private ThemeParkService themeParkService;

    @Inject
    private MachineService machineService;
    
    @Inject
    private GuestBookService guestBookservice;

    public List<VisitorDTO> getAllVisitor() {
        List<VisitorDTO> result = new ArrayList<>();
        for(Visitor visitor : visitorFacade.getAllVisitor()){
            result.add(new VisitorDTO(visitor));
        }
        return result;
    }

    @Validate
    public VisitorDTO addVisitor(VisitorDTO visitor) {
        
        return new VisitorDTO(visitorFacade.create(new Visitor(visitor)));
    }

    public VisitorDTO getVisitorById(String id) {
        return new VisitorDTO(visitorFacade.getVisitorById(Long.parseLong(id)));
    }

    @Validate
    public VisitorDTO editVisitor(String id,VisitorDTO visitor) {
        Visitor visitorEntity = visitorFacade.getVisitorById(Long.parseLong(id));
        
        visitorEntity.setActivity(visitor.getActivity());
        visitorEntity.setAge(visitor.getAge());
        visitorEntity.setPocketMoney(visitor.getPocketMoney());
        visitorEntity.setState(visitor.getState());
        visitorEntity.setTimeOfEnter(visitor.getTimeOfEnter());
        
        return new VisitorDTO(visitorFacade.update(visitorEntity));
    }

    public VisitorDTO deleteVisitor(String id) {
        return new VisitorDTO(visitorFacade.delete(visitorFacade.getVisitorById(Long.parseLong(id))));
    }

    public String enterThemePark(String visitorId, String themeParkId) {
        ThemeParkDTO themePark = themeParkService.getThemeParkById(themeParkId);
        VisitorDTO visitor = getVisitorById(visitorId);

        if (visitor.getPocketMoney() < themePark.getTicketPrice()) {
            throw new NotEnoughMoneyException(visitorId + " visitor doesn't have enough money to enter " + themePark.getName());
        }

        visitor.setActualPark(themePark);
        visitor.setPocketMoney(visitor.getPocketMoney() - themePark.getTicketPrice());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        visitor.setTimeOfEnter(calendar.getTime());
        visitor.setActivity(true);

        themePark.setStock(themePark.getStock() + themePark.getTicketPrice());
        themePark.addVisitor(visitor);

        editVisitor(visitorId,visitor);
        themeParkService.editThemePark(themeParkId,themePark);

        return visitorId + " visitor entered " + themePark.getName();

    }

    public String leaveThemePark(String id) {
        VisitorDTO visitor = getVisitorById(id);
        ThemeParkDTO themePark = visitor.getActualPark();
        
        if (null == themePark) {
            throw new VisitorIsNotInAnyThemeParkException("Visitor isn't in any theme park.");
        }
        
        themePark.removeVisitor(visitor);
        themeParkService.editThemePark(themePark.getId().toString(),themePark);
        
        visitor.setActualPark(null);
        visitor.setTimeOfEnter(null);
        visitor.setActivity(false);
        
        editVisitor(id,visitor);

        return id + " visitor left theme park.";
    }

    public String getOnMachine(String visitorId, String machineId) {
        VisitorDTO visitor = getVisitorById(visitorId);
        MachineDTO machine = machineService.getMachineById(machineId);
        ThemeParkDTO themePark = themeParkService.getThemeParkById(visitor.getActualPark().getId().toString());

        if (!themePark.getOwnedMachines().contains(machine)) {
            throw new DoesNotHaveMachineException("Theme park doesn't have " + machine.getName());
        }
        if (visitor.getAge() < machine.getRequiredAge()) {
            throw new VisitorTooYoungException("Visitor is too young to ride " + machine.getName());
        }
        if (visitor.getPocketMoney() < machine.getTicketPrice()) {
            throw new NotEnoughMoneyException("Visitor doesn't have engough money to ride " + machine.getName());
        }
        if (machine.getNumberOfSeats() < machine.getVisitorsRiding().size()) {
            throw new NotEnoughSpaceException(machine.getName() + " doesn't have enough seats.");
        }

        machine.getVisitorsRiding().add(visitor);
        themePark.setStock(themePark.getStock() + machine.getTicketPrice());
        visitor.setPocketMoney(visitor.getPocketMoney() - machine.getTicketPrice());
        visitor.setState(State.ON_MACHINE);
        
        themeParkService.editThemePark(themePark.getId().toString(),themePark);
        machineService.editMachine(machineId,machine);
        editVisitor(visitorId,visitor);
        
        return visitorId + " visitor got on " + machine.getName();
    }

    public String getOff(String visitorId, String machineId) {
        VisitorDTO visitor = getVisitorById(visitorId);
        MachineDTO machine = machineService.getMachineById(machineId);
        
        machine.getVisitorsRiding().remove(visitor);
        visitor.setState(State.REST);
        machineService.editMachine(machineId,machine);
        editVisitor(visitorId,visitor);
        return visitorId + " visitor get off from " + machine.getName();
    }
    
    public GuestBookDTO writeToGuestBook(String visitorId,String guestBookEntry){
        if(null == getVisitorById(visitorId).getActualPark()){
            throw new VisitorIsNotInAnyThemeParkException(visitorId + " visitor isn't in any theme park.");
        }
        
        GuestBookDTO guestBook = new GuestBookDTO();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        guestBook.setVisitor(getVisitorById(visitorId));
        guestBook.setDateOfEntry(calendar.getTime());
        guestBook.setThemePark(getVisitorById(visitorId).getActualPark());
        guestBook.setEntryText(guestBookEntry);
        
        return guestBookservice.addGuestBook(guestBook);
    }
}
