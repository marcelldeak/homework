package xyz.codingmentor.theme.park.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.entity.GuestBook;
import xyz.codingmentor.theme.park.facade.GuestBookFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class GuestBookService {

    @Inject
    private GuestBookFacade guestBookFacade;
    
    public List<GuestBook> getAllGuestBook(){
        return guestBookFacade.getAllGuestBook();
    }
    
    @Validate
    public GuestBook addGuestBook(GuestBook guestBook){
        return guestBookFacade.create(guestBook);
    }
    
    public GuestBook getGuestBookById(String id){
        return guestBookFacade.getGuestBookById(Long.parseLong(id));
    }
    
    @Validate
    public GuestBook editGuestBook(GuestBook guestBook){
        return guestBookFacade.update(guestBook);
    }
    
    public GuestBook deleteGuestBook(GuestBook guestBook){
        return guestBookFacade.delete(guestBook);
    }
}
