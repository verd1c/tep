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

public class PrescriptionDB {
	
	public static void addPrescription(Prescription prescription) {
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder(), userQuery = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("INSERT INTO ")
        		.append(" prescriptions (visit_id, drug) ")
        		.append(" VALUES (")
        		.append("'" + prescription.getVisitID() + "',")
        		.append("'" + prescription.getDrug() + "');");
        	
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
	
	public static List<Prescription> getPrescriptionsByExamination(int visit_id){
		List<Prescription> prescriptions = new ArrayList<>();
		Statement stmt = null;
        Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM prescriptions WHERE visit_id = '" + visit_id + "';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            while (res.next() == true) {
            	Prescription prescription = new Prescription();
                
                prescription.setVisitID(res.getInt("visit_id"));
                prescription.setDrug(res.getString("drug"));
                
                prescriptions.add(prescription);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return prescriptions;
	}
}
