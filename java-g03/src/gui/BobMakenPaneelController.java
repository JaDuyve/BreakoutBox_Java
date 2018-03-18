package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domein.Actie;
import domein.BobController;
import domein.Oefening;
import domein.Toegangscode;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BobMakenPaneelController extends StackPane {

    @FXML
    private JFXTextField txfNaam;

    @FXML
    private AnchorPane apOefeningen;

    @FXML
    private AnchorPane apActies;

    @FXML
    private JFXButton btnVoegOefeningToe;

    @FXML
    private JFXButton btnCancel;

    private ListViewController<Oefening> lvOefeningen;
    private ListViewController<Actie> lvActies;

    private BobController bobController;

    public BobMakenPaneelController(BobController bobController) {
        this.bobController = bobController;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("BobMakenPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        buildGui();
    }

    private void buildGui() {
        lvOefeningen = new ListViewController<>(bobController.geefOefeningen(), FXCollections.observableArrayList());
        lvActies = new ListViewController<>(bobController.geefActies(), FXCollections.observableArrayList());
        apOefeningen.getChildren().add(lvOefeningen);
        apActies.getChildren().add(lvActies);
    }

    @FXML
    void voegBobToe(ActionEvent event) {
        try {
            bobController.createBob(txfNaam.getText(), lvOefeningen.getLijstRight(), lvActies.getLijstRight());

        } catch (IllegalArgumentException e) {
            AlertBox.showAlertError("Toevoegen breakout box", e.getMessage(), (Stage) this.getScene().getWindow());
        }

        Scene s = this.getScene();
        s.setRoot(new BobSchermController(bobController));
    }

    @FXML
    void cancel(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new BobSchermController(bobController));
    }
}
