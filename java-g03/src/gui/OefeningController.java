package gui;

import domein.OefeningBeheerder;
import javafx.scene.layout.HBox;

public class OefeningController extends HBox {
    private OefeningenOverzichtPaneelController overzichtPanel;
    private OefeningenDetailPaneelController detailPanelController;

    private OefeningenFilterPaneelController filterPaneelController;

    private OefeningBeheerder oefeningBeheerder;

    public OefeningController(OefeningBeheerder oefeningBeheerder) {
        this.oefeningBeheerder = oefeningBeheerder;
        overzichtPanel = new OefeningenOverzichtPaneelController(oefeningBeheerder);
       detailPanelController = new OefeningenDetailPaneelController(oefeningBeheerder);
        filterPaneelController = new OefeningenFilterPaneelController(oefeningBeheerder);

        getChildren().addAll(overzichtPanel,  filterPaneelController);

        //dc.addObserver(detailPanelController);
    }
}
