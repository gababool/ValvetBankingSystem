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
    @FXML public Label totalBalanceLabel;
    @FXML public Label numberOfAccountsLabel;
    @FXML public Button returnToMenuButton;
    @FXML public Button newTransactionButton;
    @FXML public Button deleteCustomerButton;
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

        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
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
        boolean result = MessageDisplayer.displayConfirmationBox("Do you want to delete customer: " + customer.getFullName() + "?");
        if (result){
            try {
                Main.getValvet().deleteCustomer(customer.getPERSONAL_NUMBER());
                if (customer != null) {
                    switcher.switchToAllCustomersView(event);
                }
            } catch (Exception e) {
                MessageDisplayer.displayErrorAlert("Error", e.getMessage());
            }
        }
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
        boolean result = MessageDisplayer.displayConfirmationBox("Do you want to delete the selected account?");
        if (result){
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

}
