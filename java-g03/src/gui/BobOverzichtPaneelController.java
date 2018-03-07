package gui;

import com.jfoenix.controls.JFXButton;
import domein.Bob;
import domein.BobController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
    }

    @FXML
    void createBob(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new BobMakenPaneelController(bobController));
    }

    @FXML
    void deleteBob(ActionEvent event) {

    }

}
