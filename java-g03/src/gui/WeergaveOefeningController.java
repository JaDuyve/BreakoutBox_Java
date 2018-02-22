package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.Optional;

import domein.Oefening;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import javax.xml.soap.Text;

public class WeergaveOefeningController extends BorderPane {

    @FXML
    private JFXDrawer jfxDrawer;

    @FXML
    private JFXTextField txfSearch;

    @FXML
    private JFXListView<Oefening> listviewOefeningen;

    @FXML
    private JFXButton createButton;

    private DomeinController domeinController;

    public WeergaveOefeningController(DomeinController dc) {
        this.domeinController = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeergaveOefening.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }
        build();
    }
    private void build() {
        listviewOefeningen = new JFXListView<Oefening>();
        domeinController.GeefOefeningen().entrySet().stream().map(i -> listviewOefeningen.getItems().add((Oefening) i)).sorted();
        listviewOefeningen.getStyleClass().add("mylistview");
    }

    @FXML
    void createOefening(ActionEvent event) {
        createDialogBoxForInput();
    }

    private void createDialogBoxForInput() {
        // Create the custom dialog.
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Create Exercise Dialog");
        dialog.setHeaderText("Maak hier uw eigen oefening");

// Set the button types.
        ButtonType maakButton = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(maakButton, ButtonType.CANCEL);

// Create the naam and opgave labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField naam = new TextField();
        naam.setPromptText("Naam Oefening");
        TextField opgave = new TextField();
        opgave.setPromptText("Opgave");
        TextField feedback = new TextField();
        feedback.setPromptText("Feedback");
        TextField vak = new TextField();
        vak.setPromptText("Vak");

        grid.add(new Label("Naam:"), 0, 0);
        grid.add(naam, 1, 0);
        grid.add(new Label("Opgave:"), 0, 1);
        grid.add(opgave, 1, 1);
        grid.add(new Label("Feedback:"), 0, 2);
        grid.add(feedback, 1, 2);
        grid.add(new Label("Vak:"), 0, 3);
        grid.add(vak, 1, 3);

// Enable/Disable login button depending on whether a naam was entered.
        Node maakOefeningBtn = dialog.getDialogPane().lookupButton(maakButton);
        maakOefeningBtn.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        naam.textProperty().addListener((observable, oldValue, newValue) -> {
            maakOefeningBtn.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the naam field by default.
        Platform.runLater(() -> naam.requestFocus());

// Convert the result to a naam-opgave-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == maakButton) {
                return new Oefening(naam.getText(), opgave.getText(), feedback.getText(), vak.getText()) {
                    @Override
                    public String getNaam() {
                        return super.getNaam();
                    }
                };
            }
            return null;
        });

        Optional<Oefening> result = dialog.showAndWait();
    }
}
