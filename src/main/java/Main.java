package src.main.java;


import com.github.javafaker.Faker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.java.model.Customer;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;
import javafx.application.Application;

import java.time.Instant;

public class Main extends Application {

    private static Valvet valv;

    public static void main(String[] args) throws Exception {
        valv = ValvetFileManager.loadBank();
        generateFakeCustomer();
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

    /*public static generatePersonalNumber(){
        String personalNumber = "";
        Random random = new Random();

    }*/
    public static String randomPersonalNumber() {
        Random random = new Random();
        String year = "" + random.nextInt(1930, 2010);
        String month = "" + random.nextInt(01,12);
        if(month.length()<2){
            month = "0" + month;
        }
        String day = "" + random.nextInt(01,28);
        if(day.length()<2){
            day = "0" + day;
        }
        String lastDigits = "" + random.nextInt(1000, 9999);

        String personalNumber = "" + year + month + day + lastDigits;
        System.out.println(personalNumber);

        return  personalNumber;
    }
    public static String randomFirstName(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        System.out.println(firstName);
        return firstName;
    }
    public static String randomLastName(){
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        System.out.println(lastName);
        return lastName;
    }

    public static Customer generateFakeCustomer() throws Exception {
        return getValvet().createCustomer(randomFirstName(),randomLastName(),randomPersonalNumber());
    }

    public static Valvet getValvet() {
        return valv;
    }

}



