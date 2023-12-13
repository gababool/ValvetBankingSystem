package src.main.java.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class MessageDisplayer {

    // Methods to display different types of messages to the user as pop-ups, both with buttons and without.

    public static void displayMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void displayErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean displayConfirmationBox(String message) {
        boolean answer = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)){
            answer = true;
        }
        return answer;
    }

}
