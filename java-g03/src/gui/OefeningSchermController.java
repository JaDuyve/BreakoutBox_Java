package gui;

import domein.OefeningBeheerder;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OefeningSchermController extends HBox {
    private OefeningenOverzichtPaneelController overzichtPanel;
    private OefeningenDetailPaneelController detailPanelController;

    private OefeningenFilterPaneelController filterPaneelController;
    private OefeningMakenPaneelController oefeningMakenPaneelController;

    private OefeningBeheerder oefeningBeheerder;

    public OefeningSchermController(OefeningBeheerder oefeningBeheerder) {

        this.oefeningBeheerder = oefeningBeheerder;
        overzichtPanel = new OefeningenOverzichtPaneelController(oefeningBeheerder);
       detailPanelController = new OefeningenDetailPaneelController(oefeningBeheerder);
        filterPaneelController = new OefeningenFilterPaneelController(oefeningBeheerder);
        oefeningMakenPaneelController = new OefeningMakenPaneelController(oefeningBeheerder);

        getChildren().addAll(overzichtPanel, filterPaneelController);

        //dc.addObserver(detailPanelController);
    }
}
