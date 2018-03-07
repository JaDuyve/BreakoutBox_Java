package gui;

import com.jfoenix.controls.JFXButton;
import domein.Oefening;
import domein.OefeningController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class OefeningenOverzichtPaneelController extends AnchorPane {

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

    }

    @FXML
    void deleteOefening(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION, "Wilt u de geselecteerde oefening verwijderen?");
        alert.setTitle("Oefening verwijderen");
        alert.initOwner((Stage) this.getScene().getWindow());
        Scene s = this.getScene();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            oefeningController.verwijderOefening();
            s.setRoot(new OefeningSchermController(oefeningController));

        } else {
            s.setRoot(new OefeningSchermController(oefeningController));
        }
    }


    @FXML
    void createOefening(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new OefeningMakenPaneelController(oefeningController));
    }

    @FXML
    void backButton(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new StartupMenuController(oefeningController));
    }

    @FXML
    void kopieerOefening(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new OefeningKopiePaneelController(oefeningController, oefTable.getSelectionModel().getSelectedItem()));
    }
}

