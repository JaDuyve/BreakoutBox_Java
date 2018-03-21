package gui;

import domein.SessieController;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class SessieSchermController extends BorderPane {

    private SessieController sessieController;
    private SessieOverzichtPaneelController sessieOverzichtPanel;
    private SessieDetailPaneelController sessieDetailPanel;

    public SessieSchermController() {

        this(new SessieController());

    }

    public SessieSchermController(SessieController sc){
        this.sessieController = sc;
        this.setTop(new TopBarController());
        sessieOverzichtPanel = new SessieOverzichtPaneelController(sessieController);
        sessieDetailPanel = new SessieDetailPaneelController(sessieController);
        this.setCenter(sessieOverzichtPanel);
        this.setRight(sessieDetailPanel);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setOffsetX(0.0f);
        ds.setColor(Color.color(0, 0, 0, 0.5));
        ds.setRadius(5.0);
        this.getRight().setEffect(ds);
        sessieController.addObserver(sessieDetailPanel);
    }
}
