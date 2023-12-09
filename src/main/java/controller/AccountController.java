package src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;
import src.main.java.model.Transaction;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

public class AccountController {

    public Label totalBalanceLabel;
    @FXML
    private Label titleNameLabel;
    @FXML
    private Label personalNumberLabel;

    @FXML
    private ListView<Transaction> transactionList;
    @FXML
    private Account account;

    private Customer customer;
    private static SceneSwitcher switcher = new SceneSwitcher();
    public void loadAccount(Account account, Customer customer){
        this.titleNameLabel.setText("Name: " + customer.getFullName());
        this.personalNumberLabel.setText("Personal Number: " + customer.getPERSONAL_NUMBER());
        this.totalBalanceLabel.setText("Total Balance " + account.getBalance());
        this.customer = customer;
        this.account = account;
        LinkedHashMap<UUID, Transaction> transactions = account.getTransactions();
        for(Transaction transaction : transactions.values()){
            transactionList.getItems().add(transaction);
        }
    }
    public void goToMainMenu(ActionEvent event) throws IOException {
        switcher.switchToMain(event);
    }
    public void goToCustomer(ActionEvent event) throws IOException{
        switcher.switchToCustomerPage(event, customer);
    }

}
