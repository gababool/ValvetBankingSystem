package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Customer;

public class CreateCustomerViewController {

    public TextField personalNumberField;
    public TextField firstNameField;
    public TextField lastNameField;

    private static SceneSwitcher switcher = new SceneSwitcher();
    public void registerCustomerAction(ActionEvent event) throws Exception{
        String personalNumber = personalNumberField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        Customer customer = Main.getValvet().createCustomer(firstName, lastName, personalNumber);

        switcher.switchToCustomerPage(event, customer);
    }
}
