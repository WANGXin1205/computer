package com.growlithe.computer.excepetion;

/**
 * Created by WANGXin on 2017/5/21.
 */
public class TransactionException extends RuntimeException {

    private static final long serialVersionUID = -1;
    
    private String message;
    private Throwable cause;

    public TransactionException(){
        super();
    }
    public TransactionException(String message){
        super(message);
    }
    public TransactionException(Throwable cause){
        super(cause);
    }
    public TransactionException(String message, Throwable cause){
        super(message,cause);
    }
}
