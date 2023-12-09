package src.main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import src.main.java.Main;
import src.main.java.model.Customer;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import src.main.java.model.*;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.nio.file.WatchEvent;
import java.util.LinkedHashMap;

public class CustomerController {
    public Label totalBalanceLabel;
    public Label numberOfAccountsLabel;
    public Button returnToMenuButton;
    public Button makeTransactionButton;
    public Button deleteCustomerButton;
    public Button makeDepositButton;
    public Button makeWithdrawalButton;
    @FXML
    private Label titleNameLabel;
    @FXML
    private Label personalNumberLabel;
    @FXML
    private Button goToAccountButton;
    @FXML
    private ListView<Account> accountList;
    @FXML
    private static SceneSwitcher switcher = new SceneSwitcher();

    public void loadCustomer(Customer customer){
        this.titleNameLabel.setText("Name: " + customer.getFullName());
        this.personalNumberLabel.setText("Personal Number: " + customer.getPERSONAL_NUMBER());
        this.totalBalanceLabel.setText("Total Balance: " + customer.getTotalBalance());
        this.numberOfAccountsLabel.setText("Number Of Accounts: " + customer.getNumberOfAccounts());

        LinkedHashMap<String, Account> accounts = customer.getAccounts();
        for(Account account : accounts.values()){
            accountList.getItems().add(account);
        }
    }
    public void goToAccount(ActionEvent event) throws IOException {
        Account account = accountList.getSelectionModel().getSelectedItem();
        if (account == null){
            MessageDisplayer.displayErrorAlert("Error", "No account selected");
        }
        Customer customer = Main.getValvet().getCustomer(personalNumberLabel.getText().replace("Personal Number: ", ""));
        switcher.switchToAccountPage(event, account, customer);
    }

    public void goToMainMenu(ActionEvent event) throws IOException {
        switcher.switchToMain(event);
    }

    public void goToTransactionView(ActionEvent event) throws IOException {
        switcher.switchToTransactionPage(event);
    }

    public void goToWithdrawalView(ActionEvent event) {
    }

    public void goToDepositionView(ActionEvent event) {
    }

    public void deleteCustomer(ActionEvent event) {
    }
}
