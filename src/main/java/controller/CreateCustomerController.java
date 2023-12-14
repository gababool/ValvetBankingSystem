package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.AlreadyExistsException;
import src.main.java.model.Customer;
import src.main.java.model.InvalidInputException;
import src.main.java.model.NotFoundException;

import java.io.IOException;

public class CreateCustomerController {

    @FXML  public TextField personalNumberField;
    @FXML  public TextField firstNameField;
    @FXML  public TextField lastNameField;
    @FXML  public Button registerCustomerButton;
    @FXML  public Button returnToMainMenuButton;
    @FXML  public Button clearAllButton;

    private static SceneSwitcher switcher = new SceneSwitcher();

    // Registers a new customer based on information entered in the "Create customer"-screen, if data is valid.
    public void registerCustomerAction(ActionEvent event)  {
        String personalNumber = personalNumberField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        Customer customer = null;
        try {
            customer = Main.getValvet().createCustomer(firstName, lastName, personalNumber);
            switcher.switchToCustomerPage(event, customer);
        } catch (IOException e) {
            e.printStackTrace();
            MessageDisplayer.displayIOErrorAlert();
        } catch (Exception e){
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
    }

    // Returns to the main menu from the "Create Customer"-screen
    public void returnToMainMenu(ActionEvent actionEvent) {
        try {
            switcher.switchToMain(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
            MessageDisplayer.displayIOErrorAlert();
        }
    }

    // Clears all text fields.
    public void clearTextFields(ActionEvent actionEvent) {
        personalNumberField.clear();
        firstNameField.clear();
        lastNameField.clear();
    }

}
