package xyz.codingmentor.theme.park.facade;

import java.util.List;
import javax.ejb.Stateless;
import xyz.codingmentor.theme.park.entity.GuestBook;
import xyz.codingmentor.theme.park.entity.ThemePark;
import xyz.codingmentor.theme.park.exception.EntityDoesNotExistsException;

@Stateless
public class ThemeParkFacade extends ItemFacade<ThemePark> {

    public List<ThemePark> getAllThemePark() {
        return entityManager.createQuery("select tp from ThemePark tp", ThemePark.class).getResultList();
    }

    public ThemePark getThemeParkById(Long id) {
        ThemePark themePark = entityManager.createQuery("select tp from ThemePark tp where tp.id = :id", ThemePark.class)
                .setParameter("id", id).getSingleResult();
        if (null == themePark) {
            throw new EntityDoesNotExistsException("Theme park doesn't exists in database.");
        }
        return themePark;
    }

    public ThemePark getThemeParkWhereVisitorIs(Long id) {
        return entityManager.createQuery("select v.actualPark from Visitor v where v.id = :id", ThemePark.class)
                .setParameter("id", id).getSingleResult();
    }

    public List<GuestBook> getGuestBooksOfThemePark(Long id) {
        return entityManager.createQuery("select gb from GuestBook gb where gb.visitor.id = "
                + "(select v.id from Visitor v where v.actualPark.id = :id)", GuestBook.class).setParameter("id", id)
                .getResultList();
    }

    public List<ThemePark> getThemeParkFromCountry(String country) {
        return entityManager.createQuery("select tp from ThemePark tp where tp.address.country like :country", ThemePark.class)
                .setParameter("country", country).getResultList();
    }
}
