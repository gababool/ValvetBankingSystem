package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Customer;
import src.main.java.model.Valvet;

public class ValvetController {

    public TextField deleteCustomerTextField;
    public Button deleteCustomerButton;

    public void deleteCustomer(ActionEvent actionEvent) throws Exception {
        // Replace this. This is for testing only.
        Customer customer = Main.valv.deleteCustomer("0002171437");
        System.out.println(Main.valv.getCustomer("0002171437"));

    }
}
