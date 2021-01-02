package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import gr.csd.uoc.cs360.tep.model.Patient;

public class PatientDB {
	
	public static Patient addPatient(Patient patient) {
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder(), userQuery = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	patient.setUserID(UserDB.addUser(patient.getAMKA() + "", "p" + patient.getAMKA(), "patient"));
        	
        	if(patient.getUserID() == -1) return null;
        	
        	query.append("INSERT INTO ")
        		.append(" patients(user_id, amka, first_name, last_name, address, institution) ")
        		.append(" VALUES (")
        		.append("'" + patient.getUserID() + "',")
        		.append("'" + patient.getAMKA() + "',")
        		.append("'" + patient.getFirstName() + "',")
        		.append("'" + patient.getLastName() + "',")
        		.append("'" + patient.getAddress() + "',")
        		.append("'" + patient.getInstitution() + "');");
        	
        	statement.execute(query.toString());
        	return patient;
        }catch(SQLException e) {
        	System.out.println("I failed sadge" + e);
        	return patient;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return patient;
		}finally {
        	
        }
	}

	// Get patient
	public static Patient getPatient(int AMKA) {
		Patient patient = null;
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	
        	con = TepDB.getConnection();
        	
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM patients")
        		.append(" WHERE amka = '")
        		.append(AMKA)
        		.append("';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	if(res.next()) {
        		patient = new Patient();
        		patient.setAMKA(res.getInt("amka"));
        		patient.setFirstName(res.getString("first_name"));
        		patient.setLastName(res.getString("last_name"));
        		patient.setAddress(res.getString("address"));
        		patient.setInstitution(res.getString("institution"));
        		
        	}else {
        		System.out.println("Patient " + AMKA + " not found");
        	}
        	
        }catch(SQLException ex) {
        	System.out.println("Exception " + ex);
        }finally {
        	
        }
        
        return patient;
	}
}
