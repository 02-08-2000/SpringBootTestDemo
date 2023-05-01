package com.example.SpringBootTestDemo.exceptions;

public class StudentNotFoundException extends RuntimeException{
    private static final long seriolVersionUID=1L;
    public StudentNotFoundException(String message){
        super(message);
    }
}
