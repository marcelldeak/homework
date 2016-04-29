
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
import xyz.codingmentor.theme.park.entity.Machine;
import xyz.codingmentor.theme.park.service.MachineService;

@Path("/machines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MachineREST {
    
    @Inject
    private MachineService machineService;
    
    @GET
    public List<Machine> getAll(){
        return machineService.getAllMachine();
    }
    
    @POST
    public Machine addMachine(Machine machine){
        return machineService.addMachine(machine);
    }
    
    @GET
    @Path("/{id}")
    public Machine getMachineById(@PathParam("id") String id){
        return machineService.getMachineById(id);
    }
    
    @PUT
    @Path("/{id}")
    public Machine editMachine(Machine machine){
        return machineService.editMachine(machine);
    }
    
    @DELETE
    @Path("/{id}")
    public Machine deleteMachine(@PathParam("id") String id){
        return machineService.deleteMachine(getMachineById(id));
    }
}
