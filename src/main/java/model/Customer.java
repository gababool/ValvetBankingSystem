package src.main.java.model;

import java.util.HashMap;

public class Customer {

   private HashMap<Account, Integer> accounts;
   private String firstName;
   private String lastName;
   final int PERSONAL_NUMBER;


   public Customer(String firstname, String lastName, int PERSONAL_NUMBER){
       this.PERSONAL_NUMBER = PERSONAL_NUMBER;
       this.firstName = firstname;
       this.lastName = lastName;
       this.accounts = new HashMap<Account, Integer>();

   }

   public String veiwAllAccounts(){

   }

   public String veiwAccount(){

   }






}
