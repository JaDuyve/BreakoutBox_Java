package gui;

import com.jfoenix.controls.JFXTextField;
import domein.Oefening;
import domein.OefeningBeheerder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class OefeningenDetailPaneelController extends AnchorPane implements Observer {
    @FXML
    private JFXTextField txfOpgave;

    @FXML
    private JFXTextField txfAntwoord;

    @FXML
    private JFXTextField tfxFeedback;

    private OefeningBeheerder oefeningBeheerder;

    public OefeningenDetailPaneelController(OefeningBeheerder oefeningBeheerder) {
        this.oefeningBeheerder = oefeningBeheerder;

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
        Oefening oefening =  (Oefening)arg;

        txfOpgave.setText(oefening.getOpgave());
        txfAntwoord.setText("Dit moet nog geinstantieerd worden want het klopt nog niet");
        tfxFeedback.setText(oefening.getFeedback());
    }
}
