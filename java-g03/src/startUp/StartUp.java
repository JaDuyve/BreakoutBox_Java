package startUp;

import domein.OefeningController;
import gui.StartupMenuController;
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
        StartupMenuController smc = new StartupMenuController(new OefeningController());
        Scene scene = new Scene(smc);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("BreakOutBox Controller");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }
}


