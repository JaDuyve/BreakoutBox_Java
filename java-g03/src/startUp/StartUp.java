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

public class StartUp  {



    /*@Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new OefeningController(new OefeningBeheerder()), 1500, 720);

        primaryStage.setTitle("BreakOutBox");
        primaryStage.setScene(scene);
        primaryStage.show();

    }*/


    public static void main(String[] args) {
        Vak v = new Vak("test");


        EntityManager e = JPAUtil.getEntityManagerFactory().createEntityManager();
        e.getTransaction().begin();
        e.persist(v);
        e.getTransaction().commit();
        e.close();
        JPAUtil.getEntityManagerFactory().close();
    }

}
