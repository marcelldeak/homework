
package hu.codingmentor.mobile.webshop.exception;


public class ValidationException extends IllegalArgumentException{

    public ValidationException() {
        // default constructor
    }

    public ValidationException(String s) {
        super(s);
    }
    
}
