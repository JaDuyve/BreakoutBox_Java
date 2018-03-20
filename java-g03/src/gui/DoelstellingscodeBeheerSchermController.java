package gui;

import com.jfoenix.controls.JFXListView;
import domein.Doelstellingscode;
import domein.DoelstellingscodeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DoelstellingscodeBeheerSchermController extends BorderPane
{

    @FXML
    private Button backbutton;

    @FXML
    private JFXListView<Doelstellingscode> lvDoelstellingscodes;

    private DoelstellingscodeController doelstellingscodeController;

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

}
