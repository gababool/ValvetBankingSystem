package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;
import src.main.java.model.InvalidInputException;

import java.io.IOException;

public class UpdateCustomerController {
    @FXML private TextField personalNumberField;
    @FXML private TextField firstNameField;
    @FXML private TextField surnameField;
    @FXML private Button updateCustomerButton;
    @FXML private Button cancelButton;
    @FXML private Button clearAllButton;
    private Customer customer;
    private static SceneSwitcher switcher = new SceneSwitcher();

    // Loads in customer data and places it the appropriate fields
    public void loadCustomerInfo(Customer customer){
        this.customer = customer;
        personalNumberField.setText(customer.getPERSONAL_NUMBER());
        firstNameField.setText(customer.getFirstName());
        surnameField.setText(customer.getSurname());
    }

    // Updates the customer information based on user input
    public void updateCustomerInfo(ActionEvent event) {
        String newFirstName = firstNameField.getText().trim();
        String newSurname = surnameField.getText().trim();
        String customerPNO = customer.getPERSONAL_NUMBER().trim();
        try {
            Main.getValvet().updateCustomerName(customerPNO, newFirstName, newSurname);
            switcher.switchToCustomerPage(event, customer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
    }

    // Switches screen back to the customer
    public void returnToCustomerView(ActionEvent event) {
        try {
            switcher.switchToCustomerPage(event, customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clears all text fields
    public void clearTextFields(ActionEvent event) {
        firstNameField.clear();
        surnameField.clear();
    }
}
