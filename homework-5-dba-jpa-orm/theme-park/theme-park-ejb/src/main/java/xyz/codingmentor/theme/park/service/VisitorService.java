package xyz.codingmentor.theme.park.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.dto.GuestBookDTO;
import xyz.codingmentor.theme.park.dto.State;
import xyz.codingmentor.theme.park.dto.ThemeParkDTO;
import xyz.codingmentor.theme.park.dto.VisitorDTO;
import xyz.codingmentor.theme.park.entity.Machine;
import xyz.codingmentor.theme.park.entity.ThemePark;
import xyz.codingmentor.theme.park.exception.NotEnoughMoneyException;
import xyz.codingmentor.theme.park.entity.Visitor;
import xyz.codingmentor.theme.park.exception.DoesNotHaveMachineException;
import xyz.codingmentor.theme.park.exception.NotEnoughSpaceException;
import xyz.codingmentor.theme.park.exception.VisitorIsNotInAnyThemeParkException;
import xyz.codingmentor.theme.park.exception.VisitorTooYoungException;
import xyz.codingmentor.theme.park.facade.MachineFacade;
import xyz.codingmentor.theme.park.facade.ThemeParkFacade;
import xyz.codingmentor.theme.park.facade.VisitorFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class VisitorService {

    @Inject
    private VisitorFacade visitorFacade;

    @Inject
    private ThemeParkFacade themeParkFacade;

    @Inject
    private MachineFacade machineFacade;

    @Inject
    private GuestBookService guestBookservice;

    public List<VisitorDTO> getAllVisitor() {
        List<VisitorDTO> result = new ArrayList<>();
        for (Visitor visitor : visitorFacade.getAllVisitor()) {
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
    public VisitorDTO editVisitor(String id, VisitorDTO visitor) {
        Visitor visitorEntity = visitorFacade.getVisitorById(Long.parseLong(id));

        visitorEntity.setActivity(visitor.getActivity());
        visitorEntity.setAge(visitor.getAge());
        visitorEntity.setPocketMoney(visitor.getPocketMoney());
        visitorEntity.setState(visitor.getState());
        visitorEntity.setTimeOfEnter(visitor.getTimeOfEnter());
        if (null != themeParkFacade.getThemeParkWhereVisitorIs(visitor.getId())) {
            visitorEntity.setActualPark(themeParkFacade.getThemeParkWhereVisitorIs(visitor.getId()));
        }
        if (machineFacade.getMachineVisitorRiding(Long.parseLong(id)) != null) {
            visitorEntity.setMachine(machineFacade.getMachineVisitorRiding(Long.parseLong(id)));
        }

        return new VisitorDTO(visitorFacade.update(visitorEntity));
    }

    public VisitorDTO deleteVisitor(String id) {
        return new VisitorDTO(visitorFacade.delete(visitorFacade.getVisitorById(Long.parseLong(id))));
    }

    public String enterThemePark(String visitorId, String themeParkId) {
        ThemePark themePark = themeParkFacade.getThemeParkById(Long.parseLong(themeParkId));
        Visitor visitor = visitorFacade.getVisitorById(Long.parseLong(visitorId));

        if (visitor.getPocketMoney() < themePark.getTicketPrice()) {
            throw new NotEnoughMoneyException(visitorId + " visitor doesn't have enough money to enter " + themePark.getName());
        }

        visitor.setPocketMoney(visitor.getPocketMoney() - themePark.getTicketPrice());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        visitor.setTimeOfEnter(calendar.getTime());
        visitor.setActivity(true);
        visitor.setActualPark(themePark);

        themePark.getVisitors().add(visitor);
        themePark.setStock(themePark.getStock() + themePark.getTicketPrice());

        visitorFacade.update(visitor);
        themeParkFacade.update(themePark);

        return visitorId + " visitor entered " + themePark.getName();

    }

    public String leaveThemePark(String id) {
        Visitor visitor = visitorFacade.getVisitorById(Long.parseLong(id));
        ThemePark themePark = themeParkFacade.getThemeParkWhereVisitorIs(Long.parseLong(id));

        if (null == themePark) {
            throw new VisitorIsNotInAnyThemeParkException("Visitor isn't in any theme park.");
        }

        themePark.removeVisitor(visitor);
        themeParkFacade.update(themePark);

        visitor.setActualPark(null);
        visitor.setTimeOfEnter(null);
        visitor.setActivity(false);

        visitorFacade.update(visitor);

        return id + " visitor left theme park.";
    }

    public String getOnMachine(String visitorId, String machineId) {
        Visitor visitor = visitorFacade.getVisitorById(Long.parseLong(visitorId));
        Machine machine = machineFacade.getMachineById(Long.parseLong(machineId));
        ThemePark themePark = themeParkFacade.getThemeParkWhereVisitorIs(Long.parseLong(visitorId));

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
        visitor.setMachine(machine);

        themeParkFacade.update(themePark);
        machineFacade.update(machine);
        visitorFacade.update(visitor);

        return visitorId + " visitor got on " + machine.getName();
    }

    public String getOff(String visitorId) {
        Visitor visitor = visitorFacade.getVisitorById(Long.parseLong(visitorId));
        Machine machine = visitor.getMachine();

        machine.getVisitorsRiding().remove(visitor);
        visitor.setState(State.REST);
        machineFacade.update(machine);
        visitorFacade.update(visitor);
        return visitorId + " visitor get off from " + machine.getName();
    }

    public GuestBookDTO writeToGuestBook(String visitorId, String guestBookEntry) {
        if (null == themeParkFacade.getThemeParkWhereVisitorIs(Long.parseLong(visitorId))) {
            throw new VisitorIsNotInAnyThemeParkException(visitorId + " visitor isn't in any theme park.");
        }

        GuestBookDTO guestBook = new GuestBookDTO();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        guestBook.setVisitor(getVisitorById(visitorId));
        guestBook.setDateOfEntry(calendar.getTime());
        guestBook.setThemePark(new ThemeParkDTO(themeParkFacade.getThemeParkWhereVisitorIs(Long.parseLong(visitorId))));
        guestBook.setEntryText(guestBookEntry);

        return guestBookservice.addGuestBook(guestBook);
    }

    public List<VisitorDTO> getVisitorsRidingMachine(String id) {
        List<VisitorDTO> result = new ArrayList<>();
        for (Visitor visitor : visitorFacade.getVisitorsRidingMachine(Long.parseLong(id))) {
            result.add(new VisitorDTO(visitor));
        }
        return result;
    }

    public Integer getNumberOfRestingVisitors(String id) {
        Object object = visitorFacade.getNumberOfRestingVisitors(Long.parseLong(id));
        if (null != object) {
            return (Integer) object;
        } else {
            return 0;
        }
    }
}
