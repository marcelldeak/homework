package hu.codingmentor.mobile.webshop.rest;

import hu.codingmentor.mobile.webshop.dto.UserDTO;
import hu.codingmentor.mobile.webshop.exception.UserDontExsistException;
import hu.codingmentor.mobile.webshop.interceptor.Validate;
import hu.codingmentor.mobile.webshop.service.UserManagementService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserRESTService {

    @Inject
    private UserManagementService userService;

    @POST
    @Path("/")
    @Validate
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO addUser(UserDTO user) {
        return userService.addUser(user);
    }

    @DELETE
    @Path("/{username}")
    public UserDTO removeUser(@PathParam("username") String username) {
        return userService.removeUser(username);
    }

    @PUT
    @Path("/{username}")
    @Validate
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO editUser(@PathParam("username") String username, UserDTO user) {
        return userService.editUser(username, user);
    }

    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username) {
        return userService.getUserByUsername(username);
    }

    @GET
    @Path("/")
    public List<UserDTO> getUsers() {
        return userService.getUserList();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@Context HttpServletRequest request,
            @QueryParam("username") String username,
            @QueryParam("password") String password) throws ServletException {
        HttpSession session = request.getSession(true);
        for (UserDTO user : getUsers()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                session.setMaxInactiveInterval(1200);
                session.setAttribute("user", user);
                return user.getUsername() + " logged in successfully.";
            }
        }
        throw new UserDontExsistException("Wrong username or password");
    }

    @POST
    @Path("/logout")
    public String logout(@Context HttpServletRequest request) {
        request.getSession().invalidate();
        return "Successfully logout";
    }
}
