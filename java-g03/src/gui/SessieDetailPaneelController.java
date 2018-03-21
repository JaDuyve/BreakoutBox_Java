package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import domein.Groep;
import domein.Sessie;
import domein.SessieController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class SessieDetailPaneelController extends VBox implements Observer{

    @FXML
    private JFXTextField txfNaam;

    @FXML
    private JFXDatePicker jfxDate;
    @FXML
    private JFXCheckBox btnContact;
    @FXML
    private ListView<Groep> lvGroepen;

    @FXML
    private JFXTextField txfBob;

    @FXML
    private JFXTextField txfCode;

    @FXML
    private JFXButton btnGenerateSessieOverzicht;

    @FXML
    void generateSessieOverzicht(ActionEvent event) {

    }

    private SessieController sessieController;
    private Sessie sessie;

    public SessieDetailPaneelController(SessieController sessieController) {
        this.sessieController = sessieController;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("SessieDetailPaneel.fxml"));
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

    }
}

