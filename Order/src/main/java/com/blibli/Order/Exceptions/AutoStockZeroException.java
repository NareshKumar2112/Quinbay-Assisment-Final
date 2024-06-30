package com.blibli.Order.Exceptions;

public class AutoStockZeroException extends Exception {

    public AutoStockZeroException()
    {
        super();
    }
    public AutoStockZeroException(String message)
    {
        super(message);
    }
}
