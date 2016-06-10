package xyz.codingmentor.theme.park.facade;

import java.util.List;
import javax.ejb.Stateless;
import xyz.codingmentor.theme.park.entity.GuestBook;
import xyz.codingmentor.theme.park.exception.EntityDoesNotExistsException;

@Stateless
public class GuestBookFacade extends ItemFacade<GuestBook> {

    public List<GuestBook> getAllGuestBook() {
        return entityManager.createQuery("select gb from GuestBook gb", GuestBook.class).getResultList();
    }

    public GuestBook getGuestBookById(Long id) {
        GuestBook guestBook = entityManager.createQuery("select gb from GuestBook gb where gb.id = :id", GuestBook.class)
                .setParameter("id", id).getSingleResult();
        if (null == guestBook) {
            throw new EntityDoesNotExistsException("GuestBook doesn't exists.");
        }
        return guestBook;
    }
}
