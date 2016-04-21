package hu.codingmentor.mobile.webshop.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class ItemSoldOutException extends IllegalArgumentException {

    public ItemSoldOutException() {
        super();
    }

    public ItemSoldOutException(String s) {
        super(s);
    }

}
