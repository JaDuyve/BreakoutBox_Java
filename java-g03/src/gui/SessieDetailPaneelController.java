package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import domein.Groep;
import domein.Sessie;
import domein.SessieController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.ZoneId;
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
        sessie.generateSessieOverzichtPdf();
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

        btnGenerateSessieOverzicht.setDisable(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        sessie = (Sessie) arg;
        boolean isnull = sessie == null;
        if (!isnull){

            txfNaam.setText(sessie.getNaam());
            txfBob.setText(sessie.getBob().toString());
            txfCode.setText(Integer.toString(sessie.getCode()));
            lvGroepen.setItems(FXCollections.observableList(sessie.getGroepen()));
            btnContact.setSelected(sessie.isContactLeer());
            jfxDate.setValue(sessie.getStartDatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        }

        btnGenerateSessieOverzicht.setDisable(isnull);

    }

    @FXML
    void disableEditable(MouseEvent event) {
        if (sessie == null){
            btnContact.setSelected(false);

        } else {
            btnContact.setSelected(sessie.isContactLeer());
        }
    }

    @FXML
    void disableEditableDate(InputMethodEvent event) {
        if (sessie == null){
            //jfxDate.;
        } else {
            jfxDate.setValue(sessie.getStartDatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }
}

