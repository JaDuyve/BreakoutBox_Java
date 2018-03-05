package gui;

import domein.OefeningController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class OefeningenFilterPaneelController extends VBox {

    @FXML
    private TextField txtFiltByNaam;

    @FXML
    private VBox vbVakken;

    private List<CheckBox> vakken;

    private OefeningController oefeningController;

    public OefeningenFilterPaneelController(OefeningController oefeningController) {
        this.oefeningController = oefeningController;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("OefeningFilterPaneel.fxml"));
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
        oefeningController.geefVakken().stream()
            .forEach(vak -> {
                HBox hbox = new HBox();
                Circle circle = new Circle(10, Paint.valueOf(vak.getKleur()));
                Label lblVak = new Label(vak.getNaam());
                Checkbox chVak = new Checkbox();

            });


    }

    @FXML
    void changeFilterName(KeyEvent event) {

    }
}
