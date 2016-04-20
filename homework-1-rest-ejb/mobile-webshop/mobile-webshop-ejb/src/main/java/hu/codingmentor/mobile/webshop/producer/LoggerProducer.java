package hu.codingmentor.mobile.webshop.producer;

import hu.codingmentor.mobile.webshop.qualifier.LoggerQualifier;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {

    @Produces
    @LoggerQualifier
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
