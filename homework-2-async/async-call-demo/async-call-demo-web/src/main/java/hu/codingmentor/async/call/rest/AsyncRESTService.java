package hu.codingmentor.async.call.rest;

import hu.codingmentor.async.call.service.AsyncService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/demo")
@Asynchronous
public class AsyncRESTService{

    @Inject
    private Logger logger;

    @Inject
    private AsyncService asyncService;

    @GET
    @Path("/future")
    public String asyncFutureDemo() {
        logger.log(Level.INFO, "Async future methods started.");

        Future<String> future1 = asyncService.asyncFutureMethod();
        Future<String> future2 = asyncService.asyncFutureMethod();
        String result1, result2;

        try {
            Thread.sleep(10000);
            future1.cancel(true);

            result1 = future1.get();
            result2 = future2.get();

            logger.log(Level.INFO, result1 + " " + result2);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(AsyncRESTService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Async future methods called!";
    }

    @GET
    @Path("/void")
    public String asyncVoidDemo() {
        asyncService.asyncVoidMethod();
        asyncService.asyncVoidMethod();
        return "Async void methods called!";
    }
}
