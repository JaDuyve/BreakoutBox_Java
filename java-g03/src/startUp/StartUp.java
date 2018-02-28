package startUp;

import domein.OefeningBeheerder;
import gui.OefeningController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {



    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new OefeningController(new OefeningBeheerder()), 1500, 720);

        primaryStage.setTitle("BreakOutBox");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        Application.launch(StartUp.class, args);
    }

}
