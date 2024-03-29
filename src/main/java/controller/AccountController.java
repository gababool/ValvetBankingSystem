package src.main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;
import src.main.java.model.Transaction;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

public class AccountController {

    @FXML private Label totalBalanceLabel;
    @FXML private Label titleNameLabel;
    @FXML private Label personalNumberLabel;
    @FXML private Label accountNumberLabel;
    @FXML private TableView<Transaction> transactionsTable;
    @FXML private TableColumn<Transaction, String> amountColumn;
    @FXML private TableColumn<Transaction, String> senderColumn;
    @FXML private TableColumn<Transaction, String> receiverColumn;
    @FXML private TableColumn<Transaction, String> dateColumn;
    @FXML private TableColumn<Transaction, String> transactionIDColumn;

    private Button printButton;
    private Account account;
    private Customer customer;
    private static SceneSwitcher switcher = new SceneSwitcher();

    // Loads in all the account data that is displayed on the account page, including all its transactions.
    // Also displays information about the customer the account belongs to.
    public void loadAccount(Account account, Customer customer){

        amountColumn.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("senderAccountNumber"));
        receiverColumn.setCellValueFactory(new PropertyValueFactory<>("receiverAccountNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        transactionIDColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));

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

    // Switches screen to the main menu
    public void goToMainMenu(ActionEvent event){
        try {
            switcher.switchToMain(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Switches screen to the customer page of the account owner.
    public void goToCustomer(ActionEvent event) {
        try {
            switcher.switchToCustomerPage(event, customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Opens the window for physically (or PDF) printing out all transactions for the account.
    public void printAction(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if(printerJob.showPrintDialog(stage.getOwner()) && printerJob.printPage(transactionsTable))
            printerJob.endJob();
    }


}
