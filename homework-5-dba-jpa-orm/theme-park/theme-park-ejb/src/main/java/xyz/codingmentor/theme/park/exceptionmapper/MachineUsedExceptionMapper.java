
package xyz.codingmentor.theme.park.exceptionmapper;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import xyz.codingmentor.theme.park.dto.ExceptionDTO;
import xyz.codingmentor.theme.park.exception.MachineUsedException;

@Provider
public class MachineUsedExceptionMapper implements ExceptionMapper<MachineUsedException> {

    @Inject
    private Logger logger;

    @Override
    public Response toResponse(MachineUsedException exception) {
        logger.log(Level.WARNING, "Not enough money.");
        return Response.serverError().entity(new ExceptionDTO(exception.getClass().getCanonicalName(), exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }
    
}
