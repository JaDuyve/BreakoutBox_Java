package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domein.Groepsbewerking;
import domein.Oefening;
import domein.OefeningController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class OefeningenDetailPaneelController extends AnchorPane implements Observer {
    @FXML
    private JFXTextField txfAntwoord;

    @FXML
    private JFXButton openFile;

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
    }

    @Override
    public void update(Observable o, Object arg) {
        oefening =  (Oefening)arg;
        colorCircle.setFill(Paint.valueOf(oefening.getVak().getKleur()));
        txfAntwoord.setText(oefening.getAntwoord());
        txfVak.setText(oefening.getVak().getNaam());
        left.setItems(oefeningController.geefGroepsbewerkingen());
        fileLabelOpgave.setText(oefening.getOpgave());
        fileLabelFeedback.setText(oefening.getFeedback());
    }

    @FXML
    void openFeedback(ActionEvent event) {
        oefeningController.geefFile(oefening.getFeedback());
    }

    @FXML
    void openOpgave(ActionEvent event) {
        oefeningController.geefFile(oefening.getOpgave());
    }
}
