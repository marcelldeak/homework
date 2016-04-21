package hu.codingmentor.mobile.webshop.config;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/app")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(hu.codingmentor.mobile.webshop.exceptionmapper.AccessDeniedExceptionMapper.class);
        resources.add(hu.codingmentor.mobile.webshop.exceptionmapper.GeneralExceptionMapper.class);
        resources.add(hu.codingmentor.mobile.webshop.exceptionmapper.InvalidSessionExceptionMapper.class);
        resources.add(hu.codingmentor.mobile.webshop.exceptionmapper.ItemDontExistsExceptionMapper.class);
        resources.add(hu.codingmentor.mobile.webshop.exceptionmapper.ItemSoldOutExceptionMapper.class);
        resources.add(hu.codingmentor.mobile.webshop.exceptionmapper.UserAlreadyExsistsExceptionMapper.class);
        resources.add(hu.codingmentor.mobile.webshop.exceptionmapper.UserDontExsistExceptionMapper.class);
        resources.add(hu.codingmentor.mobile.webshop.exceptionmapper.ValidationExceptionMapper.class);
        resources.add(hu.codingmentor.mobile.webshop.rest.CartRESTService.class);
        resources.add(hu.codingmentor.mobile.webshop.rest.InventoryRESTService.class);
        resources.add(hu.codingmentor.mobile.webshop.rest.UserRESTService.class);
    }

}
