package gui;

import domein.BobController;
import javafx.scene.layout.HBox;

public class BobSchermController extends HBox {
    private BobOverzichtPaneelController overzichtPanel;
    private BobDetailPaneelController bobDetailPaneelController;
    private BobController bobController;

    public BobSchermController(BobController bobC) {
        this.bobController = bobC;

        overzichtPanel = new BobOverzichtPaneelController(bobController);
        bobDetailPaneelController = new BobDetailPaneelController();
        getChildren().addAll(overzichtPanel, bobDetailPaneelController);


        bobController.addObserver(bobDetailPaneelController);

    }
}
