
package xyz.codingmentor.theme.park.config;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("/app")
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
        resources.add(xyz.codingmentor.theme.park.exceptionmapper.DoesNotHaveMachineExceptionMapper.class);
        resources.add(xyz.codingmentor.theme.park.exceptionmapper.EntityDoesNotExistsExceptionMapper.class);
        resources.add(xyz.codingmentor.theme.park.exceptionmapper.GeneralExceptionMapper.class);
        resources.add(xyz.codingmentor.theme.park.exceptionmapper.MachineUsedExceptionMapper.class);
        resources.add(xyz.codingmentor.theme.park.exceptionmapper.NotEnoughMoneyExceptionMapper.class);
        resources.add(xyz.codingmentor.theme.park.exceptionmapper.NotEnoughSpaceExceptionMapper.class);
        resources.add(xyz.codingmentor.theme.park.exceptionmapper.VisitorIsNotInAnyThemeParkExceptionMapper.class);
        resources.add(xyz.codingmentor.theme.park.exceptionmapper.VisitorTooYoungExceptionMapper.class);
        resources.add(xyz.codingmentor.theme.park.rest.GuestBookREST.class);
        resources.add(xyz.codingmentor.theme.park.rest.MachineREST.class);
        resources.add(xyz.codingmentor.theme.park.rest.ThemeParkREST.class);
        resources.add(xyz.codingmentor.theme.park.rest.VisitorREST.class);
    }
    
}
