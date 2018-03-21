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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class DoelstellingscodeBeheerSchermController extends BorderPane implements Observer
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

    @FXML
    private JFXButton btnVerwijder;

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
        doelstellingscodeController.addObservertje(this);
        btnVerwijder.setDisable(true);
        this.setTop(new TopBarController());
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
            s.setRoot(new DoelstellingscodeBeheerSchermController(doelstellingscodeController));
        } catch (IllegalArgumentException ex) {
            AlertBox.showAlertError("Fout doelstelling toevoegen", ex.getMessage(), (Stage) this.getScene().getWindow());
        }
    }


    @FXML
    void zoekNaam(KeyEvent event)
    {
        doelstellingscodeController.changeFilter(txfZoekNaam.getText());
    }


    @FXML
    void verwijderDoelstellingscode(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wilt u de geselecteerde doelstellingscode wilt verwijderen?");
        alert.setTitle("Doelstellingscode verwijderen");
        alert.initOwner((Stage) this.getScene().getWindow());
        Scene s = this.getScene();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            try {
                doelstellingscodeController.verwijderDoelstellingscode();
                s.setRoot(new DoelstellingscodeBeheerSchermController(doelstellingscodeController));
            }catch (IllegalArgumentException ex){
                AlertBox.showAlertError("Fout verwijder doelstellingscode", ex.getMessage(), (Stage) this.getScene().getWindow());
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean isnull = arg == null;
        btnVerwijder.setDisable(isnull);

    }

}
