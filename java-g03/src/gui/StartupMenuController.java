package gui;

import com.jfoenix.controls.JFXButton;
import domein.BobController;
import domein.OefeningController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StartupMenuController extends BorderPane {

    @FXML
    private JFXButton btnBobB;

    @FXML
    private JFXButton btnOefB;

    private OefeningController oefeningController;
    private BobController bobController;

    public StartupMenuController(OefeningController oefeningController, BobController bobController) {
        this.oefeningController = oefeningController;
        this.bobController = bobController;
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("startupMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void showBobBeheren(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new BobSchermController(bobController));
    }

    @FXML
    void showOefBeheren(ActionEvent event) {
        Scene s = this.getScene();

        s.setRoot(new OefeningSchermController(oefeningController));
    }

}
