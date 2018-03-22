package startUp;

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


        StartupMenuController smc = new StartupMenuController();
       // JFXDecorator decorator = new JFXDecorator(primaryStage, smc);
       // decorator.setCustomMaximize(true);
        Scene scene = new Scene(smc);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("BreakOutBox");

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();


    }

    @Override
    public void stop(){
        System.exit(0);
    }
}


