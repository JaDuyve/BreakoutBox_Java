package gui;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class TopBarController extends HBox {
    public TopBarController() {
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("TopBar.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

