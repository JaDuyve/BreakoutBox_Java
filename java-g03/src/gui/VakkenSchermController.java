

package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import domein.Vak;
import domein.VakController;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class VakkenSchermController extends BorderPane implements Observer {
    @FXML
    private Button backbutton;

    @FXML
    private JFXTextField txfZoekNaam;

    @FXML
    private JFXListView<Vak> lvVakken;

    @FXML
    private JFXTextField txfToevoegen;

    @FXML
    private JFXButton btnVak;

    @FXML
    private JFXButton btnVerwijder;

    private VakController vakController;

    public VakkenSchermController() {

        vakController = new VakController();

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("VakkenScherm.fxml"));
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
        vakController.addObserver(this);
        this.setTop(new TopBarController());
        lvVakken.setItems(vakController.geefVakken());

        if (lvVakken.getItems().isEmpty()) {
            lvVakken.getStyleClass().add("emptyvak");
            lvVakken.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
        }

        lvVakken.getItems().addListener(new ListChangeListener<Vak>() {
            @Override
            public void onChanged(Change<? extends Vak> c) {
                if (lvVakken.getItems().isEmpty()) {
                    lvVakken.getStyleClass().add("emptyvak");
                    lvVakken.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
                }
                else {
                    lvVakken.getStyleClass().remove("emptyvak");
                    lvVakken.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
                }
            }
        });
        lvVakken.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue, oldValue, newValue) -> {
                    vakController.veranderHuidige(newValue);
                }
        );

        btnVerwijder.setDisable(true);
    }

    @FXML
    void backButton(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new StartupMenuController());
    }

    @FXML
    void createVak(ActionEvent event) {
        try {
            vakController.createVak(txfToevoegen.getText());
            lvVakken.setItems(vakController.geefVakken());
            txfToevoegen.setText("");
        } catch (IllegalArgumentException ex) {
            AlertBox.showAlertError("Fout vak toevoegen", ex.getMessage(), (Stage) this.getScene().getWindow());
        }
    }

    @FXML
    void verwijderVak(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wilt u de geselecteerde vak wilt verwijderen?");
        alert.setTitle("Vak verwijderen");
        alert.initOwner((Stage) this.getScene().getWindow());


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            try {
                vakController.verwijderVak();
                lvVakken.setItems(vakController.geefVakken());
            } catch (IllegalArgumentException ex) {
                AlertBox.showAlertError("Fout verwijder vak", ex.getMessage(), (Stage) this.getScene().getWindow());
            }
        }
    }

    @FXML
    void zoekNaam(KeyEvent event) {
        vakController.changeFilter(txfZoekNaam.getText());
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean isnull = arg == null;
        btnVerwijder.setDisable(isnull);
    }
}
