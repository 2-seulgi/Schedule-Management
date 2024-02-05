package com.schedule_management.exception;

public class InvalidEventException extends RuntimeException{
    public InvalidEventException(String message){
        super(message);
    }
}
