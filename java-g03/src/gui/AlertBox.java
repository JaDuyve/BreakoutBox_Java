package gui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertBox {
    public static void showAlertError(String title, String description, Stage stage){
        Alert alert = new Alert(Alert.AlertType.ERROR, description);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.initOwner(stage);

        alert.show();
    }

}
