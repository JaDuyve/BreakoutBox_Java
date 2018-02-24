package gui;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class OefeningenFilterPaneelController extends AnchorPane {

    @FXML
    private JFXTextField filterTxtField;

    @FXML
    private GridPane vakFilter;

    public OefeningenFilterPaneelController() {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("OefeningenFilterPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }    }
}
