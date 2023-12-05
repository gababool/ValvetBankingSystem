package src.main.java;


import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;
import src.main.java.model.Customer;

public class Main {

    public static void main(String[] args) throws Exception {
        // 1. Load Valvet, customers, accounts and transactions from data.json
        // Create a bank with customers, accounts, and transactions
        Valvet valv = new Valvet("1337");
        // Populate your bank with data...
       

        valv.createAccount(11111);

        System.out.println(valv.getCustomer(11111).viewAllAccounts());
        System.out.println(valv.getCustomer(22222).viewAllAccounts());



        valv.viewAllCustomers();
        // Save the bank data to a JSON file
        ValvetFileManager.saveBank(valv);

        // Load the bank data from the JSON file
        Valvet loadedBank = ValvetFileManager.loadBank();

        loadedBank.viewAllCustomers();
        System.out.println(valv.getCustomer(11111).viewAllAccounts());
        System.out.println(valv.getCustomer(22222).viewAllAccounts());

        // Display the loaded bank data
        if (loadedBank != null) {
            System.out.println(loadedBank);

        }


    }

}

