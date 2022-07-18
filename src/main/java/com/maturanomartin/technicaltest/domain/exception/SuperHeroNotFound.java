package com.maturanomartin.technicaltest.domain.exception;

public class SuperHeroNotFound extends RuntimeException{

    public SuperHeroNotFound(String message){
        super(message);
    }

}
