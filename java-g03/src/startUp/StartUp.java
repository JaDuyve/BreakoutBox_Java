package startUp;

import domein.DomeinController;
import gui.WeergaveOefeningController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartUp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        WeergaveOefeningController wgc = new WeergaveOefeningController(new DomeinController());
        Scene scene = new Scene(wgc, 1280, 720);

        primaryStage.setTitle("BreakOutBox Controller");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
