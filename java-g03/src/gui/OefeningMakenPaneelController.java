package gui;

import com.jfoenix.controls.JFXComboBox;
import domein.Groepsbewerking;
import domein.OefeningController;
import domein.Vak;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
    private Button opgaveButton;

    @FXML
    private Label lblAntwoord;

    @FXML
    private TextField txtAntwoord;

    @FXML
    private Label lblFeedback;

    @FXML
    private Button feedbackButton;

    @FXML
    private Label lblGroepsbewerkingen;

    @FXML
    private ListView<Groepsbewerking> left;

    @FXML
    private Button toRight;

    @FXML
    private Button toLeft;

    @FXML
    private ListView<Groepsbewerking> right;

    @FXML
    private Label lblVak;

    @FXML
    private JFXComboBox<Vak> vakDropDown;

    @FXML
    private Button voegOefeningToe;

    private ObservableList<Groepsbewerking> lijstLeft;
    private ObservableList<Groepsbewerking> lijstRight;
    private OefeningController oefeningController;
    private String opgavePath;
    private String feedbackPath;

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
        lijstRight = FXCollections.observableList(new ArrayList<>());
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
        File f = fc.showOpenDialog(null);

        if (f != null)
        {
            this.opgavePath = f.getAbsolutePath();
        }
    }

    @FXML
    void feedbackFileChooser(ActionEvent event){
        FileChooser fc2 = new FileChooser();
        fc2.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        File f = fc2.showOpenDialog(null);

        if (f != null)
        {
            this.feedbackPath = f.getAbsolutePath();
        }
    }

    @FXML
    void toLeft(ActionEvent event) {
        Groepsbewerking groep = right.getSelectionModel().getSelectedItem();
        lijstLeft.add(groep);
        lijstRight.remove(groep);
        if (lijstRight.isEmpty())
            toLeft.setDisable(true);
        if (!lijstLeft.isEmpty())
            toRight.setDisable(false);

    }

    @FXML
    void toRight(ActionEvent event) {
        Groepsbewerking groep = left.getSelectionModel().getSelectedItem();
        lijstRight.add(groep);
        lijstLeft.remove(groep);
        if (lijstLeft.isEmpty())
            toRight.setDisable(true);
        if (!lijstRight.isEmpty())
            toLeft.setDisable(false);
    }

    @FXML
    void VoegOefeningToe(ActionEvent event) {

        oefeningController.createOefening(txfNaam.toString(), opgavePath, txtAntwoord.toString(), feedbackPath, lijstRight.stream().collect(Collectors.toList()), vakDropDown.getSelectionModel().getSelectedItem() );
    }
}
