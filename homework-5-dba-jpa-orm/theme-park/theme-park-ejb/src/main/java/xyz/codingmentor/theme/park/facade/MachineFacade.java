
package xyz.codingmentor.theme.park.facade;

import java.util.List;
import javax.ejb.Stateless;
import xyz.codingmentor.theme.park.entity.Machine;
import xyz.codingmentor.theme.park.exception.EntityDoesNotExistsException;

@Stateless
public class MachineFacade extends ItemFacade<Machine>{
    
    public List<Machine> getAllMachine(){
        return entityManager.createQuery("select m from Machine m", Machine.class).getResultList();
    }
    
    public Machine getMachineById(Long id){
        Machine machine =  entityManager.createQuery("select m from Machine m where m.id = :id", Machine.class)
                .setParameter("id", id).getSingleResult();
        if(null == machine){
            throw new EntityDoesNotExistsException("Machine doesn't exists in database.");
        }
        return machine;
    }
}
