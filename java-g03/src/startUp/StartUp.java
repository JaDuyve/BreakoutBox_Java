package startUp;

import domein.OefeningBeheerder;
import gui.OefeningController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class StartUp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        OefeningController oc = new OefeningController(new OefeningBeheerder());
        Scene scene = new Scene(oc, 1280, 720);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("BreakOutBox Controller");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}


