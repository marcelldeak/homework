
package xyz.codingmentor.theme.park.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ItemFacade<T> {
    
    @PersistenceContext(unitName = "themeParkPU")
    protected EntityManager entityManager;

    public ItemFacade() {
        //default constructor
    }
    
    public <T> T create(T entity){
        entityManager.persist(entity);
        return entity;
    }
    
    public <T> T read(Class<T> clazz, T entity){
        return entityManager.find(clazz, entity);
    }
    
    public <T> T update(T entity){
        return entityManager.merge(entity);
    }
    
    public <T> T delete(T entity){
        entityManager.remove(entity);
        return entity;
    }
}
