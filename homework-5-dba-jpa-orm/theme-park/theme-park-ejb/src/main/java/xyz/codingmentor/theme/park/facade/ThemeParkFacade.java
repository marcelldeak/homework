
package xyz.codingmentor.theme.park.facade;

import java.util.List;
import javax.ejb.Stateless;
import xyz.codingmentor.theme.park.entity.ThemePark;
import xyz.codingmentor.theme.park.exception.EntityDoesNotExistsException;

@Stateless
public class ThemeParkFacade extends ItemFacade<ThemePark>{

    public List<ThemePark> getAllThemePark() {
        return entityManager.createQuery("select tp from ThemePark tp", ThemePark.class).getResultList();
    }

    public ThemePark getThemeParkById(long id) {
        ThemePark themePark = entityManager.createQuery("select tp from ThemePark tp where tp.id = :id", ThemePark.class)
                .setParameter("id", id).getSingleResult();
        if(null == themePark){
            throw new EntityDoesNotExistsException("Theme park doesn't exists in database.");
        }
        return themePark;
    }
    
}
