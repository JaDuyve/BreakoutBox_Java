package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domein.Doelstellingscode;
import domein.Groepsbewerking;
import domein.Oefening;
import domein.OefeningController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class OefeningenDetailPaneelController extends VBox implements Observer {
    @FXML
    private JFXTextField txfAntwoord;

    @FXML
    private JFXButton btnOpenOpgave;

    @FXML
    private Label fileLabelOpgave;

    @FXML
    private JFXButton openFeedback;

    @FXML
    private Label fileLabelFeedback;

    @FXML
    private JFXTextField txfVak;

    @FXML
    private Circle colorCircle;

    @FXML
    private ListView<Groepsbewerking> left;

    @FXML
    private ListView<Doelstellingscode> leftDoelstellingen;

    @FXML
    private TextField txfTijdslimiet;

    private OefeningController oefeningController;
    private Oefening oefening;

    public OefeningenDetailPaneelController(OefeningController dc) {
        this.oefeningController = dc;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("OefeningenDetailPaneel.fxml"));
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
        openFeedback.setDisable(true);
        btnOpenOpgave.setDisable(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            oefening = (Oefening) arg;
            colorCircle.setFill(Paint.valueOf(oefening.getVak().getKleur()));
            txfAntwoord.setText(oefening.getAntwoord());
            txfVak.setText(oefening.getVak().getNaam());
            txfTijdslimiet.setText(Integer.toString(oefening.getTijdsLimiet()));
            left.setItems(FXCollections.observableArrayList(oefening.getLijstGroepsbewerkingen()));
            leftDoelstellingen.setItems(FXCollections.observableArrayList(oefening.getDoelstellingscodes()));
            fileLabelOpgave.setText(oefening.getOpgave());
            btnOpenOpgave.setDisable(false);

            openFeedback.setDisable(false);
            fileLabelFeedback.setText(oefening.getFeedback());


        }
    }

    @FXML
    void openFeedback(ActionEvent event) {
        try {
            File file = oefeningController.geefFile(oefening.getFeedback());
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException ex) {
            AlertBox.showAlertError("Fout open feedback", ex.getMessage(), (Stage) this.getScene().getWindow());
        }
    }

    @FXML
    void openOpgave(ActionEvent event) {
        try {
            File file = oefeningController.geefFile(oefening.getOpgave());
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException ex) {
            AlertBox.showAlertError("Fout open opgave", ex.getMessage(), (Stage) this.getScene().getWindow());
        }

    }
}
