package startUp;

import domein.NumerischeOefening;
import domein.OefeningBeheerder;
import domein.Vak;
import gui.OefeningController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.JPAUtil;

import javax.persistence.EntityManager;


public class StartUp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        OefeningController oc = new OefeningController(new OefeningBeheerder());
        Scene scene = new Scene(oc, 1280, 720);

        primaryStage.setTitle("BreakOutBox Controller");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}


