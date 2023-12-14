package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Customer;
import src.main.java.model.Account;
import src.main.java.model.Transaction;
import java.io.IOException;

public class TransactionController {
    @FXML public TextField receiverTextField;
    @FXML public TextField senderTextField;
    @FXML public TextField amountTextField;
    @FXML public Button makeTransactionButton;
    @FXML public Button clearAllButton;
    @FXML public Button returnButton;

    public Customer customer;
    public Account account;
    public Button switchAccountButton;
    private static SceneSwitcher switcher = new SceneSwitcher();

    // Switches the account number entered in the sender and receiver fields with each other, based on button press.
    public void switchAccount(ActionEvent event){
        String senderAccountNumber = senderTextField.getText();
        String receiverAccountNumber = receiverTextField.getText();
        receiverTextField.setText(senderAccountNumber);
        senderTextField.setText(receiverAccountNumber);
    }

    // Reads the data entered in the transaction screen and performs the transaction if everything is valid.
    // Else it displays an error to the user of what went wrong.
    public void makeTransaction(ActionEvent event) {
        String lineSeparator = System.lineSeparator();
        String senderAccountNumber = senderTextField.getText();
        String receiverAccountNumber = receiverTextField.getText();
        String amount = amountTextField.getText();
        double value = 0;
        try {
            value = Double.parseDouble(amount);
            Transaction transaction = Main.getValvet().makeTransaction(senderAccountNumber, receiverAccountNumber, value);
            MessageDisplayer.displayMessage("Transaction Successfully made: " + lineSeparator + transaction.toString());
            amountTextField.clear();
        } catch (NumberFormatException e) {
            MessageDisplayer.displayErrorAlert("Error", "Amount not valid");
        } catch (Exception e) {
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
    }

    // Runs when the user enters the transaction screen from the customer page, by selecting a
    // certain account. Keeps track of which customer the screen was entered from, and pre-fills the account number.
    public void loadTransactionWithCustomer(Customer customer, Account account){
        this.customer = customer;
        this.account = account;
        this.senderTextField.setText(account.getAccountNumber());
    }

    // Clears all text fields for the transaction
    public void clearAll(ActionEvent event) {
        receiverTextField.clear();
        senderTextField.clear();
        amountTextField.clear();
    }

    // Switches screen to either the customer view or the main menu, depending on from where you entered the
    // transaction screen
    public void returnToLastView(ActionEvent event){
        if (customer == null){
            try {
                switcher.switchToMain(event);
            } catch (Exception e) {
                e.printStackTrace();
                MessageDisplayer.displayIOErrorAlert();
            }
        } else {
            try {
                switcher.switchToCustomerPage(event, customer);
            } catch (Exception e) {
                e.printStackTrace();
                MessageDisplayer.displayIOErrorAlert();
            }
        }

    }
}
