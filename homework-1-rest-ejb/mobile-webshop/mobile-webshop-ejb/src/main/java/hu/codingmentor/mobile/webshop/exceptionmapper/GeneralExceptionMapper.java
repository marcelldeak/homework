
package hu.codingmentor.mobile.webshop.exceptionmapper;

import hu.codingmentor.mobile.webshop.qualifier.LoggerQualifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {
    @Inject @LoggerQualifier
    Logger logger;
    
    @Override
    public Response toResponse(Throwable throwable) {
        logger.log(Level.WARNING, "General Exception",throwable);
        return Response.serverError().entity(throwable).type(MediaType.APPLICATION_JSON).build();
    }
}
