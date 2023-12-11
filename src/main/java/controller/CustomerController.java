package src.main.java.controller;

import javafx.fxml.FXML;
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

        LinkedHashMap<String, Account> Accounts = customer.getAccounts();
        for(Account account : Accounts.values()){
            this.accountList.getItems().add(account);
        }
    }
    public void goToAccount(ActionEvent event) throws IOException {
        Account account = this.accountList.getSelectionModel().getSelectedItem();
        Customer customer = Main.getValvet().getCustomer(personalNumberLabel.getText().replace("Personal Number: ", ""));
        switcher.switchToAccountPage(event, account, customer);
    }

    public void goToMainMenu(ActionEvent event) throws IOException {
        switcher.switchToMain(event);
    }

}
