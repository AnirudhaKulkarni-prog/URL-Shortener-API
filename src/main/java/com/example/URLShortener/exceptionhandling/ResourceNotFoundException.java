package com.example.URLShortener.exceptionhandling;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);

    }


}
