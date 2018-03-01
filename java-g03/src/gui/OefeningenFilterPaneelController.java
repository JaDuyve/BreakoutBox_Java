package gui;

import com.jfoenix.controls.JFXTextField;
import domein.OefeningBeheerder;
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

    private OefeningBeheerder oefeningBeheerder;

    public OefeningenFilterPaneelController(OefeningBeheerder oefeningBeheerder) {
        this.oefeningBeheerder = oefeningBeheerder;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("OefeningenFilterPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        build();
    }

    private void build(){
        oefeningBeheerder.geefVakken().stream()
                .forEach(v -> {


                        }
                        );
    }
}
