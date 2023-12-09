package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;

import java.io.IOException;

public class TransactionController {
    public TextField receiverTextField;
    public TextField senderTextField;
    public TextField amountTextField;
    public Button makeTransactionButton;
    public Button clearAllButton;
    public Button cancelButton;

    private static SceneSwitcher switcher = new SceneSwitcher();

    public void makeTransaction(ActionEvent event) {
        String senderAccountNumber = senderTextField.getText();
        String receiverAccountNumber = receiverTextField.getText();
        String amount = amountTextField.getText();
        double value = 0;
        try {
            value = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            MessageDisplayer.displayErrorAlert("Error", "Amount not valid");
        }
        try {
            Main.getValvet().makeTransaction(senderAccountNumber, receiverAccountNumber, value);
        } catch (Exception ex) {
            MessageDisplayer.displayErrorAlert("Error", ex.getMessage());
        }
    }

    public void clearAll(ActionEvent event) {
        receiverTextField.clear();
        senderTextField.clear();
        amountTextField.clear();
    }

    public void returnToLastView(ActionEvent event) throws IOException {
        // Want to make it so it either returns to a customer or the menu, depending on where the user was before
        switcher.switchToMain(event);
    }
}
