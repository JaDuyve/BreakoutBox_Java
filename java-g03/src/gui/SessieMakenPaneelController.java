package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import domein.Bob;
import domein.SessieController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneId;


public class SessieMakenPaneelController extends BorderPane {

    @FXML
    private Label lblSessieMaken;

    @FXML
    private Label lblNaam;

    @FXML
    private TextField txtNaam;

    @FXML
    private Label lblStartdatum;

    @FXML
    private JFXDatePicker dpStartdatum;

    @FXML
    private JFXCheckBox cbAfstandsleren;

    private SessieController sessieController;

    @FXML
    private Label lblGroepen;

    @FXML
    private JFXButton btnSelecteerGroepen;

    @FXML
    private Label lblBreakoutbox;

    @FXML
    private JFXComboBox<Bob> CBBob;

    private File groepFile;

    public SessieMakenPaneelController(SessieController sc) {
        this.sessieController = sc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessieMakenPaneel.fxml"));
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
        CBBob.setItems(sessieController.geefBobs());
    }

    @FXML
    void createSessie(ActionEvent event) {
        try {
            sessieController.create(txtNaam.getText(), Date.from(dpStartdatum.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), CBBob.getValue(), groepFile, cbAfstandsleren.isSelected());
            Scene s = this.getScene();
            s.setRoot(new SessieSchermController(sessieController));
        } catch (IllegalArgumentException ex) {
            AlertBox.showAlertError("Fout Sessie Toevoegen", ex.getMessage(), (Stage) this.getScene().getWindow());

        } catch (IllegalStateException ex) {
            AlertBox.showAlertError("Fout Excel document", ex.getMessage(), (Stage) this.getScene().getWindow());

        }
    }


    @FXML
    void keerTerug(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new SessieSchermController(sessieController));
    }

    @FXML
    void selecteerGroepen(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("EXCEL file", "*.xlsx"));
        groepFile = fc.showOpenDialog(null);
    }
}
