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
import gr.csd.uoc.cs360.tep.model.Visit;

public class VisitDB {
	
	public static List<Visit> getVisits(int AMKA){
		List<Visit> visits = new ArrayList<>();
		Statement stmt = null;
        Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM visits WHERE amka = '" + AMKA + "';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            while (res.next() == true) {
                Visit visit = new Visit();
                
                visit.setAMKA(res.getInt("AMKA"));
                visit.setDoctorID(res.getInt("doctor"));
                visit.setIllness(res.getString("illness"));
                visit.setVisitID(res.getInt("visit_id"));
                visit.setDate(res.getString("date"));
                visit.setDoctor(DoctorDB.getDoctor(visit.getDoctorID()));
                
                visits.add(visit);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return visits;
	}
	
	public static Visit makeVisit(Visit visit) {
		int visitID = -1;
        Connection con = null;
        StringBuilder query = new StringBuilder();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = TepDB.getConnection();
			
			Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
			
			query.append("INSERT INTO ")
					.append(" visits (AMKA, doctor, illness, date) ")
					.append(" VALUES (")
					.append("'" + visit.getAMKA() + "',")
					.append("'" + visit.getDoctorID() + "',")
					.append("'" + visit.getIllness() + "',")
					.append("'" + timestamp + "');");
			
			// Get UserID
			String generatedColumns[] = {"visit_id"};
			PreparedStatement stmtIns = con.prepareStatement(query.toString(), generatedColumns);
			stmtIns.executeUpdate();

			// Get information magically completed from database
			ResultSet rs = stmtIns.getGeneratedKeys();
			if (rs.next()) {
			    // Update value of setID based on database
			    int id = rs.getInt(1);
			    visitID = id;
			}
			
			visit.setVisitID(visitID);
		}catch(Exception e) {
			visit.setVisitID(-1);
		}finally{
			
		}
		return visit;
	}
	
	public static List<Visit> getVisitsByDoctor(int user_id){
		List<Visit> visits = new ArrayList<>();
		Statement stmt = null;
        Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM visits WHERE doctor = '" + user_id + "';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            while (res.next() == true) {
                Visit visit = new Visit();
                
                visit.setAMKA(res.getInt("AMKA"));
                visit.setDoctorID(res.getInt("doctor"));
                visit.setIllness(res.getString("illness"));
                visit.setVisitID(res.getInt("visit_id"));
                visit.setDate(res.getString("date"));
                visit.setFirstName(PatientDB.getFirstName(visit.getAMKA()));
                visit.setLastName(PatientDB.getLastName(visit.getAMKA()));
                
                visits.add(visit);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return visits;
	}
	
	public static List<Visit> getAllVisits(){
		List<Visit> visits = new ArrayList<>();
		Statement stmt = null;
        Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM visits;");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            while (res.next() == true) {
                Visit visit = new Visit();
                
                visit.setVisitID(res.getInt("visit_id"));
                visit.setAMKA(res.getInt("AMKA"));
                visit.setDoctorID(res.getInt("doctor"));
                visit.setIllness(res.getString("illness"));
                visit.setDate(res.getString("date"));
                visit.setFirstName(PatientDB.getFirstName(visit.getAMKA()));
                visit.setLastName(PatientDB.getLastName(visit.getAMKA()));
                
                visits.add(visit);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        return visits;
	}

}
