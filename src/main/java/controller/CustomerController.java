package src.main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import src.main.java.Main;
import src.main.java.model.Customer;
import src.main.java.model.*;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.file.WatchEvent;
import java.security.spec.ECField;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.concurrent.CyclicBarrier;

public class CustomerController{
    public Label totalBalanceLabel;
    public Label numberOfAccountsLabel;
    public Button returnToMenuButton;
    public Button newTransactionButton;
    @FXML public Button deleteCustomerButton;
    @FXML
    private Label titleNameLabel;
    @FXML
    private Label personalNumberLabel;
    @FXML
    private Button goToAccountButton;
    @FXML
    private Button goToAllCustomersButton;

    @FXML private Label newTransactionErrorLabel;
    @FXML private TableView<Account> allAccounts;
    @FXML private TableColumn<Account, String> accountNumberColumn;
    @FXML private TableColumn<Account, String> balanceColumn;
    @FXML
    private static SceneSwitcher switcher = new SceneSwitcher();

    private Customer customer;

    public ObservableList<Account> hashMapToObservableList(){
        ObservableList<Account> ObservableCustomers = FXCollections.observableArrayList();
        LinkedHashMap<String, Account> accounts = customer.getAccounts();
        ObservableCustomers.addAll(accounts.values());
        return ObservableCustomers;
    }
    public void loadCustomer(Customer customer){
        this.titleNameLabel.setText("Name: " + customer.getFullName());
        this.personalNumberLabel.setText("Personal Number: " + customer.getPERSONAL_NUMBER());
        this.totalBalanceLabel.setText("Total Balance: " + customer.getTotalBalance());
        this.numberOfAccountsLabel.setText("Number Of Accounts: " + customer.getNumberOfAccounts());
        this.customer = customer;

        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountNumber"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("balance"));
        allAccounts.setItems(hashMapToObservableList());
    }
    public void goToAccount(ActionEvent event) throws IOException {
        Account account = allAccounts.getSelectionModel().getSelectedItem();
        if (account == null){
            MessageDisplayer.displayErrorAlert("Error", "No account selected");
        }
        Customer customer = Main.getValvet().getCustomer(personalNumberLabel.getText().replace("Personal Number: ", ""));
        switcher.switchToAccountPage(event, account, customer);
    }

    public void goToMainMenu(ActionEvent event) throws IOException {
        switcher.switchToMain(event);
    }
    public void goToAllCustomers(ActionEvent event) throws IOException {
        switcher.switchToAllCustomersView(event, customer);
    }

    public void goToTransactionView(ActionEvent event) throws IOException {
        Account account = allAccounts.getSelectionModel().getSelectedItem();
        if (account == null){
            newTransactionErrorLabel.setText("No account selected");
        }
        switcher.switchToTransactionPage(event, customer, account);
    }

    public void deleteCustomer(ActionEvent event) throws IOException {
        try {
            Main.getValvet().deleteCustomer(customer.getPERSONAL_NUMBER());
        } catch (Exception e){
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
        switcher.switchToAllCustomersView(event);
    }
    public void addAccount(ActionEvent event){
        try {
            Main.getValvet().createAccount(customer.getPERSONAL_NUMBER());
        } catch (Exception e){
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
        this.loadCustomer(customer);
    }
    public void deleteAccount(ActionEvent event){
        try {
            Account account = allAccounts.getSelectionModel().getSelectedItem();
            Main.getValvet().deleteAccount(customer.getPERSONAL_NUMBER(), account.getAccountNumber());
        } catch (NullPointerException e){
            MessageDisplayer.displayErrorAlert("Error", "No account selected");
        } catch (BalanceNotZeroException | CannotBeZeroException e) {
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
        this.loadCustomer(customer);
    }



}
