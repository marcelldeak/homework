
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
import xyz.codingmentor.theme.park.dto.GuestBookDTO;
import xyz.codingmentor.theme.park.service.GuestBookService;

@Path("/guestbooks")
@Produces(MediaType.APPLICATION_JSON)
public class GuestBookREST {
    
    @Inject
    private GuestBookService guestBookService;
    
    @GET
    public List<GuestBookDTO> getAll(){
        return guestBookService.getAllGuestBook();
    }
    
    @GET
    @Path("/{id}")
    public GuestBookDTO getGuestBookById(@PathParam("id") String id){
        return guestBookService.getGuestBookById(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public GuestBookDTO editGuestBook(@PathParam("id") String id,GuestBookDTO guestBook){
        return guestBookService.editGuestBook(id,guestBook);
    }
    
    
    @DELETE
    @Path("/{id}")
    public GuestBookDTO deleteGuestBook(@PathParam("id") String id){
        return guestBookService.deleteGuestBook(id);
    }
}
