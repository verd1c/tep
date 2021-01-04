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

import gr.csd.uoc.cs360.tep.model.Drug;
import gr.csd.uoc.cs360.tep.model.Examination;
import gr.csd.uoc.cs360.tep.model.MedicalTest;
import gr.csd.uoc.cs360.tep.model.Patient;
import gr.csd.uoc.cs360.tep.model.Prescription;

public class ExaminationDB {
	
	public static Examination addExamination(Examination examination) {
		int examinationID = -1;
        Connection con = null;
        StringBuilder query = new StringBuilder();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = TepDB.getConnection();
			
			query.append("INSERT INTO ")
					.append(" examinations (visit_id, amka, doctor_id, diagnosis) ")
					.append(" VALUES (")
					.append("'" + examination.getVisitID() + "',")
					.append("'" + examination.getAMKA() + "',")
					.append("'" + examination.getDoctorID() + "',")
					.append("'" + examination.getDiagnosis() + "');");
			
			// Get ExaminationID
			String generatedColumns[] = {"examination_id"};
			PreparedStatement stmtIns = con.prepareStatement(query.toString(), generatedColumns);
			stmtIns.executeUpdate();

			// Get information magically completed from database
			ResultSet rs = stmtIns.getGeneratedKeys();
			if (rs.next()) {
			    // Update value of setID based on database
			    int id = rs.getInt(1);
			    examinationID = id;
			}
			
			examination.setExaminationID(examinationID);
			
//			if(examination.getDrugs() != null) {
//				for(Prescription prescription : examination.getDrugs()) {
//					prescription.setVisitID(examination.getVisitID());
//					System.out.println(prescription.getDrug());
//					PrescriptionDB.addPrescription(prescription);
//				}
//			}
			
			if(examination.getTests() != null) {
				for(MedicalTest test : examination.getTests()) {
					test.setVisitID(examination.getVisitID());
					MedicalTestDB.addTest(test);
				}
			}
			
			examination.setDiagnosis(DiagnosesDB.getDiagnosisByVisit(examination.getVisitID()).getName());
			
		}catch(Exception e) {
			System.out.println(e);
			examination.setExaminationID(-1);
		}finally{
			
		}
		return examination;
	}
	
	public static Examination getExamination(int visit_id) {
		Examination examination = new Examination();
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = TepDB.getConnection();
        	
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM examinations")
        		.append(" WHERE visit_id = '")
        		.append(visit_id)
        		.append("';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	if(res.next()) {
        		examination = new Examination();
        		examination.setExaminationID(res.getInt("examination_id"));
        		examination.setVisitID(res.getInt("visit_id"));
        		examination.setAMKA(res.getInt("amka"));
        		examination.setDoctorID(res.getInt("doctor_id"));
        		String dg = DiagnosesDB.getDiagnosisByVisit(visit_id).getName();
        		if(dg != null) examination.setDiagnosis(dg);
        		
        		List<Drug> drugs = new ArrayList<>();
        		for(Prescription prescription : PrescriptionDB.getPrescriptionsByExamination(examination.getVisitID())) {
        			Drug drug = DrugDB.getDrug(prescription.getDrug());
        			drugs.add(drug);
        		}
        		examination.setDrugs(drugs);
        		examination.setTests(MedicalTestDB.getTestsByExamination(examination.getVisitID()));
        		
        	}else {
        		System.out.println("No examination not found");
        	}
        	
        }catch(SQLException ex) {
        	System.out.println("Exception " + ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
        	
        }
        
        return examination;
	}
	
}