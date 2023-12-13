package src.main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import src.main.java.Main;
import src.main.java.model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class AllCustomersController implements Initializable {
    @FXML public Button returnToMainMenuButton;
    @FXML public Button goToCustomerButton;
    @FXML public Button searchButton;
    @FXML public TextField searchField;
    @FXML private TableView<Customer> allCustomers;
    @FXML private TableColumn<Customer, String> nameColumn;
    @FXML private TableColumn<Customer, String> personalNumberColumn;
    @FXML private TableColumn<Customer, String> accountColumn;
    @FXML private TableColumn<Customer, Double> totalBalanceColumn;

    private static SceneSwitcher switcher = new SceneSwitcher();

    // Initializes the all customers overview screen by adding all the data from the customers that is displayed
    // to the user in the table.
    public void initialize(URL url, ResourceBundle resourceBundle){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        personalNumberColumn.setCellValueFactory(new PropertyValueFactory<>("PERSONAL_NUMBER"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
        totalBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("totalBalance"));
        allCustomers.setItems(hashMapToObservableList());
        allCustomers.getSortOrder().add(nameColumn);
    }

    // Loads the all customer overview screen with a certain customer selected.
    public void loadWithCustomer(Customer customer){
        allCustomers.getSelectionModel().select(customer);
        allCustomers.scrollTo(customer);
    }

    // Method for transferring customers from a LinkedHashMap into an ObservableList, used in tables in the GUI.
    public ObservableList<Customer> hashMapToObservableList(){
        ObservableList<Customer> ObservableCustomers = FXCollections.observableArrayList();
        LinkedHashMap<String, Customer> customers = Main.getValvet().getAllCustomers();
            ObservableCustomers.addAll(customers.values());
        return ObservableCustomers;
    }

    // Performs a search in the table of customer based on user input. Searches by name and personal number match.
    public void searchAction(ActionEvent event){
        String searchID = searchField.getText().toUpperCase();
        Stream<Customer> customerStream = allCustomers.getItems().stream();
        Optional<Customer> foundItem = customerStream.filter(item -> item.getPERSONAL_NUMBER().toUpperCase().startsWith(searchID)|| item.getFullName().toUpperCase().startsWith(searchID)).findAny();
        foundItem.ifPresent(item -> {
                    allCustomers.getSelectionModel().select(item);
                    allCustomers.scrollTo(item);
                });
    }

    // Makes the search function trigger by pressing the Enter-key.
    public void onEnter(ActionEvent event){
        searchAction(event);
    }

    // Switches to the main menu screen.
    public void goToMainMenu(ActionEvent event) throws IOException {
        switcher.switchToMain(event);
    }

    // Switches screen to the customer page of the selected customer in the table.
    public void goToCustomer(ActionEvent event) throws IOException {
        Customer customer = allCustomers.getSelectionModel().getSelectedItem();
        if (customer == null){
            MessageDisplayer.displayErrorAlert("Error", "No customer selected");
        }
        switcher.switchToCustomerPage(event, customer);
    }




}
