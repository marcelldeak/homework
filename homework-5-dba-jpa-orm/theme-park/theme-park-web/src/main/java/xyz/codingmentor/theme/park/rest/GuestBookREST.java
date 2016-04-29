
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
import xyz.codingmentor.theme.park.entity.GuestBook;
import xyz.codingmentor.theme.park.service.GuestBookService;

@Path("/guestbooks")
@Produces(MediaType.APPLICATION_JSON)
public class GuestBookREST {
    
    @Inject
    private GuestBookService guestBookService;
    
    @GET
    public List<GuestBook> getAll(){
        return guestBookService.getAllGuestBook();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public GuestBook addGuestBook(GuestBook guestBook){
        return guestBookService.addGuestBook(guestBook);
    }
    
    @GET
    @Path("/{id}")
    public GuestBook getGuestBookById(@PathParam("id") String id){
        return guestBookService.getGuestBookById(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public GuestBook editGuestBook(GuestBook guestBook){
        return guestBookService.editGuestBook(guestBook);
    }
    
    
    @DELETE
    @Path("/{id}")
    public GuestBook deleteGuestBook(@PathParam("id") String id){
        return guestBookService.deleteGuestBook(getGuestBookById(id));
    }
}
