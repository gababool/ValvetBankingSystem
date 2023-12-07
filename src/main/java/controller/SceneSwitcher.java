package src.main.java.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import src.main.java.model.Account;
import src.main.java.model.Customer;

import javafx.event.ActionEvent;
import java.io.IOException;

public class SceneSwitcher {
    //private Stage stage;

    private Scene scene;
    private Stage stage;
    private Parent root;

    public SceneSwitcher(){}

    public void switchToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/MainView.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCustomerPage(ActionEvent event, Customer customer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/CustomerView.fxml"));
        Parent root = loader.load();

        CustomerController customerController = loader.getController();
        customerController.loadCustomer(customer);

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToTransactionPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/TransactionView.fxml"));
        Parent root = loader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToAccountPage(ActionEvent event, Account account, Customer lastCustomer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/AccountView.fxml"));
        Parent root = loader.load();

        AccountController accountController = loader.getController();
        accountController.loadAccount(account, lastCustomer);

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
