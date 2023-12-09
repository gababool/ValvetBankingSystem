package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Customer;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;

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
    private static CustomerController customerController;
    @FXML
    public Button viewAllCustomersButton;


    public void findCustomer(ActionEvent event) throws IOException {
        Customer customer = Main.getValvet().getCustomer(personalNumberField.getText());
        if (customer == null){
            MessageDisplayer.displayErrorAlert("Error", "Customer not found");
        }
        switcher.switchToCustomerPage(event, customer);
    }

    public void goToTransactionScreen(ActionEvent actionEvent) throws IOException {
        switcher.switchToTransactionPage(actionEvent);
    }

    public void viewAllCustomers(ActionEvent actionEvent) throws IOException {
        switcher.switchToAllCustomersView(actionEvent);
    }

    public void addNewCustomer(ActionEvent actionEvent) throws IOException {
        switcher.switchToCreateCustomerView(actionEvent);
    }

}
