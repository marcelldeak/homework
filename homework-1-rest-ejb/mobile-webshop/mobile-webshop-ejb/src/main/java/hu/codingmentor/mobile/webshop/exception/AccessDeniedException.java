package hu.codingmentor.mobile.webshop.exception;

public class AccessDeniedException extends IllegalArgumentException {

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String s) {
        super(s);
    }

}
