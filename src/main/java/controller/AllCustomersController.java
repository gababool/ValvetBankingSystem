package src.main.java.controller;

import javafx.scene.control.ListView;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;

import java.util.LinkedHashMap;

public class AllCustomersController {
    public ListView allCustomersListView;

    public void loadAllCustomers(){
        LinkedHashMap<String, Customer> customers = Main.getValvet().getAllCustomers();
        for(Customer customer : customers.values()){
            allCustomersListView.getItems().add(customer);
        }
    }




}
