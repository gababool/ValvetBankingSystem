package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Customer;
import java.io.IOException;

public class ValvetController {

    @FXML
    public Button findCustomerButton;
    @FXML
    public Button transactionsButton;
    @FXML
    public Button addNewCustomerButton;
    @FXML
    public TextField personalNumberField;
    private static SceneSwitcher switcher = new SceneSwitcher();
    @FXML
    public Button viewAllCustomersButton;


    // Finds customer by entering a valid personal number into the TextField at the main menu
    // and switches scene to the customer's page
    public void findCustomer(ActionEvent event)  {
        Customer customer = Main.getValvet().getCustomer(personalNumberField.getText());
        if (personalNumberField.getText().isBlank()){
            MessageDisplayer.displayMessage("Please enter a personal number");
        }
        else if (customer == null){
            MessageDisplayer.displayErrorAlert("Error", "Customer not found");
        } else {
            try {
                switcher.switchToCustomerPage(event, customer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Switches screen from main menu to the screen for making a new transaction
    public void goToTransactionScreen(ActionEvent actionEvent)  {
        try {
            switcher.switchToTransactionPage(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Switches screen from main menu to the searchable list of all customers
    public void viewAllCustomers(ActionEvent actionEvent) {
        try {
            switcher.switchToAllCustomersView(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Switches screen to the page for creating and adding a new customer to the system
    public void addNewCustomer(ActionEvent actionEvent)  {
        try {
            switcher.switchToCreateCustomerView(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
