package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gr.csd.uoc.cs360.tep.model.Patient;
import gr.csd.uoc.cs360.tep.model.Doctor.Specialization;

public class IllnessDB {
		
	public static String getSpecByIllness(String illness) {
		String spec = "";
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver"); 
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	query.append("SELECT * FROM illness WHERE name = '" + illness + "';");
        	statement.execute(query.toString());
        	ResultSet res = statement.getResultSet();

        	if(res.next()) {
        		spec = res.getString("specialization");
        	}else {
        		System.out.println("Illness not found");
        	}
        	
        	statement.execute(query.toString());
        	return spec;
        }catch(Exception e) {
        	System.out.println("I failed sadge " + e);
        	return "";
        }finally {
        	
        }
	}
}
