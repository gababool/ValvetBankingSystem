package src.main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import src.main.java.Main;
import src.main.java.model.Customer;
import src.main.java.model.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.LinkedHashMap;

public class CustomerController{
    @FXML private Label totalBalanceLabel;
    @FXML private Label numberOfAccountsLabel;
    @FXML private Button returnToMenuButton;
    @FXML private Button newTransactionButton;
    @FXML private Button deleteCustomerButton;
    @FXML private Button updateCustomerInfoButton;
    @FXML private Button addAccountButton;
    @FXML private Button deleteAccount;
    @FXML private Label titleNameLabel;
    @FXML private Label personalNumberLabel;
    @FXML private Button goToAccountButton;
    @FXML private Button goToAllCustomersButton;
    @FXML private Label newTransactionErrorLabel;
    @FXML private TableView<Account> allAccounts;
    @FXML private TableColumn<Account, String> accountNumberColumn;
    @FXML private TableColumn<Account, String> balanceColumn;

    private static SceneSwitcher switcher = new SceneSwitcher();
    private Customer customer;

    // Method for transferring customers from a LinkedHashMap into an ObservableList, used in tables in the GUI.
    public ObservableList<Account> hashMapToObservableList(){
        ObservableList<Account> ObservableCustomers = FXCollections.observableArrayList();
        LinkedHashMap<String, Account> accounts = customer.getAccounts();
        ObservableCustomers.addAll(accounts.values());
        return ObservableCustomers;
    }

    // Loads in customer data and places it the appropriate fields and tables on the customer screen.
    public void loadCustomer(Customer customer){
        this.titleNameLabel.setText("Name: " + customer.getSurname() + ", " + customer.getFirstName());
        this.personalNumberLabel.setText("Personal Number: " + customer.getPERSONAL_NUMBER());
        this.totalBalanceLabel.setText("Total Balance: " + customer.getTotalBalance());
        this.numberOfAccountsLabel.setText("Number Of Accounts: " + customer.getNumberOfAccounts());
        this.customer = customer;
        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        allAccounts.setItems(hashMapToObservableList());
    }

    // Switches screen from the customer to the selected account screen, from the table.
    public void goToAccount(ActionEvent event) {
        Account account = allAccounts.getSelectionModel().getSelectedItem();
        if (account == null){
            MessageDisplayer.displayErrorAlert("Error", "No account selected");
        } else {
            try {
                switcher.switchToAccountPage(event, account, customer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Customer customer = Main.getValvet().getCustomer(personalNumberLabel.getText().replace("Personal Number: ", ""));
    }

    // Switches screen to the main menu
    public void goToMainMenu(ActionEvent event) {
        try {
            switcher.switchToMain(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Switches screen to the all customers overview screen
    public void goToAllCustomers(ActionEvent event) {
        try {
            switcher.switchToAllCustomersView(event, customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Switches screen from the customer to the transaction page, based on which account was selected in the table
    // for the transaction.
    public void goToTransactionView(ActionEvent event) {
        Account account = allAccounts.getSelectionModel().getSelectedItem();
        if (account == null){
            newTransactionErrorLabel.setText("No account selected");
        } else {
            try {
                switcher.switchToTransactionPage(event, customer, account);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Deletes customer from the system, unless they have remaining balance. Displays a confirmation message before.
    public void deleteCustomer(ActionEvent event)   {
        boolean result = MessageDisplayer.displayConfirmationBox("Do you want to delete customer: " + customer.getFullName() + "?");
        if (result){
            try {
                Main.getValvet().deleteCustomer(customer.getPERSONAL_NUMBER());
                if (customer != null) {
                    switcher.switchToAllCustomersView(event);
                }
            } catch (IOException e){
                e.printStackTrace();
            } catch (Exception e) {
                MessageDisplayer.displayErrorAlert("Error", e.getMessage());
            }
        }
    }

    // Adds new account to the customer and saves the bank data.
    public void addAccount(ActionEvent event){
        try {
            Main.getValvet().createAccount(customer.getPERSONAL_NUMBER());
            ValvetFileManager.saveBank(Main.getValvet());
        } catch (Exception e){
            MessageDisplayer.displayErrorAlert("Error", e.getMessage());
        }
        this.loadCustomer(customer);
    }

    // Deletes a selected account from the customer, unless it has remaining balance. Displays confirmation message
    // before and saves the bank data.
    public void deleteAccount(ActionEvent event){
        boolean result = MessageDisplayer.displayConfirmationBox("Do you want to delete the selected account?");
        if (result){
            try {
                Account account = allAccounts.getSelectionModel().getSelectedItem();
                Main.getValvet().deleteAccount(customer.getPERSONAL_NUMBER(), account.getAccountNumber());
                ValvetFileManager.saveBank(Main.getValvet());
            } catch (NullPointerException e){
                MessageDisplayer.displayErrorAlert("Error", "No account selected");
            } catch (Exception e) {
                MessageDisplayer.displayErrorAlert("Error", e.getMessage());
            }
            this.loadCustomer(customer);
        }

    }

    public void goToUpdateCustomerView(ActionEvent event) {
        try {
            switcher.switchToUpdateCustomerView(event, customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
