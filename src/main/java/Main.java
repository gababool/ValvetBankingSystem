package src.main.java;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;
import javafx.application.Application;

public class Main extends Application {

    public static void main(String[] args) throws Exception {
        // 1. Load Valvet, customers, accounts and transactions from data.json
        // Create a bank with customers, accounts, and transactions
        Valvet valv = new Valvet("1337");

        // Populate your bank with data...
        // MAIN MENU HERE

        // Save the bank data to a JSON file
        ValvetFileManager.saveBank(valv);

        // Load the bank data from the JSON file
        Valvet loadedBank = ValvetFileManager.loadBank();

        // Display the loaded bank data
        if (loadedBank != null) {
            System.out.println(loadedBank);

        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

