package com.blibli.inventory.Exception;

public class CategoryNotFoundException extends Exception{

    public CategoryNotFoundException(String message)
    {
        super(message);
    }
    public CategoryNotFoundException()
    {
        super();
    }
}
