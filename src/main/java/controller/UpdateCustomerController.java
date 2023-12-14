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
    @FXML public TextField personalNumberField;
    @FXML public TextField firstNameField;
    @FXML public TextField lastNameField;
    @FXML public Button updateCustomerButton;
    @FXML public Button cancelButton;
    @FXML public Button clearAllButton;
    private Customer customer;
    private static SceneSwitcher switcher = new SceneSwitcher();

    public void loadCustomerInfo(Customer customer){
        this.customer = customer;
        personalNumberField.setText(customer.getPERSONAL_NUMBER());
    }
    public void updateCustomerInfo(ActionEvent event) {
        String newFirstName = firstNameField.getText();
        String newSurname = lastNameField.getText();
        String customerPNO = customer.getPERSONAL_NUMBER();
        try {
            Main.getValvet().updateCustomerName(customerPNO, newFirstName, newSurname);
            switcher.switchToCustomerPage(event, customer);
        } catch (InvalidInputException e) {
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            MessageDisplayer.displayIOErrorAlert();
        }
    }

    public void returnToCustomerView(ActionEvent event) {
        try {
            switcher.switchToCustomerPage(event, customer);
        } catch (IOException e) {
            e.printStackTrace();
            MessageDisplayer.displayIOErrorAlert();
        }
    }

    public void clearTextFields(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
    }
}
