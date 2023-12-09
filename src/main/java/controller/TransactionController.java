package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Customer;
import src.main.java.model.Account;

import java.io.IOException;

public class TransactionController {
    public TextField receiverTextField;
    public TextField senderTextField;
    public TextField amountTextField;
    public Button makeTransactionButton;
    public Button clearAllButton;
    public Button cancelButton;
    public Customer customer;
    public Account account;

    private static SceneSwitcher switcher = new SceneSwitcher();

    public void makeTransaction(ActionEvent event) {
        String senderAccountNumber = senderTextField.getText();
        String receiverAccountNumber = receiverTextField.getText();
        String amount = amountTextField.getText();
        double value = 0;
        try {
            value = Double.parseDouble(amount);
            Main.getValvet().makeTransaction(senderAccountNumber, receiverAccountNumber, value);
        } catch (NumberFormatException e) {
            MessageDisplayer.displayErrorAlert("Error", "Amount not valid");
        } catch (Exception e) {
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
    }
    public void loadTransactionWithCustomer(Customer customer, Account account){
        this.customer = customer;
        this.account = account;
        this.senderTextField.setText(account.getAccountNumber());
    }

    public void clearAll(ActionEvent event) {
        receiverTextField.clear();
        senderTextField.clear();
        amountTextField.clear();
    }

    public void returnToLastView(ActionEvent event) throws IOException {
        if (customer == null){
            switcher.switchToMain(event);
        } else {
            switcher.switchToCustomerPage(event, customer);
        }

    }
}
