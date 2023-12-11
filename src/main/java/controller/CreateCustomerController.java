package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Customer;
import java.io.IOException;

public class CreateCustomerController {

    public TextField personalNumberField;
    public TextField firstNameField;
    public TextField lastNameField;
    public Button registerCustomerButton;
    public Button returnToMainMenuButton;
    public Button clearAllButton;

    private static SceneSwitcher switcher = new SceneSwitcher();

    public void registerCustomerAction(ActionEvent event) throws Exception {
        String personalNumber = personalNumberField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        Customer customer = null;
        try {
            customer = Main.getValvet().createCustomer(firstName, lastName, personalNumber);
        } catch (Exception e) {
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
        switcher.switchToCustomerPage(event, customer);
    }

    public void returnToMainMenu(ActionEvent actionEvent) throws IOException {
        switcher.switchToMain(actionEvent);
    }

    public void clearTextFields(ActionEvent actionEvent) {
        personalNumberField.clear();
        firstNameField.clear();
        lastNameField.clear();
    }

}
