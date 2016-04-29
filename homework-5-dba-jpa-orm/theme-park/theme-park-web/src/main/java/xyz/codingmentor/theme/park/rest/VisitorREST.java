
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import xyz.codingmentor.theme.park.entity.GuestBook;
import xyz.codingmentor.theme.park.entity.Visitor;
import xyz.codingmentor.theme.park.service.VisitorService;

@Path("/visitors")
@Produces(MediaType.APPLICATION_JSON)
public class VisitorREST {
    
    @Inject
    private VisitorService visitorService;
    
    @GET
    public List<Visitor> getAll(){
        return visitorService.getAllVisitor();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Visitor addVisitor(Visitor visitor){
        return visitorService.addVisitor(visitor);
    }
    
    @GET
    @Path("/{id}")
    public Visitor getVisitorById(@PathParam("id") String id){
        return visitorService.getVisitorById(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Visitor editVisitor(Visitor visitor){
        return visitorService.editVisitor(visitor);
    }
    
    @DELETE
    @Path("/{id}")
    public Visitor deleteVisitor(@PathParam("id") String id){
        return visitorService.deleteVisitor(visitorService.getVisitorById(id));
    }
    
    @POST
    @Path("/{id}/entry")
    public String enterThemePark(@PathParam("id") String visitorId,
            @QueryParam("themeparkid") String themeParkId){
        return visitorService.enterThemePark(visitorId, themeParkId);
    }
    
    @POST
    @Path("/{id}/leave")
    public String leaveThemePark(@PathParam("id") String id){
        return visitorService.leaveThemePark(id);
    }
    
    @POST
    @Path("/{id}/geton")
    public String getOnMachine(@PathParam("id") String visitorId,
            @QueryParam("machineid") String machineId){
        return visitorService.getOnMachine(visitorId, machineId);
    }
    
    @POST
    @Path("/{id}/getoff")
    public String getOff(@PathParam("id") String visitorId,
            @QueryParam("machineid") String machineId){
        return visitorService.getOff(visitorId, machineId);
    }
    
    @POST
    @Path("/{id}/writetoguestbook")
    @Consumes(MediaType.APPLICATION_JSON)
    public GuestBook writeToGuestBook(@PathParam("id") String visitorId, String guestBookEntry){
        return visitorService.writeToGuestBook(visitorId, guestBookEntry);
    }
}
