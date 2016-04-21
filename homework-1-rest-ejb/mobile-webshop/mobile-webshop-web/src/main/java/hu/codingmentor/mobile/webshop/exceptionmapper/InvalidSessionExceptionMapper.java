package hu.codingmentor.mobile.webshop.exceptionmapper;

import hu.codingmentor.mobile.webshop.dto.ExceptionDTO;
import hu.codingmentor.mobile.webshop.exception.InvalidSessionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidSessionExceptionMapper implements ExceptionMapper<InvalidSessionException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(InvalidSessionException exception) {
        logger.log(Level.WARNING, "Invalid session");
        return Response.serverError().entity(new ExceptionDTO(exception.getClass().getSimpleName(), exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
