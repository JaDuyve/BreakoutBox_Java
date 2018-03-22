package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domein.Bob;
import domein.BobController;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class BobOverzichtPaneelController extends VBox implements Observer {

    private BobController bobController;

    @FXML
    private Label lblBobs;

    @FXML
    private ListView<Bob> bobView;

    @FXML
    private JFXButton addBobButton;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnCopy;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXTextField txfZoekNaam;

    public BobOverzichtPaneelController(BobController dc) {
        this.bobController = dc;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("BobOverzichtPaneel.fxml"));
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
        bobView.setItems(bobController.geefBobs());
        btnCopy.setDisable(true);
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);

        if (bobView.getItems().isEmpty()) {
            bobView.getStyleClass().add("emptybob");
            bobView.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
        }

        bobView.getItems().addListener(new ListChangeListener<Bob>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Bob> c) {
                if (bobView.getItems().isEmpty()) {
                    bobView.getStyleClass().add("emptybob");
                    bobView.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
                }
                else {
                    bobView.getStyleClass().clear();
                    bobView.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
                }
            }
        });
        bobView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue, oldValue, newValue) -> {
                    bobController.veranderHuidigeBob(newValue);
                });

    }

    @FXML
    void createBob(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new BobMakenPaneelController(bobController));
    }

    @FXML
    void deleteBob(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Wilt u de geselecteerde BOB verwijderen?");
        alert.setTitle("BOB verwijderen");
        alert.initOwner((Stage) this.getScene().getWindow());
        Scene s = this.getScene();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            try {
                bobController.verwijderBob();
                s.setRoot(new BobSchermController());

            } catch (IllegalArgumentException ex) {
                AlertBox.showAlertError("Fout delete bob", ex.getMessage(), (Stage) this.getScene().getWindow());
            }
        }

    }

    @FXML
    void copyBob(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new BobKopiePaneelController(bobController, bobView.getSelectionModel().getSelectedItem()));
    }

    @FXML
    void backButton(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new StartupMenuController());
    }

    @FXML
    void onEdit(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new BobEditPaneelController(bobController));
    }

    @FXML
    void zoekNaam(KeyEvent event) {
        bobController.changeFilter(txfZoekNaam.getText());
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean isnull = arg == null;
        btnCopy.setDisable(isnull);
        btnDelete.setDisable(isnull);
        btnEdit.setDisable(isnull);

    }
}
