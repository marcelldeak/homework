package hu.codingmentor.mobile.webshop.exceptionmapper;

import hu.codingmentor.mobile.webshop.dto.ExceptionDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(Throwable throwable) {
        logger.log(Level.SEVERE, "General Exception", throwable);
        return Response.serverError().entity(new ExceptionDTO(throwable.getClass().getSimpleName(), throwable.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
}
