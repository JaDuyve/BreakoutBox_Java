package persistentie;

import domein.NumerischeOefening;
import domein.Oefening;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OefeningMapper {

    public List<Oefening> geefOefeningen(){
        List<Oefening> oefeningen = new ArrayList<>();
        //tester
        oefeningen.add(new NumerischeOefening("testNaam", "testOpgeave", "Wiskunde", 50));
        oefeningen.add(new NumerischeOefening("optellen", "2X2", "wiskunde", 4));
 /*       try(Connection conn = DriverManager.getConnection(Connectie.JDBC_URL))
        {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM project_prog_g03.")
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return oefeningen;
    }

    public Oefening zoekOefening(String naam){
        throw new UnsupportedOperationException();

    }

}
