package src.main.java;


import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.java.model.Customer;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;
import javafx.application.Application;

public class Main extends Application {

    private static Valvet valv;

    public static void main(String[] args) throws Exception {
        // 1. Load Valvet, customers, accounts and transactions from data.json
        // Create a bank with customers, accounts, and transactions
        valv = new Valvet("1337");

        // Populate your bank with data...
        Customer martin = valv.createCustomer("Martin", "Lidgren", "123");
        valv.createAccount("123");

        // Save the bank data to a JSON file
        ValvetFileManager.saveBank(valv);

        // Load the bank data from the JSON file
        Valvet loadedBank = ValvetFileManager.loadBank();

        // Display the loaded bank data
        if (loadedBank != null) {
            System.out.println(loadedBank);

        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/view/MainView.fxml"));
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Valvet getValvet() {
        return valv;
    }
    }



