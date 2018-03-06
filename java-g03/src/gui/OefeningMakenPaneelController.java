package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import domein.Groepsbewerking;
import domein.OefeningController;
import domein.Vak;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OefeningMakenPaneelController extends AnchorPane {


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

    private ObservableList<Groepsbewerking> lijstLeft;
    private ObservableList<Groepsbewerking> lijstRight;
    private OefeningController oefeningController;
    private File opgaveFile;
    private File feedbackFile;

    public OefeningMakenPaneelController(OefeningController dc) {
        this.oefeningController = dc;
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

    private void buildGui(){
        lijstLeft = oefeningController.geefGroepsbewerkingen();
        lijstRight = FXCollections.observableArrayList();
        left.setItems(lijstLeft);
        left.getSelectionModel().selectFirst();
        right.setItems(lijstRight);
        right.getSelectionModel().selectFirst();
        vakDropDown.setItems(oefeningController.geefVakken());
        toLeft.setDisable(true);

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
    void toLeft(ActionEvent event) {
        Groepsbewerking groep = right.getSelectionModel().getSelectedItem();
        if (groep != null){
            lijstLeft.add(groep);
            lijstRight.remove(groep);
            if (lijstRight.isEmpty())
                toLeft.setDisable(true);
            if (!lijstLeft.isEmpty())
                toRight.setDisable(false);
        }


    }

    @FXML
    void toRight(ActionEvent event) {
        Groepsbewerking groep = left.getSelectionModel().getSelectedItem();
        if (groep != null){
            lijstRight.add(groep);
            lijstLeft.remove(groep);
            if (lijstLeft.isEmpty())
                toRight.setDisable(true);
            if (!lijstRight.isEmpty())
                toLeft.setDisable(false);
        }

    }

    @FXML
    void VoegOefeningToe(ActionEvent event) {
        if (opgaveFile == null || feedbackFile == null){
            throw new IllegalArgumentException("Er is geen opgave of feedback geselecteerd.");
        }
        String naam = txfNaam.getText();
        String antwoord = txtAntwoord.getText();
        List<Groepsbewerking> list = new ArrayList<>();
        lijstRight.stream().forEach(g -> list.add(g));
        Vak vak = vakDropDown.getSelectionModel().getSelectedItem();

        oefeningController.createOefening(naam, opgaveFile, antwoord, feedbackFile, list, vak );
        Scene s = this.getScene();
        s.setRoot(new OefeningSchermController(oefeningController));
    }

    @FXML
    void canel(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new OefeningSchermController(oefeningController));
    }
}
