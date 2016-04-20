package hu.codingmentor.mobile.webshop.exception;

public class UserAlreadyExsistsException extends IllegalArgumentException {

    public UserAlreadyExsistsException() {
        super();
    }

    public UserAlreadyExsistsException(String s) {
        super(s);
    }

}
