package src.main.java;

import src.main.java.model.ValvetFileManager;

public class Main {

    public static void main(String[] args) {
        // 1. Load Valvet, customers, accounts and transactions from data.json
        // Create a bank with customers, accounts, and transactions
        Valvet valv = new Valvet();
        // Populate your bank with data...

        // Save the bank data to a JSON file
        ValvetFileManager.saveBank(bank);

        // Load the bank data from the JSON file
        Bank loadedBank = BankManager.loadBank();

        // Display the loaded bank data
        if (loadedBank != null) {
            System.out.println(loadedBank);

    }


}

