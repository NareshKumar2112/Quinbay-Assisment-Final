package com.blibli.inventory.config;


import com.blibli.inventory.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handlerProductNotFound( ProductNotFoundException productNotFoundException )
    {
        return new ResponseEntity<>( productNotFoundException.getMessage() , HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(value = CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handlerCategoryNotFound( CategoryNotFoundException categoryNotFoundException )
    {
        return new ResponseEntity<>( categoryNotFoundException.getMessage() , HttpStatus.NOT_FOUND );
    }

//    @ExceptionHandler(value = {InvalidPriceException.class, AutoStockZeroException.class})
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    ResponseEntity<String> handleInvalidPrice( Exception exceptionInvalid )
//    {
//        return new ResponseEntity<>(exceptionInvalid.getMessage() , HttpStatus.METHOD_NOT_ALLOWED);
//    }
}