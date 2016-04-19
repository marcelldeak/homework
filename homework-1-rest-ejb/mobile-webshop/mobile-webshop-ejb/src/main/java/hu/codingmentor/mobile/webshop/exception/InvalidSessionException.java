
package hu.codingmentor.mobile.webshop.exception;


public class InvalidSessionException extends IllegalArgumentException{

    public InvalidSessionException() {
        super();
    }

    public InvalidSessionException(String s) {
        super(s);
    }
    
}
