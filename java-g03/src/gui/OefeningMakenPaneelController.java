package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import domein.Doelstellingscode;
import domein.Groepsbewerking;
import domein.OefeningController;
import domein.Vak;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OefeningMakenPaneelController extends BorderPane {

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
    private ImageView img;

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

    private ListViewController<Groepsbewerking> lvGroepsbewerking;
    private ListViewController<Doelstellingscode> lvDoelstellingen;

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

    private void buildGui() {
        this.setTop(new TopBarController());
        lvGroepsbewerking = new ListViewController<>(oefeningController.geefGroepsbewerkingen(), FXCollections.observableArrayList());
        lvDoelstellingen = new ListViewController<>(oefeningController.geefDoelstelingscodes(), FXCollections.observableArrayList());
        vakDropDown.setItems(oefeningController.geefVakken());
        lvDoelstellingen.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        lvGroepsbewerking.setPrefSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
        apGroepsbewerking.getChildren().add(lvGroepsbewerking);
        apDoelstellingen.getChildren().add(lvDoelstellingen);

        txtAntwoord.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtAntwoord.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void opgaveFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        opgaveFile = fc.showOpenDialog(null);


    }

    @FXML
    void feedbackFileChooser(ActionEvent event) {
        FileChooser fc2 = new FileChooser();
        fc2.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        feedbackFile = fc2.showOpenDialog(null);


    }


    @FXML
    void VoegOefeningToe(ActionEvent event) {

        String naam = txfNaam.getText();
        String antwoord = txtAntwoord.getText();
        List<Groepsbewerking> list = lvGroepsbewerking.getLijstRight();
        List<Doelstellingscode> listDoelstellingen = lvDoelstellingen.getLijstRight();
        Vak vak = vakDropDown.getSelectionModel().getSelectedItem();
        //int tijdLimiet = 0;
        try {
            int tijdLimiet = Integer.parseInt(txfTijdslimiet.getText());
            oefeningController.createOefening(naam, opgaveFile, antwoord, feedbackFile, list, listDoelstellingen, vak, tijdLimiet);
            Scene s = this.getScene();
            s.setRoot(new OefeningSchermController());
        }catch (NumberFormatException ex)
        {
            AlertBox.showAlertError("Fout oefening toevoegen", "Tijdslimiet moet ingevuld zijn of moet een nummer zijn", (Stage) this.getScene().getWindow());
        } catch (IllegalArgumentException ex) {
            AlertBox.showAlertError("Fout Oefening Toevoegen", ex.getMessage(), (Stage) this.getScene().getWindow());
        }





    }

    @FXML
    void cancel(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new OefeningSchermController(oefeningController));
    }
}
