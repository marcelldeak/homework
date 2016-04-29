package xyz.codingmentor.theme.park.service;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.entity.GuestBook;
import xyz.codingmentor.theme.park.entity.Machine;
import xyz.codingmentor.theme.park.dto.State;
import xyz.codingmentor.theme.park.entity.ThemePark;
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

    public List<Visitor> getAllVisitor() {
        return visitorFacade.getAllVisitor();
    }

    @Validate
    public Visitor addVisitor(Visitor visitor) {
        visitorFacade.create(visitor);
        return visitorFacade.read(Visitor.class, visitor);
    }

    public Visitor getVisitorById(String id) {
        return visitorFacade.getVisitorById(Long.parseLong(id));
    }

    @Validate
    public Visitor editVisitor(Visitor visitor) {
        return visitorFacade.update(visitor);
    }

    public Visitor deleteVisitor(Visitor visitor) {
        return visitorFacade.delete(visitor);
    }

    public String enterThemePark(String visitorId, String themeParkId) {
        ThemePark themePark = themeParkService.getThemeParkById(themeParkId);
        Visitor visitor = getVisitorById(visitorId);

        if (visitor.getPocketMoney() < themePark.getTicketPrice()) {
            throw new NotEnoughMoneyException(visitorId + " visitor doesn't have enough money to enter " + themePark.getName());
        }

        visitor.setActualPark(themePark);
        visitor.setPocketMoney(visitor.getPocketMoney() - themePark.getTicketPrice());
        visitor.setTimeOfEnter(Calendar.getInstance().getTime());
        visitor.setActivity(true);

        themePark.setStock(themePark.getStock() + themePark.getTicketPrice());

        editVisitor(visitor);
        themeParkService.editThemePark(themePark);

        return visitorId + " visitor entered " + themePark.getName();

    }

    public String leaveThemePark(String id) {
        Visitor visitor = getVisitorById(id);

        if (null == visitor.getActualPark()) {
            throw new VisitorIsNotInAnyThemeParkException("Visitor isn't in any theme park.");
        }
        visitor.setActualPark(null);
        visitor.setTimeOfEnter(null);
        visitor.setActivity(false);
        editVisitor(visitor);

        return id + " visitor left theme park.";
    }

    public String getOnMachine(String visitorId, String machineId) {
        Visitor visitor = getVisitorById(visitorId);
        Machine machine = machineService.getMachineById(machineId);
        ThemePark themePark = themeParkService.getThemeParkById(visitor.getActualPark().getId().toString());

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
        
        themeParkService.editThemePark(themePark);
        machineService.editMachine(machine);
        editVisitor(visitor);
        
        return visitorId + " visitor got on " + machine.getName();
    }

    public String getOff(String visitorId, String machineId) {
        Visitor visitor = visitorFacade.getVisitorById(Long.parseLong(visitorId));
        Machine machine = machineService.getMachineById(machineId);
        
        machine.getVisitorsRiding().remove(visitor);
        visitor.setState(State.REST);
        machineService.editMachine(machine);
        editVisitor(visitor);
        return visitorId + " visitor get off from " + machine.getName();
    }
    
    public GuestBook writeToGuestBook(String visitorId,String guestBookEntry){
        if(null == getVisitorById(visitorId).getActualPark()){
            throw new VisitorIsNotInAnyThemeParkException(visitorId + " visitor isn't in any theme park.");
        }
        
        GuestBook guestBook = new GuestBook();
        
        guestBook.setVisitor(getVisitorById(visitorId));
        guestBook.setDateOfEntry(Calendar.getInstance().getTime());
        guestBook.setThemePark(getVisitorById(visitorId).getActualPark());
        guestBook.setEntryText(guestBookEntry);
        
        return guestBookservice.addGuestBook(guestBook);
    }
}
