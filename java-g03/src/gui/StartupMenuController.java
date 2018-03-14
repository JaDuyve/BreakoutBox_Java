package gui;

import com.jfoenix.controls.JFXButton;
import domein.BobController;
import domein.OefeningController;
import domein.SessieController;
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

    @FXML
    private JFXButton btnSesstieToevoegen;



    public StartupMenuController() {

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
        s.setRoot(new BobSchermController());
    }

    @FXML
    void showOefBeheren(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new OefeningSchermController());
    }

    @FXML
    void showSessieToevoegen(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new SessieSchermController());
    }

}
