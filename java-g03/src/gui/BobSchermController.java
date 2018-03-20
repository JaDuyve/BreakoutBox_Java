package gui;

import domein.BobController;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class BobSchermController extends BorderPane {
    private BobOverzichtPaneelController overzichtPanel;
    private BobDetailPaneelController bobDetailPaneelController;
    private BobController bobController;

    public BobSchermController() {

        this(new BobController());

    }

    public BobSchermController(BobController bc){
        this.bobController = bc;
        this.setTop(new TopBarController());
        overzichtPanel = new BobOverzichtPaneelController(bobController);
        bobDetailPaneelController = new BobDetailPaneelController();
        this.setCenter(overzichtPanel);
        this.setRight(bobDetailPaneelController);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setOffsetX(0.0f);
        ds.setColor(Color.color(0, 0, 0, 0.5));
        ds.setRadius(5.0);
        this.getRight().setEffect(ds);
        bobController.addObserver(bobDetailPaneelController);
    }
}
