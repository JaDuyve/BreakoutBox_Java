package persistentie;

import java.util.ArrayList;
import java.util.List;

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
