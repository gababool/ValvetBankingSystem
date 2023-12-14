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
    private FXMLLoader prepareScene(ActionEvent event, Parent root, String filePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath));
        root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ValvetFileManager.saveBank(Main.getValvet());
        return loader;
    }

    // Methods for switching to new scenes, based on what fxml file is put. Some methods also run load methods for
    // controllers to keep track of information to be displayed to the user, like customer data.

    public void switchToMain(ActionEvent event) throws IOException {
        String filePath = "/src/main/java/view/MainView.fxml";
        prepareScene(event,root, filePath);
    }

    public void switchToCustomerPage(ActionEvent event, Customer customer) throws IOException {
        String filePath = "/src/main/java/view/CustomerView.fxml";
        FXMLLoader loader = prepareScene(event, root, filePath);
        CustomerController customerController = loader.getController();
        customerController.loadCustomer(customer);
    }

    public void switchToTransactionPage(ActionEvent event) throws IOException {
        String filePath = "/src/main/java/view/TransactionView.fxml";
        prepareScene(event, root, filePath);
    }

    public void switchToTransactionPage(ActionEvent event, Customer customer, Account account) throws IOException {
        String filePath = "/src/main/java/view/TransactionView.fxml";
        FXMLLoader loader = prepareScene(event, root, filePath);

        TransactionController transactionController = loader.getController();
        transactionController.loadTransactionWithCustomer(customer, account);
    }

    public void switchToAccountPage(ActionEvent event, Account account, Customer lastCustomer) throws IOException {
        String filePath = "/src/main/java/view/AccountView.fxml";
        FXMLLoader loader = prepareScene(event, root, filePath);
        AccountController accountController = loader.getController();
        accountController.loadAccount(account, lastCustomer);
    }

    public void switchToAllCustomersView(ActionEvent event) throws IOException {
        String filePath = "/src/main/java/view/AllCustomersView.fxml";
        prepareScene(event, root, filePath);
    }

    public void switchToAllCustomersView(ActionEvent event, Customer customer) throws IOException {
        String filePath = "/src/main/java/view/AllCustomersView.fxml";
        FXMLLoader loader = prepareScene(event, root, filePath);
        AllCustomersController allCustomersController = loader.getController();
        allCustomersController.loadWithCustomer(customer);
    }

    public void switchToCreateCustomerView(ActionEvent event) throws IOException {
        String filePath = "/src/main/java/view/CreateCustomerView.fxml";
        prepareScene(event, root, filePath);
    }


}
