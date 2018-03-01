package gui;

import domein.OefeningBeheerder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OefeningMakenPaneelController extends AnchorPane {

    @FXML
    private Label lblTitel;

    @FXML
    private Label lblNaam;

    @FXML
    private TextField txfNaam;

    private OefeningBeheerder oefeningBeheerder;

    public OefeningMakenPaneelController(OefeningBeheerder oefeningBeheerder) {
        this.oefeningBeheerder = oefeningBeheerder;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OefeningMakenPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
