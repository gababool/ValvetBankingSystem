package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;

import java.io.IOException;
import java.util.LinkedHashMap;

public class AllCustomersController {
    public ListView<Customer> allCustomersListView;
    public Button returnToMainMenuButton;
    public Button goToCustomerButton;

    public TableView<Customer> allCustomers;
    public TableColumn<Customer, String> nameColumn;
    public TableColumn<Customer, String> personalNumberColumn;
    public TableColumn<Customer, String> accountColumn;
    public TableColumn<Customer, String> totalBalanceColumn;

    private static SceneSwitcher switcher = new SceneSwitcher();

    public void loadAllCustomers(){
        LinkedHashMap<String, Customer> customers = Main.getValvet().getAllCustomers();
        for(Customer customer : customers.values()){
            allCustomersListView.getItems().add(customer);
        }
    }
    public void goToMainMenu(ActionEvent event) throws IOException {
        switcher.switchToMain(event);
    }
    public void goToCustomer(ActionEvent event) throws IOException {
        Customer customer = allCustomersListView.getSelectionModel().getSelectedItem();
        switcher.switchToCustomerPage(event, customer);
    }




}
