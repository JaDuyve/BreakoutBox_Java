package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domein.Actie;
import domein.Bob;
import domein.BobController;
import domein.Oefening;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BobEditPaneelController extends BorderPane {

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

    @FXML
    private Label lblTitel;

    private ListViewController<Oefening> lvOefeningen;
    private ListViewController<Actie> lvActies;
    private Bob bob;

    private BobController bobController;

    public BobEditPaneelController(BobController bobController) {
        this.bobController = bobController;
        this.bob = bobController.geefBob();

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
        this.setTop(new TopBarController());
        lblTitel.setText("BREAKOUT-BOX WIJZIGEN");
        btnVoegOefeningToe.setText("Wijzig");
        txfNaam.setText(bob.getNaam());
        lvOefeningen = new ListViewController<>(bobController.geefOefeningen(), bob.getLijstOefeningen());
        lvActies = new ListViewController<>(bobController.geefActies(), bob.getLijstActies());

        apOefeningen.getChildren().add(lvOefeningen);
        apActies.getChildren().add(lvActies);


    }

    @FXML
    void voegBobToe(ActionEvent event) {
        try {
            bobController.wijzigBob(txfNaam.getText(), lvOefeningen.getLijstRight(), lvActies.getLijstRight());
            Scene s = this.getScene();
            s.setRoot(new BobSchermController(bobController));
        } catch (IllegalArgumentException e) {
            AlertBox.showAlertError("Wijzigen breakout box", e.getMessage(), (Stage) this.getScene().getWindow());
        }


    }

    @FXML
    void cancel(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new BobSchermController(bobController));
    }
}
