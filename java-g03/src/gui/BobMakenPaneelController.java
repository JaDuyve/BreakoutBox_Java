package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import domein.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BobMakenPaneelController extends AnchorPane {

    @FXML
    private JFXTextField txfNaam;

    @FXML
    private AnchorPane apOefeningen;

    @FXML
    private AnchorPane apActies;

    @FXML
    private AnchorPane apToegangscodes;

    @FXML
    private JFXComboBox<Vak> vakDropDown;

    @FXML
    private JFXButton btnVoegOefeningToe;

    @FXML
    private JFXButton btnCancel;

    private ListViewController<Oefening> lvOefeningen;
    private ListViewController<Actie> lvActies;
    private ListViewController<Toegangscode> lvToegangscodes;

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
        try {
            lvOefeningen = new ListViewController<>(bobController.geefOefeningen(), FXCollections.observableArrayList());
            lvActies = new ListViewController<>(bobController.geefActies(), FXCollections.observableArrayList());
            lvToegangscodes = new ListViewController<>(bobController.geefToegangsCodes(), FXCollections.observableArrayList());
        } catch (IllegalArgumentException e) {
            AlertBox.showAlertError("Toevoegen breakout box", e.getMessage(), (Stage) this.getScene().getWindow());
        }
        apOefeningen.getChildren().add(lvOefeningen);
        apActies.getChildren().add(lvActies);
        apToegangscodes.getChildren().add(lvToegangscodes);
    }

    @FXML
    void voegBobToe(ActionEvent event) {
        try {
            bobController.createBob(txfNaam.getText(), lvOefeningen.getLijstRight(), lvActies.getLijstRight(), lvToegangscodes.getLijstRight());

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
