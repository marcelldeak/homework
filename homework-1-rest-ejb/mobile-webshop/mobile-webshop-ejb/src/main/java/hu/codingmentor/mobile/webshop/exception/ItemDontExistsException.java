
package hu.codingmentor.mobile.webshop.exception;


public class ItemDontExistsException extends IllegalArgumentException{

    public ItemDontExistsException() {
        super();
    }

    public ItemDontExistsException(String s) {
        super(s);
    }
    
}
