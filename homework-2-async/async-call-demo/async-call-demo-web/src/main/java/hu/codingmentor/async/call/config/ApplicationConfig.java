
package hu.codingmentor.async.call.config;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("app")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(hu.codingmentor.async.call.rest.AsyncRESTService.class);
    }
    
}
