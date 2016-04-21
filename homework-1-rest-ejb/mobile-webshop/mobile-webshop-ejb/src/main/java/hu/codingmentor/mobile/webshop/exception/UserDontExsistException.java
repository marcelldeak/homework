package hu.codingmentor.mobile.webshop.exception;

import javax.ejb.ApplicationException;

@ApplicationException
public class UserDontExsistException extends IllegalArgumentException {

    public UserDontExsistException() {
        super();
    }

    public UserDontExsistException(String s) {
        super(s);
    }

}
