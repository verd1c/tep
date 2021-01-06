package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.Doctor;
import gr.csd.uoc.cs360.tep.model.Employee;
import gr.csd.uoc.cs360.tep.model.Nurse;
import gr.csd.uoc.cs360.tep.model.Patient;
import gr.csd.uoc.cs360.tep.model.Shift;
import gr.csd.uoc.cs360.tep.model.User;

public class ShiftDB {
	
	public static Shift getShift(int shift_id) {
		Shift shift = null;
		Statement stmt;
        Connection con;
        ResultSet res;
        StringBuilder insQuery;
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();

            stmt = con.createStatement();
            insQuery = new StringBuilder();
            
            insQuery.append("SELECT * FROM shift_attendee ")
                    .append(" WHERE ")
                    .append(" shift_id = ").append("'").append(shift_id).append("';");

            stmt.execute(insQuery.toString());

            res = stmt.getResultSet();
            shift = new Shift();
            List<User> users = new ArrayList<>();
            User user;
            Doctor doc;
            Nurse nurse;
            Employee employee;
            while (res.next() == true) {
            	int id = res.getInt("user_id");
            	
            	if((doc = DoctorDB.getDoctor(id)) != null) {
            		users.add(doc);
            	}else if((nurse = NurseDB.getNurse(id)) != null){
            		users.add(nurse);
            	}else if((employee = EmployeeDB.getEmployee(id)) != null) {
            		users.add(employee);
            	}
            }
            shift.setShiftID(shift_id);
            shift.setAttendees(users);
            return shift;
            
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

	}
	
	public static Shift updateShift(List<User> attendees) {
		List<User> shiftAttendees = new ArrayList<User>();
		int shiftID = -1;
		Shift shift = new Shift();
        Connection con = null;
        StringBuilder query = new StringBuilder();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = TepDB.getConnection();
			
			Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
			
			query.append("INSERT INTO ")
					.append(" shift (since) ")
					.append(" VALUES (")
					.append("'" + timestamp + "');");
			
			// Get UserID
			String generatedColumns[] = {"shift_id"};
			PreparedStatement stmtIns = con.prepareStatement(query.toString(), generatedColumns);
			stmtIns.executeUpdate();

			// Get information magically completed from database
			ResultSet rs = stmtIns.getGeneratedKeys();
			if (rs.next()) {
			    // Update value of setID based on database
			    int id = rs.getInt(1);
			    shift.setShiftID(id);
			}
			
			Doctor doc;
			Nurse nurse;
			Employee employee;
			for(User user : attendees) {
				query = new StringBuilder();
				Statement statement = con.createStatement();
				query.append("INSERT INTO ")
					.append(" shift_attendee(shift_id, user_id) ")
					.append(" VALUES (")
					.append("'" + shift.getShiftID() + "',")
					.append("'" + user.getUserID() + "');");
				statement.execute(query.toString());
				
				if((doc = DoctorDB.getDoctor(user.getUserID())) != null) {
					shiftAttendees.add(doc);
				}else if((nurse = NurseDB.getNurse(user.getUserID())) != null) {
					shiftAttendees.add(nurse);
				}else if((employee = EmployeeDB.getEmployee(user.getUserID())) != null) {
					shiftAttendees.add(employee);
				}
			}
			
			shift.setSince(timestamp.toString());
			shift.setAttendees(shiftAttendees);
			return shift;
		}catch(Exception e) {
			return shift;
		}finally{
			
		}
	}
	
	public static int getDoctorIDBySpecShift(String spec, int shift_id) {
		int doctor_id = -1;
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = TepDB.getConnection();
        	
        	statement = con.createStatement();
        	
        	query.append("SELECT doctors.user_id, doctors.specialization FROM ")
        		.append(" doctors INNER JOIN shift_attendee ")
        		.append(" ON Doctors.user_id=shift_attendee.user_id ")
        		.append(" WHERE Doctors.specialization='" + spec + "';");
        	System.out.println(query.toString());
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	if(res.next()) {
        		return res.getInt("user_id");
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
        
        return doctor_id;
	}
	
	public static Doctor getShiftDoctor(String illness) {
		Doctor doctor = null;
		String spec;
		int doctor_id = 0;
        Statement stmt;
        Connection con;
        ResultSet res;
        StringBuilder insQuery;
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();

            // Find illness spec
            spec = IllnessDB.getSpecByIllness(illness);        
            doctor_id = getDoctorIDBySpecShift(spec, getCurrentShift());
            
            // Finally get doctor
            stmt = con.createStatement();
            insQuery = new StringBuilder();
            
            insQuery.append("SELECT * FROM doctors ")
                    .append(" WHERE ")
                    .append(" user_id = ").append("'").append(doctor_id).append("';");

            stmt.execute(insQuery.toString());

            res = stmt.getResultSet();

            if (res.next() == true) {
                doctor = new Doctor();
                doctor.setUserID(res.getInt("user_id"));
                doctor.setFirstName(res.getString("first_name"));
                doctor.setLastName(res.getString("last_name"));
                doctor.setSpecialization(res.getString("specialization"));
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

        return doctor;
	}
	
	public static int getCurrentShift() {
		int shiftID = -1;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        Statement statement;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = TepDB.getConnection();
			statement = con.createStatement();
			
			query.append("SELECT MAX(shift_id) AS MaxID FROM shift;");
			statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();
        	
        	if(res.next()) {
        		shiftID = res.getInt("MaxID");
        	}
			
			return shiftID;
		}catch(Exception e) {
			return shiftID;
		}finally{
			
		}
	}
}
