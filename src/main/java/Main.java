package src.main.java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;
import javafx.application.Application;

public class Main extends Application {

    private static Valvet valv;

    public static void main(String[] args) {
        valv = ValvetFileManager.loadBank();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/main/java/view/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setTitle("V.A.L.V.E.T. Banking System");
            stage.getIcons().add(new Image("/src/main/java/view/Images/icon.png"));
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


    public static Valvet getValvet() {
        return valv;
    }

}



