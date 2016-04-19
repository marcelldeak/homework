
package hu.codingmentor.mobile.webshop.rest;

import hu.codingmentor.mobile.webshop.dto.MobileDTO;
import hu.codingmentor.mobile.webshop.dto.UserDTO;
import hu.codingmentor.mobile.webshop.exception.AccessDeniedException;
import hu.codingmentor.mobile.webshop.exception.UserDontExsistException;
import hu.codingmentor.mobile.webshop.interceptor.Validate;
import hu.codingmentor.mobile.webshop.service.InventoryService;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/mobiles")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryRESTService implements Serializable{
    
    @Inject
    private InventoryService inventoryService;
    
    @POST
    @Path("/")
    @Validate
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileDTO addMobile(@Context HttpServletRequest request,MobileDTO mobile){
        HttpSession session = request.getSession();
        Object userAttribute = session.getAttribute("user");
        if (null != userAttribute && userAttribute instanceof UserDTO) {
            UserDTO user = (UserDTO) userAttribute;
            if(user.getAdmin())
                return inventoryService.addMobile(mobile);
            else
                throw new AccessDeniedException("Admin user can add mobile to inventory only!");
        }
        throw new UserDontExsistException("User don't exist!");
    }
    
    @GET
    @Path("/")
    public List<MobileDTO> getMobiles(){
        return inventoryService.getMobileList();
    }
}
