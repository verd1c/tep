package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.Doctor;
import gr.csd.uoc.cs360.tep.model.Patient;
import gr.csd.uoc.cs360.tep.model.User;

public class DoctorDB {
	
	public static List<Doctor> getDoctorsBySpec(String spec) throws ClassNotFoundException {
        List<Doctor> doctors = new ArrayList<>();

        Statement stmt = null;
        Connection con = null;

        try {

            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM doctors WHERE specialization = '" + spec + "';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            while (res.next() == true) {
                Doctor doctor = new Doctor();
                doctor.setUserID(res.getInt("user_id"));
                doctor.setFirstName(res.getString("first_name"));
                doctor.setLastName(res.getString("last_name"));
                doctor.setSpecialization(res.getString("specialization"));
                doctors.add(doctor);
            }

        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return doctors;
    }
	
	public static Doctor addDoctor(Doctor doctor) {
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	doctor.setUserID(UserDB.addUser(doctor.getFirstName() + doctor.getLastName(), "p" + doctor.getLastName(), "doctor"));
        	
        	if(doctor.getUserID() == -1) return null;
        	
        	query.append("INSERT INTO ")
        		.append(" doctors (user_id, first_name, last_name, specialization) ")
        		.append(" VALUES (")
        		.append("'" + doctor.getUserID() + "',")
        		.append("'" + doctor.getFirstName() + "',")
        		.append("'" + doctor.getLastName() + "',")
        		.append("'" + doctor.getSpecialization() + "');");
        	
        	statement.execute(query.toString());
        	return doctor;
        }catch(SQLException e) {
        	System.out.println("I failed sadge" + e);
        	return doctor;
        }finally {
        	
        }
	}
	
	public static Doctor getDoctor(int ID) {
		Doctor doctor = null;
        Statement stmt = null;
        Connection con = null;
        try {

            con = TepDB.getConnection();

            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM doctors ")
                    .append(" WHERE ")
                    .append(" user_id = ").append("'").append(ID).append("';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            if (res.next() == true) {
            	doctor = new Doctor();
            	doctor.setUserID(res.getInt("user_id"));
            	doctor.setFirstName(res.getString("first_name"));
            	doctor.setLastName(res.getString("last_name"));
            	doctor.setSpecialization(res.getString("specialization"));
            	doctor.setVisits(VisitDB.getVisitsByDoctor(doctor.getUserID()));
            } else {
                System.out.println("User was not found");
            }
        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            // close connection

        }

        return doctor;
	}
}
