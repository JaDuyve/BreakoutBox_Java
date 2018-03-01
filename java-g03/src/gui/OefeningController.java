package gui;

import domein.OefeningBeheerder;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OefeningController extends HBox {
    private OefeningenOverzichtPaneelController overzichtPanel;
    private OefeningenDetailPaneelController detailPanelController;

    private OefeningenFilterPaneelController filterPaneelController;
    private OefeningMakenPaneelController oefeningMakenPaneelController;

    private OefeningBeheerder oefeningBeheerder;

    public OefeningController(OefeningBeheerder oefeningBeheerder) {

        this.oefeningBeheerder = oefeningBeheerder;
        overzichtPanel = new OefeningenOverzichtPaneelController(oefeningBeheerder);
       detailPanelController = new OefeningenDetailPaneelController(oefeningBeheerder);
        filterPaneelController = new OefeningenFilterPaneelController(oefeningBeheerder);
        oefeningMakenPaneelController = new OefeningMakenPaneelController(oefeningBeheerder);

        getChildren().addAll(overzichtPanel, detailPanelController, filterPaneelController);

        //dc.addObserver(detailPanelController);
    }
}
