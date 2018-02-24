package gui;

import domein.DomeinController;
import javafx.scene.layout.HBox;

public class OefeningController extends HBox {
    private OefeningenOverzichtPaneelController overzichtPanel;
    private OefeningenDetailPaneelController detailPanelController;

    private OefeningenFilterPaneelController filterPaneelController;

    private DomeinController domeinController;

    public OefeningController(DomeinController domeinController) {
        this.domeinController = domeinController;
        overzichtPanel = new OefeningenOverzichtPaneelController(domeinController);
        detailPanelController = new OefeningenDetailPaneelController();
        filterPaneelController = new OefeningenFilterPaneelController();

        getChildren().addAll(overzichtPanel, detailPanelController, filterPaneelController);

        domeinController.addObserver(detailPanelController);
    }
}
