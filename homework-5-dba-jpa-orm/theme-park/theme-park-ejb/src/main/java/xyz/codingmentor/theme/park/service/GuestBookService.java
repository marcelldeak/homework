package xyz.codingmentor.theme.park.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.theme.park.dto.GuestBookDTO;
import xyz.codingmentor.theme.park.entity.GuestBook;
import xyz.codingmentor.theme.park.facade.GuestBookFacade;
import xyz.codingmentor.theme.park.interceptor.Validate;

@Stateless
public class GuestBookService {

    @Inject
    private GuestBookFacade guestBookFacade;
    
    public List<GuestBookDTO> getAllGuestBook(){
        List<GuestBookDTO> result = new ArrayList<>();
        for(GuestBook guestbook : guestBookFacade.getAllGuestBook()){
            result.add(new GuestBookDTO(guestbook));
        }
        return result;
    }
    
    @Validate
    public GuestBookDTO addGuestBook(GuestBookDTO guestBook){
        return new GuestBookDTO(guestBookFacade.create(new GuestBook(guestBook)));
    }
    
    public GuestBookDTO getGuestBookById(String id){
        return new GuestBookDTO(guestBookFacade.getGuestBookById(Long.parseLong(id)));
    }
    
    @Validate
    public GuestBookDTO editGuestBook(String id,GuestBookDTO guestBook){
        GuestBook guestBookEntity = guestBookFacade.getGuestBookById(Long.parseLong(id));
        
        guestBookEntity.setDateOfEntry(guestBook.getDateOfEntry());
        guestBookEntity.setEntryText(guestBook.getEntryText());
        
        return new GuestBookDTO(guestBookFacade.update(guestBookEntity));
    }
    
    public GuestBookDTO deleteGuestBook(String id){
        return new GuestBookDTO(guestBookFacade.delete(guestBookFacade.getGuestBookById(Long.parseLong(id))));
    }
}
