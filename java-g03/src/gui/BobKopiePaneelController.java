package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domein.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BobKopiePaneelController extends StackPane {

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
    private ListViewController<Toegangscode> lvToegangscodes;


    private BobController bobController;
    private Bob bob;


    public BobKopiePaneelController(BobController bobController, Bob bobke) {
        this.bobController = bobController;
        this.bob = bobke;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("BobKopiePaneel.fxml"));
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

        try {
            lvOefeningen = new ListViewController<>(bobController.geefOefeningen(), FXCollections.observableArrayList(bob.getLijstOefeningen()));
            lvActies = new ListViewController<>(bobController.geefActies(), FXCollections.observableArrayList(bob.getLijstActies()));
        } catch (IllegalArgumentException e) {
            AlertBox.showAlertError("Toevoegen breakout box", e.getMessage(), (Stage) this.getScene().getWindow());
        }
        apOefeningen.getChildren().add(lvOefeningen);
        apActies.getChildren().add(lvActies);
        txfNaam.setText(bob.getNaam());
    }

    @FXML
    void voegBobToe(ActionEvent event) {
        try {
            bobController.createBob(txfNaam.getText(), lvOefeningen.getLijstRight(), lvActies.getLijstRight());
            Scene s = this.getScene();
            s.setRoot(new BobSchermController(bobController));
        } catch (IllegalArgumentException e) {
            AlertBox.showAlertError("breakout-Box Kopieren", e.getMessage(), (Stage) this.getScene().getWindow());
        }


    }

    @FXML
    void cancel(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new BobSchermController(bobController));
    }
}
