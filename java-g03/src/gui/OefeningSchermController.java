package gui;


import domein.OefeningController;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class OefeningSchermController extends BorderPane {
    private OefeningenOverzichtPaneelController overzichtPanel;
    private OefeningenDetailPaneelController detailPanelController;

    private OefeningenFilterPaneelController filterPaneelController;

    private OefeningController oefeningController;

    public OefeningSchermController(OefeningController dc) {

        this.oefeningController = dc;

        detailPanelController = new OefeningenDetailPaneelController(oefeningController);
        overzichtPanel = new OefeningenOverzichtPaneelController(oefeningController, this, detailPanelController);
        filterPaneelController = new OefeningenFilterPaneelController(oefeningController);
        this.setLeft(filterPaneelController);
        this.setRight(detailPanelController);
        this.setCenter(overzichtPanel);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setOffsetX(0.0f);
        ds.setColor(Color.color(0, 0, 0, 0.5));
        ds.setRadius(5.0);
        this.getRight().setEffect(ds);
        this.getLeft().setEffect(ds);
        this.setStyle("-fx-background-color: #FFFFFF;");
        oefeningController.addObservertje(detailPanelController);
    }
}
