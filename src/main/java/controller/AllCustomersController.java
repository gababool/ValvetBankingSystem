package src.main.java.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AllCustomersController implements Initializable {
    @FXML public Button returnToMainMenuButton;
    @FXML public Button goToCustomerButton;
    @FXML public Button searchButton;
    @FXML public TextField searchField;


    @FXML private TableView<Customer> allCustomers;
    @FXML private TableColumn<Customer, SimpleStringProperty> nameColumn;
    @FXML private TableColumn<Customer, String> personalNumberColumn;
    @FXML private TableColumn<Customer, String> accountColumn;
    @FXML private TableColumn<Customer, Double> totalBalanceColumn;

    private static SceneSwitcher switcher = new SceneSwitcher();

    public void initialize(URL url, ResourceBundle resourceBundle){
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, SimpleStringProperty>("name"));
        personalNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("PERSONAL_NUMBER"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("account"));
        totalBalanceColumn.setCellValueFactory(new PropertyValueFactory<Customer, Double>("totalBalance"));
        allCustomers.setItems(hashMapToObservableList());
    }
    public ObservableList<Customer> hashMapToObservableList(){
        ObservableList<Customer> ObservableCustomers = FXCollections.observableArrayList();
        LinkedHashMap<String, Customer> customers = Main.getValvet().getAllCustomers();
            ObservableCustomers.addAll(customers.values());
        return ObservableCustomers;
    }
    public void searchAction(ActionEvent event){
        String searchID = searchField.getText();
        allCustomers.getItems().stream()
                .filter(item -> item.getPERSONAL_NUMBER().startsWith(searchID)|| item.getFullName().startsWith(searchID))
                .findAny()
                .ifPresent(item -> {
                    allCustomers.getSelectionModel().select(item);
                    allCustomers.scrollTo(item);
                });
    }
    public void goToMainMenu(ActionEvent event) throws IOException {
        switcher.switchToMain(event);
    }
    public void goToCustomer(ActionEvent event) throws IOException {
        Customer customer = allCustomers.getSelectionModel().getSelectedItem();
        if (customer == null){
            MessageDisplayer.displayErrorAlert("Error", "No customer selected");
        }
        switcher.switchToCustomerPage(event, customer);
    }




}
