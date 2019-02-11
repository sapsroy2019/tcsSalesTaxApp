package com.tcs.product;

public class CustomException extends Exception{
    private String message;

    CustomException(String msg){
        this.message = msg;
    }

    public String toString(){
        return "Exception encountered--->"+this.message;
    }
}
