package gui;

import domein.BobController;
import javafx.scene.layout.HBox;

public class BobSchermController extends HBox {
    private BobOverzichtPaneelController overzichtPanel;

    private BobController bobController;

    public BobSchermController(BobController bobController) {
        this.bobController = bobController;

        overzichtPanel = new BobOverzichtPaneelController(bobController);

        getChildren().addAll( overzichtPanel);

    }
}
