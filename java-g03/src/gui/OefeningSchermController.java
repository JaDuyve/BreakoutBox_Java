package gui;


import domein.OefeningController;
import javafx.scene.layout.HBox;

public class OefeningSchermController extends HBox {
    private OefeningenOverzichtPaneelController overzichtPanel;
    private OefeningenDetailPaneelController detailPanelController;

    private OefeningenFilterPaneelController filterPaneelController;
    private OefeningMakenPaneelController oefeningMakenPaneelController;

    private OefeningController oefeningController;

    public OefeningSchermController(OefeningController dc) {

        this.oefeningController = dc;

        detailPanelController = new OefeningenDetailPaneelController(oefeningController);
        overzichtPanel = new OefeningenOverzichtPaneelController(oefeningController, this, detailPanelController);
        filterPaneelController = new OefeningenFilterPaneelController(oefeningController);
        oefeningMakenPaneelController = new OefeningMakenPaneelController(oefeningController);

        getChildren().addAll(filterPaneelController, overzichtPanel, detailPanelController);

        oefeningController.addObservertje(detailPanelController);
    }
}
