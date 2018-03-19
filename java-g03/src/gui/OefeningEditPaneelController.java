package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import domein.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.eclipse.persistence.internal.oxm.schema.model.All;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OefeningEditPaneelController extends StackPane {


    @FXML
    private Label lblTitel;

    @FXML
    private Label lblNaam;

    @FXML
    private TextField txfNaam;

    @FXML
    private Label lblOpgave;

    @FXML
    private JFXButton btnOpgaveButton;

    @FXML
    private Label lblAntwoord;

    @FXML
    private TextField txtAntwoord;

    @FXML
    private Label lblFeedback;

    @FXML
    private JFXButton btnFeedbackButton;

    @FXML
    private Label lblGroepsbewerkingen;

    @FXML
    private ListView<Groepsbewerking> left;

    @FXML
    private JFXButton toRight;

    @FXML
    private JFXButton toLeft;

    @FXML
    private ListView<Groepsbewerking> right;

    @FXML
    private Label lblVak;

    @FXML
    private JFXComboBox<Vak> vakDropDown;

    @FXML
    private JFXButton btnVoegOefeningToe;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private AnchorPane apGroepsbewerking;

    @FXML
    private Label lblDoelstellingen;

    @FXML
    private AnchorPane apDoelstellingen;

    @FXML
    private Label lblTijdslimiet;

    @FXML
    private TextField txfTijdslimiet;

    private ListViewController<Groepsbewerking> lvGroepsbewerkingen;
    private ListViewController<Doelstellingscode> lvDoelstellingen;

    private OefeningController oefeningController;
    private File opgaveFile;
    private File feedbackFile;
    private Oefening oefening;

    public OefeningEditPaneelController(OefeningController dc) {
        this.oefeningController = dc;
        this.oefening = oefeningController.geefOefening();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OefeningMakenPaneel.fxml"));
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
        lblTitel.setText("Edit Oefening");
        btnVoegOefeningToe.setText("Wijzige");
        txfNaam.setText(oefening.getNaam());
        txtAntwoord.setText(oefening.getAntwoord());
        txfTijdslimiet.setText(Integer.toString(oefening.getTijdsLimiet()));
        vakDropDown.getSelectionModel().select(oefening.getVak());
        opgaveFile = oefeningController.geefFile(oefening.getOpgave());
        if (oefening.getFeedback() != null) {
            feedbackFile = oefeningController.geefFile(oefening.getFeedback());

        }
        lvGroepsbewerkingen = new ListViewController<>(oefeningController.geefGroepsbewerkingen(), oefening.getLijstGroepsbewerkingen());
        lvDoelstellingen = new ListViewController<>(oefeningController.geefDoelstelingscodes(), oefening.getDoelstellingscodes());

        vakDropDown.setItems(oefeningController.geefVakken());

        apGroepsbewerking.getChildren().add(lvGroepsbewerkingen);
        apDoelstellingen.getChildren().add(lvDoelstellingen);

    }

    @FXML
    void opgaveFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        File file = fc.showOpenDialog(null);
        if (file != null){
            opgaveFile = file;
        }

    }

    @FXML
    void feedbackFileChooser(ActionEvent event) {
        FileChooser fc2 = new FileChooser();
        fc2.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        File file = fc2.showOpenDialog(null);
        if (file != null){
            feedbackFile = file;
        }
    }

    @FXML
    void VoegOefeningToe(ActionEvent event) {

        String naam = txfNaam.getText();
        String antwoord = txtAntwoord.getText();
        List<Groepsbewerking> list = lvGroepsbewerkingen.getLijstRight();
        List<Doelstellingscode> listDoelstellingen = lvDoelstellingen.getLijstRight();
        Vak vak = vakDropDown.getSelectionModel().getSelectedItem();
        int tijdsLimiet = Integer.parseInt(txfTijdslimiet.getText());

        try {
            oefeningController.wijzigOefening(naam, opgaveFile, antwoord, feedbackFile, list, listDoelstellingen, vak, tijdsLimiet);
            Scene s = this.getScene();
            s.setRoot(new OefeningSchermController(oefeningController));
        } catch (IllegalArgumentException ex) {
            AlertBox.showAlertError("Fout wijzig Oefening", ex.getMessage(), (Stage) this.getScene().getWindow());
        }

    }

    @FXML
    void cancel(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new OefeningSchermController(oefeningController));
    }

}
