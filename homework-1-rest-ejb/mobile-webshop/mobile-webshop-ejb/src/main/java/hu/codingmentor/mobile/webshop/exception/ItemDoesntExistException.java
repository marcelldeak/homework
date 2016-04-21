package hu.codingmentor.mobile.webshop.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class ItemDoesntExistException extends IllegalArgumentException {

    public ItemDoesntExistException() {
        super();
    }

    public ItemDoesntExistException(String s) {
        super(s);
    }

}
