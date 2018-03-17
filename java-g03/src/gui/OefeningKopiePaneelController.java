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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OefeningKopiePaneelController extends StackPane {


    @FXML
    private Label lblTitel;

    @FXML
    private Label lblNaam;

    @FXML
    private JFXTextField txfNaam;

    @FXML
    private Label lblOpgave;

    @FXML
    private JFXButton btnOpgaveButton;

    @FXML
    private Label lblAntwoord;

    @FXML
    private JFXTextField txtAntwoord;

    @FXML
    private Label lblFeedback;

    @FXML
    private JFXButton btnFeedbackButton;

    @FXML
    private Label lblGroepsbewerkingen;

    @FXML
    private Label lblVak;

    @FXML
    private JFXComboBox<Vak> vakDropDown;

    @FXML
    private JFXButton btnVoegOefeningToe;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnOpgave;

    @FXML
    private JFXButton btnFeedback;

    @FXML
    private AnchorPane apGroepsbewerking;

    @FXML
    private Label lblDoelstellingen;

    @FXML
    private AnchorPane apDoelstellingen;

    private OefeningController oefeningController;
    private File opgaveFile;
    private File feedbackFile;
    private Oefening oefening;

    private ListViewController<Groepsbewerking> lvGroepsbewerking;
    private ListViewController<Doelstellingscode> lvDoelstellingen;

    public OefeningKopiePaneelController(OefeningController dc, Oefening oef) {
        this.oefeningController = dc;
        this.oefening = oef;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OefeningKopiePaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        buildGui();
    }

    private void buildGui(){
        try {
            lvGroepsbewerking = new ListViewController<>(oefeningController.geefGroepsbewerkingen(), FXCollections.observableArrayList(oefening.getLijstGroepsbewerkingen()));
            lvDoelstellingen = new ListViewController<>(oefeningController.geefDoelstelingscodes(),FXCollections.observableArrayList());

        } catch (IllegalArgumentException e) {
            AlertBox.showAlertError("Toevoegen breakout box", e.getMessage(), (Stage) this.getScene().getWindow());
        }
        apGroepsbewerking.getChildren().add(lvGroepsbewerking);
        apDoelstellingen.getChildren().add(lvDoelstellingen);
        vakDropDown.setItems(oefeningController.geefVakken());
        txfNaam.setText(oefening.getNaam());
        txtAntwoord.setText(oefening.getAntwoord());
        vakDropDown.getSelectionModel().select(oefening.getVak());
        opgaveFile = oefeningController.geefFile(oefening.getOpgave());
        if (oefening.getFeedback() != null){
            feedbackFile = oefeningController.geefFile(oefening.getFeedback());

        }
    }

    @FXML
    void opgaveFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        opgaveFile = fc.showOpenDialog(null);


    }

    @FXML
    void feedbackFileChooser(ActionEvent event){
        FileChooser fc2 = new FileChooser();
        fc2.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        feedbackFile = fc2.showOpenDialog(null);


    }


    @FXML
    void VoegOefeningToe(ActionEvent event) {
        if (opgaveFile == null){
            throw new IllegalArgumentException("Er is geen opgave of feedback geselecteerd.");
        }
        String naam = txfNaam.getText();
        String antwoord = txtAntwoord.getText();
        List<Groepsbewerking> list = new ArrayList<>();
        List<Doelstellingscode> listDoelstellingen = lvDoelstellingen.getLijstRight();
        lvGroepsbewerking.getLijstRight().stream().forEach(g -> list.add(g));
        lvDoelstellingen.getLijstRight().stream().forEach(d -> listDoelstellingen.add(d));
        Vak vak = vakDropDown.getSelectionModel().getSelectedItem();

        oefeningController.createOefening(naam, opgaveFile, antwoord, feedbackFile, list, listDoelstellingen,vak );
        Scene s = this.getScene();
        s.setRoot(new OefeningSchermController(oefeningController));
    }

    @FXML
    void canel(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new OefeningSchermController(oefeningController));
    }

    @FXML
    void toonFeedback(ActionEvent event) {
        try {
            Desktop.getDesktop().open(feedbackFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toonOpgave(ActionEvent event) {
        try {
            Desktop.getDesktop().open(opgaveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
