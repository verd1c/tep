package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.Doctor;
import gr.csd.uoc.cs360.tep.model.Nurse;

public class NurseDB {
	
	public static Nurse addNurse(Nurse nurse) {
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	nurse.setUserID(UserDB.addUser(nurse.getFirstName() + nurse.getLastName(), "p" + nurse.getLastName(), "nurse"));
        	
        	if(nurse.getUserID() == -1) return null;
        	
        	query.append("INSERT INTO ")
        		.append(" nurses (user_id, first_name, last_name) ")
        		.append(" VALUES (")
        		.append("'" + nurse.getUserID() + "',")
        		.append("'" + nurse.getFirstName() + "',")
        		.append("'" + nurse.getLastName() + "');");
        	
        	statement.execute(query.toString());
        	return nurse;
        }catch(SQLException e) {
        	System.out.println("I failed sadge " + e);
        	return nurse;
        }finally {
        	
        }
	}
	
	public static Nurse getNurse(int ID) {
		Nurse nurse = null;
        Statement stmt = null;
        Connection con = null;
        try {

            con = TepDB.getConnection();

            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM nurses ")
                    .append(" WHERE ")
                    .append(" user_id = ").append("'").append(ID).append("';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            if (res.next() == true) {
            	nurse = new Nurse();
            	nurse.setUserID(res.getInt("user_id"));
            	nurse.setFirstName(res.getString("first_name"));
            	nurse.setLastName(res.getString("last_name"));
            } else {
                System.out.println("User was not found");
            }
            
            System.out.println(nurse);
        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            // close connection

        }

        return nurse;
	}
	
	public static void updateNurse(Nurse nurse) {
		// Check that we have all we need

        Statement stmt = null;
        Connection con = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("UPDATE nurses ")
                    .append(" SET ")
                    .append(" first_name = ").append("'" + nurse.getFirstName() + "',")
                    .append(" last_name = ").append("'" + nurse.getLastName() + "'")
                    .append(" WHERE user_id= ").append("'").append(nurse.getUserID()).append("';");

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
