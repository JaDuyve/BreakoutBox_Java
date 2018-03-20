package gui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import domein.OefeningController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class OefeningenFilterPaneelController extends VBox {

    @FXML
    private JFXTextField txtFiltByName;

    @FXML
    private VBox vbVakken;

    private Map<String, JFXCheckBox> vakken;

    private OefeningController oefeningController;

    public OefeningenFilterPaneelController(OefeningController oefeningController) {
        this.oefeningController = oefeningController;
        vakken = new HashMap<>();

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

    private void build() {
        GridPane gridPane = new GridPane();
        AtomicInteger i = new AtomicInteger();
        oefeningController.geefVakken().stream()
                .forEach(vak -> {
                    Circle circle = new Circle(20, Paint.valueOf(vak.getKleur()));
                    Label lblVak = new Label(vak.getNaam());
                    JFXCheckBox chVak = new JFXCheckBox();
                    chVak.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            changeFilter();
                        }
                    });

                    lblVak.setPadding(new Insets(10, 20,10,10));
                    lblVak.setMinWidth(60);
                    chVak.setPadding(new Insets(10,10,10,10));
                    gridPane.addRow(i.get(), circle,lblVak,chVak);
                    gridPane.setAlignment(Pos.CENTER_LEFT);
                    GridPane.setHalignment(chVak, HPos.RIGHT); // To align horizontally in the cell
                    GridPane.setValignment(chVak, VPos.CENTER);

                    vakken.put(vak.getNaam(), chVak);
                    i.getAndIncrement();
                });
        vbVakken.getChildren().add(gridPane);
        gridPane.setHgap(40);
        gridPane.setVgap(20);
    }

    @FXML
    private void filtByName(KeyEvent event) {
        changeFilter();

    }


    private void changeFilter() {
        String filtByName = txtFiltByName.getText();
        List<String> filtVakken = new ArrayList<>();
        vakken.entrySet().stream().forEach(vak -> {
            if (vak.getValue().isSelected()) {
                filtVakken.add(vak.getKey());
            }
        });

        oefeningController.changeFilter(filtByName, filtVakken, new ArrayList<String>());
    }

}
