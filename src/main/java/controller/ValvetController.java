package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import src.main.java.Main;
import src.main.java.model.Customer;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;

public class ValvetController {

    public TextField deleteCustomerTextField;
    public Button deleteCustomerButton;


    public Button deleteAccountButton;
    public TextField deleteAccountTextField;


    public void deleteCustomer(ActionEvent actionEvent) throws Exception {
        // Replace this. This is for testing only.
        Customer customer = Main.getValvet().deleteCustomer(deleteCustomerTextField.getText());
        System.out.println(Main.getValvet().getCustomer("0002171437"));
        ValvetFileManager.saveBank(Main.getValvet());

    }

    public void deleteAccount(ActionEvent actionEvent) throws Exception{
        Main.getValvet().deleteAccount("0002171437", deleteAccountTextField.getText());
        ValvetFileManager.saveBank(Main.getValvet());
    }
}
