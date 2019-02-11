package com.tcs.product;

import java.util.LinkedHashMap;

public class WordsCollection {
   // All the items purposefully kept public to keep things simple...
    // For future modifications, they need to be declared private, with additional methods
    // for accessing and updating them.....
    public static LinkedHashMap<String,Integer> books =  new LinkedHashMap<String,Integer>(){{
              put("books",1);
              put("book",1);
              put("Learning Java",1);

    }};

    public static LinkedHashMap<String,Integer> food =  new LinkedHashMap<String,Integer>() {{

             put("chocolates",1);
             put("chocolate",1);
             put("oranges",1);
             put("rice",1);
             put("mangoes",1);

    }};

    public static LinkedHashMap<String,Integer> medical =  new LinkedHashMap<String,Integer>() {{

            put("pills",1);
            put("medicine",1);

    }};
}
