package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domein.Sessie;
import domein.SessieController;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SessieOverzichtPaneelController extends VBox {

    private SessieController sessieController;

    @FXML
    private Label lblBobs;

    @FXML
    private ListView<Sessie> sessieView;

    @FXML
    private JFXButton addBobButton;

    @FXML
    private JFXTextField txfZoekNaam;

    public SessieOverzichtPaneelController(SessieController sc) {
        this.sessieController = sc;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("SessieOverzichtPaneel.fxml"));
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
        sessieView.setItems(sessieController.geefSessies());

        if (sessieView.getItems().isEmpty()) {
            sessieView.getStyleClass().add("emptysessie");
            sessieView.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
        }

        sessieView.getItems().addListener(new ListChangeListener<Sessie>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Sessie> c) {
                if (sessieView.getItems().isEmpty()) {
                    sessieView.getStyleClass().add("emptysessie");
                    sessieView.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
                }
                else {
                    sessieView.getStyleClass().clear();
                    sessieView.getStylesheets().add(getClass().getResource("/css/app.css").toExternalForm());
                }
            }
        });

        sessieView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue, oldValue, newValue) -> {
                    sessieController.veranderHuidgeSessie(newValue);

                });

    }

    @FXML
    void createBob(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new SessieMakenPaneelController(sessieController));
    }

    @FXML
    void backButton(ActionEvent event) {
        Scene s = this.getScene();
        s.setRoot(new StartupMenuController());
    }

    @FXML
    void zoekNaam(KeyEvent event) {
        sessieController.changeFilter(txfZoekNaam.getText());
    }



}


