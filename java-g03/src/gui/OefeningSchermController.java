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
        overzichtPanel = new OefeningenOverzichtPaneelController(oefeningController);
        detailPanelController = new OefeningenDetailPaneelController(oefeningController);
        filterPaneelController = new OefeningenFilterPaneelController(oefeningController);
        oefeningMakenPaneelController = new OefeningMakenPaneelController(oefeningController);

        getChildren().addAll(overzichtPanel, filterPaneelController);

        //dc.addObserver(detailPanelController);s
    }
}
