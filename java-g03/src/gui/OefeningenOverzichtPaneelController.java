package gui;

import com.jfoenix.controls.JFXButton;
import domein.NumerischeOefening;
import domein.Oefening;
import domein.OefeningBeheerder;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;


public class OefeningenOverzichtPaneelController extends AnchorPane{

    @FXML
    private Button backbutton;

    @FXML
    private JFXButton addExerciseButton;

    @FXML
    private TableView<Oefening> oefTable;

    @FXML
    private TableColumn<Oefening, String> categorieTable;

    @FXML
    private TableColumn<Oefening, String> nameTable;

    @FXML
    private GridPane toolGrid;

    private OefeningBeheerder oefeningBeheerder;

    public OefeningenOverzichtPaneelController(OefeningBeheerder oefeningBeheerder) {
        this.oefeningBeheerder = oefeningBeheerder;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OefeningenOverzichtPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        build();
    }

    private void build() {
        oefTable.setItems(oefeningBeheerder.geefOefeningen());

        categorieTable.setCellValueFactory(cel -> new ReadOnlyStringWrapper(cel.getValue().getNaam()));
        nameTable.setCellValueFactory(cel -> new ReadOnlyStringWrapper(cel.getValue().getNaam()));
        for (int i = 0; i < oefeningBeheerder.geefOefeningen().size(); i++) {
            Button copy = new Button("Copy");
            Button edit = new Button("Edit");
            Button delete = new Button("Delete");
            toolGrid.add(copy, 0, i + 1);
            toolGrid.add(edit, 1, i + 1);
            toolGrid.add(delete, 2, i + 1);
        }
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
        ButtonType maakButton = new ButtonType("Maak Oefening", ButtonBar.ButtonData.OK_DONE);
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
        /*dialog.setResultConverter(dialogButton -> {
            if (dialogButton == maakButton) {
                return new NumerischeOefening(naam.getText(), opgave.getText(), feedback.getText(), vak.getText()) {
                    @Override
                    public String getNaam() {
                        return super.getNaam();
                    }
                };
            }
            return null;
        });*/

        Optional<NumerischeOefening> result = dialog.showAndWait();
    }
}

