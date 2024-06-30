package com.blibli.Order.Exceptions;

public class InvalidPriceException extends Exception{

    public InvalidPriceException()
    {
        super();
    }
    public InvalidPriceException(String message)
    {
        super(message);
    }
}
