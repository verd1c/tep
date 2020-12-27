package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TepDB {
	private static final String URL = "jdbc:mysql://localhost";
    private static final String DATABASE = "tep";
    private static final int PORT = 3306;
    private static final String UNAME = "root";
    
    
    public static Connection getConnection() throws SQLException {
    	Properties prop = new Properties();
    	prop.put("user", UNAME);
    	Connection connection = DriverManager.
				getConnection(URL + ":" + PORT + "/" + DATABASE, prop);
    	return connection;
    }
}
