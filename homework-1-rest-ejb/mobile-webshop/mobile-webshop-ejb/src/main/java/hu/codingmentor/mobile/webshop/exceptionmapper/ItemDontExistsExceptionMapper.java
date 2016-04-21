package hu.codingmentor.mobile.webshop.exceptionmapper;

import hu.codingmentor.mobile.webshop.dto.ExceptionDTO;
import hu.codingmentor.mobile.webshop.exception.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ItemDontExistsExceptionMapper implements ExceptionMapper<ItemDoesntExistException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(ItemDoesntExistException exception) {
        logger.log(Level.WARNING, "Item don't exist");
        return Response.serverError().entity(new ExceptionDTO(exception.getClass().getSimpleName(), exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
