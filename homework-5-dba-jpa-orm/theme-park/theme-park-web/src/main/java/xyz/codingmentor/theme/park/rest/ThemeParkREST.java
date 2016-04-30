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
import xyz.codingmentor.theme.park.dto.MachineDTO;
import xyz.codingmentor.theme.park.dto.ThemeParkDTO;
import xyz.codingmentor.theme.park.service.MachineService;
import xyz.codingmentor.theme.park.service.ThemeParkService;
import xyz.codingmentor.theme.park.service.VisitorService;

@Path("/themeparks")
@Produces(MediaType.APPLICATION_JSON)
public class ThemeParkREST {

    @Inject
    private ThemeParkService themeParkService;

    @Inject
    private MachineService machineService;

    @Inject
    private VisitorService visitorService;

    @GET
    public List<ThemeParkDTO> getAll() {
        return themeParkService.getAllThemePark();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ThemeParkDTO addThemePark(ThemeParkDTO themePark) {
        return themeParkService.addThemePark(themePark);
    }

    @GET
    @Path("/{id}")
    public ThemeParkDTO getThemeParkById(@PathParam("id") String id) {
        return themeParkService.getThemeParkById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ThemeParkDTO editThemePark(@PathParam("id") String id, ThemeParkDTO themePark) {
        return themeParkService.editThemePark(id, themePark);
    }

    @DELETE
    @Path("/{id}")
    public ThemeParkDTO deleteThemePark(@PathParam("id") String id) {
        return themeParkService.deleteThemePark(id);
    }

    @POST
    @Path("/{id}/buymachine")
    public String buyMachine(@QueryParam("machineid") String machineId,
            @PathParam("id") String themeParkId) {
        return themeParkService.buyMachine(machineId, themeParkId);
    }

    @POST
    @Path("/{id}/sellmachine")
    public String sellMachine(@QueryParam("machineid") String machineId,
            @PathParam("id") String themeParkId) {
        return themeParkService.sellMachine(machineId, themeParkId);
    }

    @GET
    @Path("/{id}/ownedmachines")
    public List<MachineDTO> getOwnedMachines(@PathParam("id") String id) {
        return machineService.getMachinesFromThemePark(id);
    }

    @GET
    @Path("/{country}")
    public List<ThemeParkDTO> getThemeParkFromCountry(@PathParam("country") String country) {
        return themeParkService.getThemeParkFromCountry(country);
    }

    @GET
    @Path("/{id}/restingvisitorscount")
    public Integer getNumberOfRestingVisitors(@PathParam("id") String id) {
        return visitorService.getNumberOfRestingVisitors(id);
    }
}
