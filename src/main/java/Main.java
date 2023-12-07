package src.main.java;


import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.java.controller.SceneSwitcher;
import src.main.java.model.Customer;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;
import javafx.application.Application;

public class Main extends Application {

    private static Valvet valv;
    // private static SceneSwitcher sceneSwitcher;
    //private static Valvet

    public static void main(String[] args) throws Exception {
        valv = ValvetFileManager.loadBank();
        // 1. Load Valvet, customers, accounts and transactions from data.json
        // Create a bank with customers, accounts, and transactions
        //valv = new Valvet("1337");
        //valv = ValvetFileManager.loadBank();
        // Populate your bank with data...
        /*
        Customer martin = valv.createCustomer("Martin", "Lidgren", "123");
        valv.createAccount("123");
        valv.makeDeposit("911", "1337-123-1", 500);
        valv.makeDeposit("911", "1337-123-2", 500);
        Customer edvin = valv.createCustomer("Edvin", "Sanfridsson", "124");
        valv.createAccount("124");
        valv.makeDeposit("911", "1337-124-1", 500);
        valv.makeDeposit("911", "1337-124-2", 500);
        Customer emma = valv.createCustomer("Emma", "Olmås", "125");
        valv.createAccount("125");
        valv.makeDeposit("911", "1337-125-1", 500);
        valv.makeDeposit("911", "1337-125-2", 500);
        Customer love = valv.createCustomer("Love", "Carlander", "126");
        valv.createAccount("126");
        valv.makeDeposit("911", "1337-126-1", 500);
        valv.makeDeposit("911", "1337-126-2", 500);
        Customer johannes = valv.createCustomer("Johannes", "Borg", "127");
        valv.createAccount("127");
        valv.makeDeposit("911", "1337-127-1", 500);
        valv.makeDeposit("911", "1337-127-2", 500);
        Customer daniel = valv.createCustomer("Daniel", "Norberg", "128");
        valv.createAccount("128");
        valv.makeDeposit("911", "1337-128-1", 500);
        valv.makeDeposit("911", "1337-128-2", 500);
        Customer gösta = valv.createCustomer("Gösta", "Olmås", "129");
        valv.createAccount("129");
        valv.makeDeposit("911", "1337-129-1", 500);
        valv.makeDeposit("911", "1337-129-2", 500);
        */

        // Save the bank data to a JSON file
        //ValvetFileManager.saveBank(valv);
        //valv = ValvetFileManager.loadBank();
        // Load the bank data from the JSON fil

        // Display the loaded bank data
        //if (loadedBank != null) {
        //    System.out.println(loadedBank);

        //}

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("V.A.L.V.E.T. Banking System");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Valvet getValvet() {
        return valv;
    }

}



