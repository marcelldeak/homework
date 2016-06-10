package xyz.codingmentor.theme.park.exceptionmapper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import xyz.codingmentor.theme.park.dto.ExceptionDTO;
import xyz.codingmentor.theme.park.exception.NotEnoughSpaceException;

@Provider
public class NotEnoughSpaceExceptionMapper implements ExceptionMapper<NotEnoughSpaceException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(NotEnoughSpaceException exception) {
        logger.log(Level.WARNING, "Not enough Space.");
        return Response.serverError().entity(new ExceptionDTO(exception.getClass().getCanonicalName(), exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
