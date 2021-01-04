package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gr.csd.uoc.cs360.tep.model.Drug;

public class DrugDB {
	public static List<Drug> getDrugsByIllness(String illness) {
		List<Drug> drugs = new ArrayList<>();
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver"); 
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM drugs WHERE target = '" + illness + "';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	while (res.next()) {
        		Drug drug = new Drug();
        		drug.setName(res.getString("name"));
        		System.out.println(drug.getName());
        		drug.setType(res.getString("type"));
        		drug.setDensity(res.getInt("density"));
        		drug.setTarget(res.getString("target"));
        		
        		drugs.add(drug);
        	}
        	
        	statement.execute(query.toString());
        	return drugs;
        }catch(Exception e) {
        	System.out.println("I failed sadge " + e);
        	return drugs;
        }finally {
        	
        }
	}
	
	public static Drug getDrug(String name) {
		Drug drug = null;
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver"); 
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM drugs WHERE name = '" + name + "';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	if (res.next()) {
        		drug = new Drug();
        		drug.setName(res.getString("name"));
        		drug.setDensity(res.getInt("density"));
        		drug.setTarget(res.getString("target"));
        		drug.setType(res.getString("type"));
        	}
        	
        	statement.execute(query.toString());
        	return drug;
        }catch(Exception e) {
        	System.out.println("I failed sadge " + e);
        	return drug;
        }finally {
        	
        }
	}
}
