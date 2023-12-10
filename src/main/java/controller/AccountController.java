package src.main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;
import src.main.java.model.Transaction;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

public class AccountController {

    @FXML public Label totalBalanceLabel;
    @FXML private Label titleNameLabel;
    @FXML private Label personalNumberLabel;
    @FXML private Label accountNumberLabel;
    @FXML private TableView<Transaction> transactionsTable;
    @FXML private TableColumn<Transaction, String> amountColumn;
    @FXML private TableColumn<Transaction, String> senderColumn;
    @FXML private TableColumn<Transaction, String> receiverColumn;
    @FXML private TableColumn<Transaction, String> dateColumn;
    @FXML private TableColumn<Transaction, String> transactionIDColumn;

    private Account account;
    private Customer customer;
    private static SceneSwitcher switcher = new SceneSwitcher();

    public void loadAccount(Account account, Customer customer){

        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionAmount"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("senderAccountNumber"));
        receiverColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("receiverAccountNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionDate"));
        transactionIDColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionID"));

        this.account = account;
        this.customer = customer;

        titleNameLabel.setText("Customer Name: " + customer.getFullName());
        personalNumberLabel.setText("PNO: " + customer.getPERSONAL_NUMBER());
        accountNumberLabel.setText("Account Number: " + account.getAccountNumber());
        totalBalanceLabel.setText("Total Balance: " + account.getBalance() + " kr");


        ObservableList<Transaction> ObservableTransaction = FXCollections.observableArrayList();
        LinkedHashMap<UUID, Transaction> transactions = account.getTransactions();
        ObservableTransaction.addAll(transactions.values());

        transactionsTable.setItems(ObservableTransaction);
        transactionsTable.getSortOrder().add(dateColumn);

    }
    public void goToMainMenu(ActionEvent event) throws IOException {
        switcher.switchToMain(event);
    }
    public void goToCustomer(ActionEvent event) throws IOException{
        switcher.switchToCustomerPage(event, customer);
    }

}
