
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
import xyz.codingmentor.theme.park.entity.ThemePark;
import xyz.codingmentor.theme.park.service.MachineService;
import xyz.codingmentor.theme.park.service.ThemeParkService;

@Path("/themeparks")
@Produces(MediaType.APPLICATION_JSON)
public class ThemeParkREST {
    
    @Inject
    private ThemeParkService themeParkService;
    
    @Inject
    private MachineService machineService;
    
    @GET
    public List<ThemePark> getAll(){
        return themeParkService.getAllThemePark();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ThemePark addThemePark(ThemePark themePark){
        return themeParkService.addThemePark(themePark);
    }
    
    @GET
    @Path("/{id}")
    public ThemePark getThemeParkById(@PathParam("id") String id){
        return themeParkService.getThemeParkById(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ThemePark editThemePark(ThemePark themePark){
        return themeParkService.editThemePark(themePark);
    }
    
    @DELETE
    @Path("/{id}")
    public ThemePark deleteThemePark(@PathParam("id") String id){
        return themeParkService.deleteThemePark(getThemeParkById(id));
    }
    
    @POST
    @Path("/{id}/buymachine")
    public String buyMachine(@QueryParam("machineId") String machineId,
            @PathParam("id") String themeParkId){
        return themeParkService.buyMachine(machineId,themeParkId);
    }
    
    @POST
    @Path("/{id}/sellmachine")
    public String sellMachine(@QueryParam("machineId") String machineId,
            @PathParam("id") String themeParkId){
        return themeParkService.sellMachine(machineId,themeParkId);
    }
}
