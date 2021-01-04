package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gr.csd.uoc.cs360.tep.model.Diagnosis;
import gr.csd.uoc.cs360.tep.model.MedicalTest;

public class TestDB {
	
	public static List<MedicalTest> getTestByIllness(String illness) {
		List<MedicalTest> tests = new ArrayList<>();
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver"); 
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM tests WHERE target = '" + illness + "';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	while (res.next()) {
        		MedicalTest test = new MedicalTest();
        		test.setType(res.getString("name"));
        		
        		tests.add(test);
        	}
        	
        	statement.execute(query.toString());
        	return tests;
        }catch(Exception e) {
        	System.out.println("I failed sadge " + e);
        	return tests;
        }finally {
        	
        }
	}
	
	public static MedicalTest getTest(String name) {
		MedicalTest test = null;
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver"); 
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM tests WHERE name = '" + name + "';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	if (res.next()) {
        		test = new MedicalTest();
        		test.setType(res.getString("name"));
        	}
        	
        	statement.execute(query.toString());
        	return test;
        }catch(Exception e) {
        	System.out.println("I failed sadge " + e);
        	return test;
        }finally {
        	
        }
	}
}
