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


    public void findCustomer(ActionEvent event) throws IOException {
        Customer customer = Main.getValvet().getCustomer(personalNumberField.getText());
        if (personalNumberField.getText().isBlank()){
            MessageDisplayer.displayErrorAlert("Error", "Please enter a personal number");
        }
        else if (customer == null){
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
