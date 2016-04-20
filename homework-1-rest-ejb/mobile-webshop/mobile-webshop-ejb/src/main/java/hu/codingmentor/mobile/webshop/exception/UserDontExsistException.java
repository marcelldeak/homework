package hu.codingmentor.mobile.webshop.exception;

public class UserDontExsistException extends IllegalArgumentException {

    public UserDontExsistException() {
        super();
    }

    public UserDontExsistException(String s) {
        super(s);
    }

}
