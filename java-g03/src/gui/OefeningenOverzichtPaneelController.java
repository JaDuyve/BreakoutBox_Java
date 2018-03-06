package gui;

import com.jfoenix.controls.JFXButton;
import domein.Oefening;
import domein.OefeningBeheerder;
import domein.OefeningController;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    private JFXButton btnCopy;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnDelete;


    private OefeningController oefeningController;
    private OefeningSchermController oefschcont;
    private OefeningenDetailPaneelController detailscherm;

    public OefeningenOverzichtPaneelController(OefeningController dc, OefeningSchermController oefschcont, OefeningenDetailPaneelController detailscherm) {
        this.oefeningController = dc;
        this.oefschcont = oefschcont;
        this.detailscherm = detailscherm;
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
        oefTable.setItems(oefeningController.geefOefeningen());
        categorieTable.setCellValueFactory(cel -> new ReadOnlyStringWrapper(cel.getValue().getVak().getNaam()));
        nameTable.setCellValueFactory(cel -> new ReadOnlyStringWrapper(cel.getValue().getNaam()));
        btnCopy = new JFXButton("Copy");
        btnEdit = new JFXButton("Edit");
        btnDelete = new JFXButton("Delete");
        btnDelete.setOnAction(this::deleteOefening);

        oefTable.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue, oldValue, newValue) -> {
                    oefeningController.veranderHuidigeOefening(newValue);
                });

        // TODO - BUG VAN EEN NIEUW SCHERM TE SETTEN
        if (oefschcont.getChildren().size() < 2) {
            oefTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    oefschcont.getChildren().add(detailscherm);
                    oefschcont.getScene().getWindow().sizeToScene();
                }
            });
        }
        oefTable.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                oefschcont.getChildren().set(2 , detailscherm);
            }
        });


    }



    @FXML
    void deleteOefening(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION, "verwijder oefening");
        alert.setTitle("Oefening verwijderen");
        alert.initOwner((Stage) this.getScene().getWindow());
        Scene s = this.getScene();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            oefeningController.verwijderOefening(oefTable.getSelectionModel().getSelectedItem());
            s.setRoot(new OefeningSchermController(oefeningController));

        }else
        {
            s.setRoot(new OefeningSchermController(oefeningController));
        }
    }


    @FXML
    void createOefening(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new OefeningMakenPaneelController(oefeningController));
    }

    private void maken(ActionEvent event)
    {

        Scene s = this.getScene();
        s.setRoot(new OefeningMakenPaneelController(oefeningController));
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

        Optional<Oefening> result = dialog.showAndWait();
    }


    @FXML
    void backButton(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new StartupMenuController(oefeningController));
    }
}

