package xyz.codingmentor.theme.park.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import xyz.codingmentor.theme.park.dto.MachineDTO;
import xyz.codingmentor.theme.park.dto.VisitorDTO;
import xyz.codingmentor.theme.park.service.MachineService;
import xyz.codingmentor.theme.park.service.VisitorService;

@Path("/machines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MachineREST {

    @Inject
    private MachineService machineService;

    @Inject
    private VisitorService visitorService;

    @GET
    public List<MachineDTO> getAll() {
        return machineService.getAllMachine();
    }

    @POST
    public MachineDTO addMachine(MachineDTO machine) {
        return machineService.addMachine(machine);
    }

    @GET
    @Path("/{id}")
    public MachineDTO getMachineById(@PathParam("id") String id) {
        return machineService.getMachineById(id);
    }

    @PUT
    @Path("/{id}")
    public MachineDTO editMachine(@PathParam("id") String id, MachineDTO machine) {
        return machineService.editMachine(id, machine);
    }

    @DELETE
    @Path("/{id}")
    public MachineDTO deleteMachine(@PathParam("id") String id) {
        return machineService.deleteMachine(id);
    }

    @GET
    @Path("/{id}/ridingvisitors")
    public List<VisitorDTO> getVisitorsRidingMachine(@PathParam("id") String id) {
        return visitorService.getVisitorsRidingMachine(id);
    }
}
