package src.main.java;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.main.java.model.Valvet;
import src.main.java.model.ValvetFileManager;
import javafx.application.Application;

import java.time.Instant;

public class Main extends Application {

    private static Valvet valv;

    public static void main(String[] args) throws Exception {
        valv = ValvetFileManager.loadBank();
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



