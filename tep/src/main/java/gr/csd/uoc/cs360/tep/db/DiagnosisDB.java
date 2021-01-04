package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.Diagnosis;
import gr.csd.uoc.cs360.tep.model.Drug;
import gr.csd.uoc.cs360.tep.model.Prescription;

public class DiagnosisDB {
	
	public static List<Diagnosis> getDiagnosesByIllness(String illness) {
		List<Diagnosis> diagnoses = new ArrayList<>();
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver"); 
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM diagnosis WHERE target = '" + illness + "';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	while (res.next()) {
        		Diagnosis diagnosis = new Diagnosis();
        		diagnosis.setName(res.getString("name"));
        		
        		diagnoses.add(diagnosis);
        	}
        	
        	statement.execute(query.toString());
        	return diagnoses;
        }catch(Exception e) {
        	System.out.println("I failed sadge " + e);
        	return diagnoses;
        }finally {
        	
        }
	}
	
	public static Diagnosis getDiagnosis(String name) {
		Diagnosis diagnosis = null;
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver"); 
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM diagnosis WHERE name = '" + name + "';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	if (res.next()) {
        		diagnosis = new Diagnosis();
        		diagnosis.setName(res.getString("name"));
        	}
        	
        	statement.execute(query.toString());
        	return diagnosis;
        }catch(Exception e) {
        	System.out.println("I failed sadge " + e);
        	return diagnosis;
        }finally {
        	
        }
	}
}
