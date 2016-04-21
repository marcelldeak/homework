package hu.codingmentor.mobile.webshop.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class UserAlreadyExsistsException extends IllegalArgumentException {

    public UserAlreadyExsistsException() {
        super();
    }

    public UserAlreadyExsistsException(String s) {
        super(s);
    }

}
