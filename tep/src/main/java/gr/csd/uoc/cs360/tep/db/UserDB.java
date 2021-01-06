package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.Doctor;
import gr.csd.uoc.cs360.tep.model.Employee;
import gr.csd.uoc.cs360.tep.model.Nurse;
import gr.csd.uoc.cs360.tep.model.Patient;
import gr.csd.uoc.cs360.tep.model.User;

public class UserDB {
	
	public static List<User> getAllUsers(){
		List<User> users = new ArrayList<>();
		
		Statement stmt = null;
        Connection con = null;

        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM users;");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            Patient patient;
            Doctor doctor;
            Nurse nurse;
            Employee employee;
            while (res.next() == true) {
                int id = res.getInt("user_id");
                
                if((patient = PatientDB.getPatientByID(id)) != null) {
                	users.add(patient);
                }else if((doctor = DoctorDB.getDoctor(id)) != null) {
                	users.add(doctor);
                }else if((nurse = NurseDB.getNurse(id)) != null) {
                	users.add(nurse);
                }else if((employee = EmployeeDB.getEmployee(id)) != null) {
                	users.add(employee);
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return users;
	}

	public static int addUser(String username, String password, String job) {
		int userID = -1;
        Connection con = null;
        StringBuilder query = new StringBuilder();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = TepDB.getConnection();
			
			query.append("INSERT INTO ")
					.append(" users (username, password, job) ")
					.append(" VALUES (")
					.append("'" + username + "',")
					.append("'" + password + "',")
					.append("'" + job + "');");
			
			// Get UserID
			String generatedColumns[] = {"user_id"};
			PreparedStatement stmtIns = con.prepareStatement(query.toString(), generatedColumns);
			stmtIns.executeUpdate();

			// Get information magically completed from database
			ResultSet rs = stmtIns.getGeneratedKeys();
			if (rs.next()) {
			    // Update value of setID based on database
			    int id = rs.getInt(1);
			    userID = id;
			}
			
			return userID;
		}catch(Exception e) {
			return -1;
		}finally{
			
		}
	}
	
	public static User getUser(String username) {
		User user = null;
        Statement stmt = null;
        Connection con = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();

            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM users ")
                    .append(" WHERE ")
                    .append(" username = ").append("'").append(username).append("';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            if (res.next() == true) {
                user = new User();
                user.setUserID(res.getInt("user_id"));
                user.setUsername(res.getString("username"));
                user.setPassword(res.getString("password"));
                user.setJob(res.getString("job"));
            } else {
                System.out.println("User was not found");
            }
        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
            // close connection

        }

        return user;
	}
}
