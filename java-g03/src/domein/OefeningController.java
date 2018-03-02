package domein;

import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.management.Notification;
import java.io.File;
import java.util.ArrayList;


public class OefeningController
{
    private OefeningBeheerder oefeningBeheerder = new OefeningBeheerder();

    public OefeningController() {
    }

    public void verwijderOefening(String naam){
        oefeningBeheerder.verwijderOefening(naam);
    }

    public void wijzigOefening(String oefeningNaam, String naam, String opgavePath, String antwoord, String feedbackPath, ArrayList<Groepsbewerking> groepsbewerkingen, Vak vak){
        oefeningBeheerder.wijzigOefening(oefeningNaam, naam, opgavePath, antwoord, feedbackPath, groepsbewerkingen, vak);
    }

    public ObservableList<Vak> geefVakken(){
        return oefeningBeheerder.geefVakken();
    }

    public ObservableList<Groepsbewerking> geefGroepsbewerkingen(){
        return oefeningBeheerder.geefGroepsbewerkingen();
    }

    public void createOefening(String naam, String opgavePath,String antwoord, String feedback, ArrayList<Groepsbewerking> groepsbewerkingen, Vak vak){
        oefeningBeheerder.createOefening(naam, opgavePath, antwoord, feedback, groepsbewerkingen, vak);
    }

    public ObservableList<Oefening> geefOefeningen(){
        return oefeningBeheerder.geefOefeningen();
    }

    public void filterOpNaam(String oefeningNaam){
        oefeningBeheerder.filterOpNaam(oefeningNaam);
    }

    public void filterOpVak(String vakNaam){
        oefeningBeheerder.filterOpNaam(vakNaam);
    }

    public File GeefFile(String pathName){
        throw new NotImplementedException();
    }

}
