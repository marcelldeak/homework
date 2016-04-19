
package hu.codingmentor.mobile.webshop.exceptionmapper;

import hu.codingmentor.mobile.webshop.exception.*;
import hu.codingmentor.mobile.webshop.qualifier.LoggerQualifier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class ItemSoldOutExceptionMapper implements ExceptionMapper<ItemSoldOutException>{

    @Inject @LoggerQualifier
    Logger logger;
    
    @Override
    public Response toResponse(ItemSoldOutException exception) {
        logger.log(Level.FINER, "Item sold out",exception);
        return Response.serverError().entity(exception).type(MediaType.APPLICATION_JSON).build();
    }
    
}
