package gui;

import domein.OefeningBeheerder;
import domein.OefeningController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class OefeningMakenPaneelController extends AnchorPane {

    @FXML
    private Label lblTitel;

    @FXML
    private Label lblNaam;

    @FXML
    private TextField txfNaam;

    @FXML
    private Button opgaveButton;

    private OefeningController oefeningController;

    public OefeningMakenPaneelController(OefeningController dc) {
        this.oefeningController = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OefeningMakenPaneel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    void opgaveFileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        File f = fc.showOpenDialog(null);

        if (f != null)
        {
            String opgavePath = f.getAbsolutePath();
        }
    }

    @FXML
    void feedbackFileChooser(ActionEvent event){
        FileChooser fc2 = new FileChooser();
        fc2.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*.pdf"));
        File f = fc2.showOpenDialog(null);

        if (f != null)
        {
            String feedbackPath = f.getAbsolutePath();
        }
    }
}
