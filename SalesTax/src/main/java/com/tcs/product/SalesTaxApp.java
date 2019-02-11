package com.tcs.product;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.FutureTask;

public class SalesTaxApp {

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Sales Tax calculation App .............");
        String choice ;
        while (true){

            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("Enter 1 to continue with tax calculation..........");

            System.out.println("Enter -1 to exit the application...........");
            System.out.println("Enter 0 to add new tags to collection............");

            try{
               choice = sc.nextLine();
               if(choice.equals("-1")){
                   System.out.println("Thanks for using our application ...................");
                   break;
               }

               else if(choice.equals("0")){
                   System.out.println("Enter 1 for foods.....");
                   System.out.println("Enter 2 for books......");
                   System.out.println("Enter 3 for medicine ........");
                   String s = sc.nextLine();
                   System.out.println("Enter the tag to be inserted .......");
                   String tag = sc.nextLine();
                   if(s.equals("1")){
                       WordsCollection.food.put(tag,1);
                   }else if (s.equals("2")){
                       WordsCollection.books.put(tag,1);
                   }else if (s.equals("3")){
                       WordsCollection.medical.put(tag,1);
                   }else{
                       throw new CustomException("Wrong choice of command........");
                   }
               }

               else if(choice.equals("1")){
                    System.out.println("Enter the no of particulars to be calculated.....");
                    int n = Integer.parseInt(sc.nextLine());
                    ArrayList<String> output = new ArrayList<String>();
                    double salesTax=0.00;
                    double total =0.00;
                    while(n>0){
                        n--;
                        String inputLine = sc.nextLine();
                        double updatedPrice =0.00;
                        boolean importedFlag = false;
                        if(inputLine.toLowerCase().contains("imported")){importedFlag = true;}

                        String []inputArray = inputLine.split(" ");
                        //int noOfItems = Integer.parseInt(inputArray[0]);
                        double price = Double.parseDouble(inputArray[inputArray.length-1]);

                        Classifier obj_food = new Classifier(inputArray,"food");
                        FutureTask<Boolean> foodFuture =new FutureTask<Boolean>(obj_food);
                        Thread foodClassify =new Thread(foodFuture);
                        foodClassify.start();

                        Classifier obj_books = new Classifier(inputArray,"book");
                        FutureTask<Boolean> bookFuture =new FutureTask<Boolean>(obj_books);
                        Thread bookClassify =new Thread(bookFuture);
                        bookClassify.start();

                        Classifier obj_medicine = new Classifier(inputArray,"medicine");
                        FutureTask<Boolean> medicineFuture =new FutureTask<Boolean>(obj_medicine);
                        Thread medicineClassify =new Thread(medicineFuture);
                        medicineClassify.start();

                        if(foodFuture.get() == true || bookFuture.get() == true || medicineFuture.get() == true){
                            // Sales Tax not applicable
                            salesTax += 0.00;
                            updatedPrice = price;
                        }else{
                            double calcSalesTax = price * 0.10;

                            salesTax += Math.round(calcSalesTax*20)/20.0;

                            updatedPrice = price + Math.round(calcSalesTax*20)/20.0;

                        }

                        if(importedFlag == true){
                            double calcImpTax = price * 0.05;
                            salesTax += Math.round(calcImpTax*20)/20.0;
                            updatedPrice += Math.round(calcImpTax*20)/20.0;

                        }

                        total += updatedPrice;

                        String outputLine = "";
                        for(int i =0;i<inputArray.length-2;i++){
                            outputLine += inputArray[i] + " ";

                        }
                        outputLine += ": " + new DecimalFormat("#0.00").format(updatedPrice);
                        output.add(outputLine);
                    }
                    System.out.println("Output..........");
                    for(String s: output){
                        System.out.println(s);
                    }

                    System.out.println("Sales Taxes: " + new DecimalFormat("#0.00").format(salesTax));
                    System.out.println("Total: " + new DecimalFormat("#0.00").format(total));

               }
               else{
                   throw new CustomException("Wrong choice of command........");
               }
            }catch(Exception e){
                 System.out.println("Error ocurred while execution .......");
                 System.out.println(e);
            }


        }


    }
}
