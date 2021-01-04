package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.Diagnosis;

public class DiagnosesDB {
	
	public static void addDiagnosis(Diagnosis diagnosis) {
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("INSERT INTO ")
        		.append(" diagnoses (visit_id, name) ")
        		.append(" VALUES (")
        		.append("'" + diagnosis.getVisitID() + "',")
        		.append("'" + diagnosis.getName() + "');");
        	
        	statement.execute(query.toString());
        	return;
        }catch(SQLException e) {
        	System.out.println("I failed sadge" + e);
        	return;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}finally {
        	
        }
	}
	
	public static Diagnosis getDiagnosisByVisit(int visit_id){
		Diagnosis diagnosis = null;
		Statement stmt = null;
        Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM diagnoses WHERE visit_id = '" + visit_id + "';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            if (res.next() == true) {
            	diagnosis = new Diagnosis();
                
                diagnosis.setVisitID(res.getInt("visit_id"));
                diagnosis.setName(res.getString("name"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return diagnosis;
	}
}
