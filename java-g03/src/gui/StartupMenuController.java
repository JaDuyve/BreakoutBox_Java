package gui;

import com.jfoenix.controls.JFXButton;
import domein.BobController;
import domein.DoelstellingscodeController;
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

    @FXML
    private JFXButton btnDoelstellingscodeToevoegen;

    @FXML
    private JFXButton btnVakken;

    private static VakkenSchermController vakkenSchermController;
    private static BobSchermController bobSchermController;
    private static OefeningSchermController oefeningSchermController;
    private static SessieSchermController sessieSchermController;
    private static DoelstellingscodeBeheerSchermController doelstellingscodeBeheerSchermController;

    public StartupMenuController() {

        vakkenSchermController = new VakkenSchermController();
        bobSchermController =new BobSchermController();
        oefeningSchermController =new OefeningSchermController();
        sessieSchermController = new SessieSchermController();
        doelstellingscodeBeheerSchermController =  new DoelstellingscodeBeheerSchermController();

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
        s.setRoot(bobSchermController);
    }

    @FXML
    void showOefBeheren(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(oefeningSchermController);
    }

    @FXML
    void showSessieToevoegen(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(sessieSchermController);
    }

    @FXML
    void showVakken(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(vakkenSchermController);
    }

    @FXML
    void showDoelstellingscodeToevoegen(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(doelstellingscodeBeheerSchermController);
    }



}
