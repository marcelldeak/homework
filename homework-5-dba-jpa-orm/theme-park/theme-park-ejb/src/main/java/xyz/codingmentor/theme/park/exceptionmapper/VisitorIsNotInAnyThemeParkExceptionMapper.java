package xyz.codingmentor.theme.park.exceptionmapper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import xyz.codingmentor.theme.park.dto.ExceptionDTO;
import xyz.codingmentor.theme.park.exception.VisitorIsNotInAnyThemeParkException;

@Provider
public class VisitorIsNotInAnyThemeParkExceptionMapper implements ExceptionMapper<VisitorIsNotInAnyThemeParkException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(VisitorIsNotInAnyThemeParkException exception) {
        logger.log(Level.WARNING, "Visitor is not in any theme park.");
        return Response.serverError().entity(new ExceptionDTO(exception.getClass().getCanonicalName(), exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
