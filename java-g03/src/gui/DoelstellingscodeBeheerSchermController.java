package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import domein.Doelstellingscode;
import domein.DoelstellingscodeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DoelstellingscodeBeheerSchermController extends VBox
{

    @FXML
    private Button backbutton;

    @FXML
    private JFXListView<Doelstellingscode> lvDoelstellingscodes;

    @FXML
    private JFXButton btnDoelstellingscode;

    private DoelstellingscodeController doelstellingscodeController;

    @FXML
    private JFXTextField txfZoekNaam;

    @FXML
    private JFXTextField txfToevoegen;

    public DoelstellingscodeBeheerSchermController(DoelstellingscodeController doelstellingscodeController) {
        this.doelstellingscodeController = doelstellingscodeController;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("DoelstellingscodeBeheerScherm.fxml"));
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
        lvDoelstellingscodes.setItems(doelstellingscodeController.geefDoelstellingscodes());


        lvDoelstellingscodes.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue, oldValue, newValue) -> {
                    doelstellingscodeController.veranderHuidige(newValue);

                });

    }


    @FXML
    void backButton(ActionEvent event)
    {
        Scene s = this.getScene();
        s.setRoot(new StartupMenuController());
    }


    @FXML
    void createDoelstellingscode(ActionEvent event) {
        try {
            doelstellingscodeController.createDoelstellingscode(txfToevoegen.getText());
            Scene s = this.getScene();
            s.setRoot(new DoelstellingscodeBeheerSchermController(new DoelstellingscodeController()));
        } catch (IllegalArgumentException ex) {
            AlertBox.showAlertError("Fout doelstelling toevoegen", ex.getMessage(), (Stage) this.getScene().getWindow());
        }
    }


    @FXML
    void zoekNaam(KeyEvent event)
    {
        doelstellingscodeController.changeFilter(txfZoekNaam.getText());
    }

}
