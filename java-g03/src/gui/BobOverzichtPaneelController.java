package gui;

import com.jfoenix.controls.JFXButton;
import domein.Bob;
import domein.BobController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.IOException;
import java.util.Optional;

public class BobOverzichtPaneelController extends AnchorPane {

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
        bobView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue, oldValue, newValue) -> {
                    bobController.veranderHuidigeOefening(newValue);
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
        alert.initOwner((Stage)this.getScene().getWindow());
        Scene s = this.getScene();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            try {
                bobController.verwijderBob();

            }catch(IllegalArgumentException ex){
                AlertBox.showAlertError("Fout delete bob", ex.getMessage(), (Stage)this.getScene().getWindow());
            }
            s.setRoot(new BobOverzichtPaneelController(bobController));
        }
        else {
            s.setRoot(new BobOverzichtPaneelController(bobController));
        }

    }

    @FXML
    void copyBob(ActionEvent event)
    {
        Scene s = this.getScene();
        s.setRoot(new BobKopiePaneelController(bobController, bobView.getSelectionModel().getSelectedItem()));
    }

}
