package src.main.java.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;
import javafx.event.ActionEvent;
import src.main.java.model.ValvetFileManager;

import java.io.IOException;

public class SceneSwitcher {

    private Scene scene;
    private Stage stage;
    private Parent root;

    public SceneSwitcher(){}


    // Reusable method for preparing and showing a new scene.
    private void prepareScene(ActionEvent event, Parent root) {
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ValvetFileManager.saveBank(Main.getValvet());
    }

    // Methods for switching to new scenes, based on what fxml file is put. Some methods also run load methods for
    // controllers to keep track of information to be displayed to the user, like customer data.

    public void switchToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/MainView.fxml"));
        root = loader.load();

        prepareScene(event,root);
    }

    public void switchToCustomerPage(ActionEvent event, Customer customer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/CustomerView.fxml"));
        root = loader.load();

        CustomerController customerController = loader.getController();
        customerController.loadCustomer(customer);

        prepareScene(event, root);
    }

    public void switchToTransactionPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/TransactionView.fxml"));
        root = loader.load();

        prepareScene(event, root);
    }

    public void switchToTransactionPage(ActionEvent event, Customer customer, Account account) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/TransactionView.fxml"));
        root = loader.load();

        TransactionController transactionController = loader.getController();
        transactionController.loadTransactionWithCustomer(customer, account);

        prepareScene(event, root);
    }

    public void switchToAccountPage(ActionEvent event, Account account, Customer lastCustomer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/AccountView.fxml"));
        root = loader.load();

        AccountController accountController = loader.getController();
        accountController.loadAccount(account, lastCustomer);

        prepareScene(event, root);
    }

    public void switchToAllCustomersView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/AllCustomersView.fxml"));
        root = loader.load();

        prepareScene(event, root);
    }

    public void switchToAllCustomersView(ActionEvent event, Customer customer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/AllCustomersView.fxml"));
        root = loader.load();

        AllCustomersController allCustomersController = loader.getController();
        allCustomersController.loadWithCustomer(customer);

        prepareScene(event, root);
    }

    public void switchToCreateCustomerView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/CreateCustomerView.fxml"));
        root = loader.load();

        prepareScene(event, root);
    }


}
