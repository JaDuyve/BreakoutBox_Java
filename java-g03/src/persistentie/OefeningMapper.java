package persistentie;

import domein.Oefening;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OefeningMapper {

    public HashMap<String, Oefening> geefOefeningen(){
        HashMap<String, Oefening> oefeningen = new HashMap<String, Oefening>();

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
