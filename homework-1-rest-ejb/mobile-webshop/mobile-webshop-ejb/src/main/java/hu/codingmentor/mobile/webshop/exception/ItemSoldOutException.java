
package hu.codingmentor.mobile.webshop.exception;


public class ItemSoldOutException extends IllegalArgumentException{

    public ItemSoldOutException() {
        super();
    }

    public ItemSoldOutException(String s) {
        super(s);
    }
    
}
