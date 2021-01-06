package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.Patient;
import gr.csd.uoc.cs360.tep.model.Visit;

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
        	Class.forName("com.mysql.jdbc.Driver");
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
        		patient.setVisits(VisitDB.getVisits(patient.getAMKA()));
        		
        	}else {
        		System.out.println("Patient " + AMKA + " not found");
        	}
        	
        }catch(SQLException ex) {
        	System.out.println("Exception " + ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
        	
        }
        
        return patient;
	}
	
	// Get patient
	public static Patient getPatientByID(int id) {
		Patient patient = null;
		Statement statement = null;
	    Connection con = null;
	    StringBuilder query = new StringBuilder();
	        
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = TepDB.getConnection();
	        	
	        	statement = con.createStatement();
	        	
	        	query.append("SELECT * FROM patients")
	        		.append(" WHERE user_id = '")
	        		.append(id)
	        		.append("';");
	        	statement.execute(query.toString());
	        	ResultSet res = statement.getResultSet();

	        	if(res.next()) {
	        		patient = new Patient();
	        		patient.setUserID(res.getInt("user_id"));
	        		patient.setAMKA(res.getInt("amka"));
	        		patient.setFirstName(res.getString("first_name"));
	        		patient.setLastName(res.getString("last_name"));
	        		patient.setAddress(res.getString("address"));
	        		patient.setInstitution(res.getString("institution"));
	        		patient.setVisits(VisitDB.getVisits(patient.getAMKA()));
	        		
	        	}else {
	        		System.out.println("Patient " + id + " not found");
	        	}
	        	
	        }catch(SQLException ex) {
	        	System.out.println("Exception " + ex);
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
	        	
	        }
	        
	        return patient;
		}
	
	public static String getFirstName(int AMKA) {
		String name = null;
		Statement statement = null;
	    Connection con = null;
	    StringBuilder query = new StringBuilder();
	        
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = TepDB.getConnection();
	        	
	        	statement = con.createStatement();
	        	
	        	query.append("SELECT * FROM patients")
	        		.append(" WHERE amka = '")
	        		.append(AMKA)
	        		.append("';");
	        	statement.execute(query.toString());
	        	ResultSet res = statement.getResultSet();

	        	if(res.next()) {
	        		name = res.getString("first_name");
	        		
	        	}else {
	        		System.out.println("Patient not found");
	        	}
	        	
	        }catch(SQLException ex) {
	        	System.out.println("Exception " + ex);
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
	        	
	        }
	        
	        return name;
	}
	
	public static String getLastName(int AMKA) {
		String name = null;
		Statement statement = null;
	    Connection con = null;
	    StringBuilder query = new StringBuilder();
	        
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = TepDB.getConnection();
	        	
	        	statement = con.createStatement();
	        	
	        	query.append("SELECT * FROM patients")
	        		.append(" WHERE amka = '")
	        		.append(AMKA)
	        		.append("';");
	        	statement.execute(query.toString());
	        	ResultSet res = statement.getResultSet();

	        	if(res.next()) {
	        		name = res.getString("last_name");
	        		
	        	}else {
	        		System.out.println("Patient not found");
	        	}
	        	
	        }catch(SQLException ex) {
	        	System.out.println("Exception " + ex);
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				// close connection 
	        }
	        
	        return name;
	}
	
	public static void updatePatient(Patient patient) {
		// Check that we have all we need

        Statement stmt = null;
        Connection con = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("UPDATE patients ")
                    .append(" SET ")
                    .append(" first_name = ").append("'" + patient.getFirstName() + "',")
                    .append(" last_name = ").append("'" + patient.getLastName() + "',")
                    .append(" address = ").append("'" + patient.getAddress() + "',")
                    .append(" institution = ").append("'" + patient.getInstitution() + "'")
                    .append(" WHERE user_id= ").append("'").append(patient.getUserID()).append("';");

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
