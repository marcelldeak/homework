package hu.codingmentor.mobile.webshop.rest;

import hu.codingmentor.mobile.webshop.dto.MobileDTO;
import hu.codingmentor.mobile.webshop.dto.UserDTO;
import hu.codingmentor.mobile.webshop.exception.InvalidSessionException;
import hu.codingmentor.mobile.webshop.exception.UserDontExsistException;
import hu.codingmentor.mobile.webshop.service.CartService;
import hu.codingmentor.mobile.webshop.service.InventoryService;
import hu.codingmentor.mobile.webshop.service.UserManagementService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
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

@Path("/cart")
@SessionScoped
public class CartRESTService implements Serializable{

    private static final String USER = "user"; 
    
    @Inject
    private CartService cartService;

    @Inject
    private InventoryService inventoryService;

    @Inject
    private UserManagementService userService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MobileDTO> addToCart(@Context HttpServletRequest request, MobileDTO mobile) {
        HttpSession session = request.getSession();
        Object userAttribute = session.getAttribute(USER);
        if (null != userAttribute && userAttribute instanceof UserDTO) {
            UserDTO user = (UserDTO) userAttribute;
            if (userService.getUserByUsername(user.getUsername()) != null) {
                inventoryService.removeMobile(mobile);
                cartService.addToCart(mobile);
                return cartService.getCart();
            }else
                throw new UserDontExsistException("User doesn't exist! Registrate first!");
        }
        throw new InvalidSessionException("Time expired! Log in again!");
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MobileDTO> getCartList(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userAttribute = session.getAttribute(USER);
        if (null != userAttribute && userAttribute instanceof UserDTO) {
            UserDTO user = (UserDTO) userAttribute;
            if (userService.getUserByUsername(user.getUsername()) != null)
                return cartService.getCart();
            else
                throw new UserDontExsistException("User doesn't exist! Registrate first!");
        }
        throw new InvalidSessionException("Time expired! Log in again!");
        
    }

    @POST
    @Path("/checkout")
    public String checkout() {
        cartService.checkout();
        return "Checkout successfully";
    }
}
