package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.MedicalTest;
import gr.csd.uoc.cs360.tep.model.Prescription;
import gr.csd.uoc.cs360.tep.model.Visit;

public class MedicalTestDB {
	
	public static void addTest(MedicalTest test) {
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder(), userQuery = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("INSERT INTO ")
        		.append(" medicaltests (visit_id, type, completed) ")
        		.append(" VALUES (")
        		.append("'" + test.getVisitID() + "',")
        		.append("'" + test.getType() + "',")
        		.append("'false');");
        	
        	statement.execute(query.toString());
        	
        	System.out.println("ssasas");
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
	
	public static List<MedicalTest> getTestsByExamination(int visit_id){
		List<MedicalTest> tests = new ArrayList<>();
		Statement stmt = null;
        Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM medicaltests WHERE visit_id = '" + visit_id + "';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            while (res.next() == true) {
                MedicalTest test = new MedicalTest();
                
                test.setVisitID(res.getInt("visit_id"));
                test.setType(res.getString("type"));
                test.setCompleted(Boolean.parseBoolean(res.getString("completed")));
                
                tests.add(test);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return tests;
	}
	
	public static void completeTest(int visit_id) {
		// Check that we have all we need

        Statement stmt = null;
        Connection con = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("UPDATE medicaltests ")
                    .append(" SET ")
                    .append(" completed = ").append("'true'")
                    .append(" WHERE visit_id= ").append("'").append(visit_id).append("';");

            stmt.executeUpdate(insQuery.toString());


        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // close connection

        }
	}
}
