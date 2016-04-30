package xyz.codingmentor.theme.park.facade;

import java.util.List;
import javax.ejb.Stateless;
import xyz.codingmentor.theme.park.entity.Visitor;
import xyz.codingmentor.theme.park.exception.EntityDoesNotExistsException;

@Stateless
public class VisitorFacade extends ItemFacade<Visitor> {

    public List<Visitor> getAllVisitor() {
        return entityManager.createQuery("select v from Visitor v", Visitor.class).getResultList();
    }

    public Visitor getVisitorById(Long id) {
        Visitor visitor = entityManager.createQuery("select v from Visitor v where v.id = :id", Visitor.class)
                .setParameter("id", id).getSingleResult();
        if (null == visitor) {
            throw new EntityDoesNotExistsException("Visitor doesn't exists in database.");
        }
        return visitor;
    }

    public List<Visitor> getVisitorsFromThemePark(Long id) {
        return entityManager.createQuery("select v from Visitor v where v.actualPark.id = :id", Visitor.class)
                .setParameter("id", id).getResultList();
    }

    public List<Visitor> getVisitorsRidingMachine(Long id) {
        return entityManager.createQuery("select v from Visitor v where v.machine.id = :id", Visitor.class)
                .setParameter("id", id).getResultList();
    }

    public Object getNumberOfRestingVisitors(Long id) {
        return entityManager.createQuery("select count(v) from Visitor v where v.state like 'REST' and v.actualPark.id = :id")
                .setParameter("id", id).getSingleResult().toString();

    }
}
