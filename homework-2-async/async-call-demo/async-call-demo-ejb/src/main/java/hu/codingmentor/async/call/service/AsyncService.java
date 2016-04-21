package hu.codingmentor.async.call.service;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Asynchronous
public class AsyncService{

    @Inject
    private Logger logger;
    
    @Resource
    private SessionContext context;

    public Future<String> asyncFutureMethod() {
        
        logger.log(Level.INFO, "Async future method on " + 
                Thread.currentThread().getName() + " started.");
        
        int i = 0;
        while(!context.wasCancelCalled() && i < 20){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AsyncService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(i%2 == 0)
                logger.log(Level.INFO,"Async future method on " + 
                        Thread.currentThread().getName() + " is working.");
            ++i;
        }
        logger.log(Level.INFO, "Async future method on " + 
                Thread.currentThread().getName() + " stopped!");
        
        return new AsyncResult<>("Async future method on " + 
                Thread.currentThread().getName() + " stopped, after " + i + " seconds.");
    }

    public void asyncVoidMethod(){
        int methodId = Math.abs(new Random().nextInt() % 10 + 1);
        logger.log(Level.INFO,"Method " + methodId + " started.");
        for(int i=1;i<=methodId*3;++i){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AsyncService.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(i%2 == 0)
                logger.log(Level.INFO,"Method "+ methodId + " on " + 
                        Thread.currentThread().getName() + " is working.");
        }
        logger.log(Level.INFO,"Method " + methodId + " finished.");
    }

}
