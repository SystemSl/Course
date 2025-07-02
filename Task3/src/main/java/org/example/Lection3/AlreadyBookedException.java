package org.example.Lection3;

public class AlreadyBookedException extends RuntimeException{
    public AlreadyBookedException(String message) {
        super(message);
    }
}
