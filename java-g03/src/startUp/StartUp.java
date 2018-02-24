package startUp;

import domein.DomeinController;
import gui.OefeningController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        OefeningController wgc = new OefeningController(new DomeinController());
        Scene scene = new Scene(wgc, 1500, 720);

        primaryStage.setTitle("BreakOutBox");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
