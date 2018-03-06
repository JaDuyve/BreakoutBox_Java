package gui;

import com.jfoenix.controls.JFXButton;
import domein.Bob;
import domein.BobController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Observer;

public class BobOverzichtPaneelController extends AnchorPane {

    BobController bobController;

    @FXML
    private Label lblBobs;

    @FXML
    private TableView<Bob> bobTable;

    @FXML
    private TableColumn<Bob, String> nameTable;

    @FXML
    private TableColumn<Bob, String> categorieTable;

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
    }

    @FXML
    void createBob(ActionEvent event) {

    }

    @FXML
    void deleteBob(ActionEvent event) {

    }

}
