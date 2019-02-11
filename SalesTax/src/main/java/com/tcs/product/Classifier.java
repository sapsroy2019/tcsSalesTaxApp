package com.tcs.product;

import java.util.concurrent.Callable;

public class Classifier implements Callable<Boolean> {

    String[] input;
    String type;

    Classifier(String[]inp,String type){
           this.input = inp;
           this.type = type;
    }

public Boolean call(){

        if(type.equals("book")){
            for(String token:input){
                if(WordsCollection.books.containsKey(token.toLowerCase())){
                    return true;
                }
            }
            return false;
        }

        else if(type.equals("food")){
            for(String token:input){
                if(WordsCollection.food.containsKey(token.toLowerCase())){
                    return true;
                }
            }
            return false;
        }
        else{
            for(String token:input){
                if(WordsCollection.medical.containsKey(token.toLowerCase())){
                    return true;
                }
            }
            return false;
        }

    }

}
