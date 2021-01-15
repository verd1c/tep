package gr.csd.uoc.cs360.tep.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import gr.csd.uoc.cs360.tep.model.Doctor;
import gr.csd.uoc.cs360.tep.model.Employee;

public class EmployeeDB {
	
	public static Employee addEmployee(Employee employee) {
		Statement statement = null;
        Connection con = null;
        StringBuilder query = new StringBuilder();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	con = TepDB.getConnection();
        	statement = con.createStatement();
        	
        	employee.setUserID(UserDB.addUser(employee.getFirstName() + employee.getLastName(), "p" + employee.getLastName(), "employee"));
        	
        	if(employee.getUserID() == -1) return null;
        	
        	query.append("INSERT INTO ")
        		.append(" employees (user_id, first_name, last_name) ")
        		.append(" VALUES (")
        		.append("'" + employee.getUserID() + "',")
        		.append("'" + employee.getFirstName() + "',")
        		.append("'" + employee.getLastName() + "');");
        	
        	statement.execute(query.toString());
        	return employee;
        }catch(SQLException | ClassNotFoundException e) {
        	System.out.println("I failed sadge" + e);
        	return employee;
        }finally {
        	
        }
	}
	
	public static Employee getEmployee(int ID) {
		Employee employee = null;
        Statement stmt = null;
        Connection con = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();

            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("SELECT * FROM employees ")
                    .append(" WHERE ")
                    .append(" user_id = ").append("'").append(ID).append("';");

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();

            if (res.next() == true) {
            	employee = new Employee();
            	employee.setUserID(res.getInt("user_id"));
            	employee.setFirstName(res.getString("first_name"));
            	employee.setLastName(res.getString("last_name"));
            } else {
                System.out.println("User was not found");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            // close connection

        }

        return employee;
	}
	
	public static void updateEmployee(Employee employee) {
		// Check that we have all we need

        Statement stmt = null;
        Connection con = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append("UPDATE employees ")
                    .append(" SET ")
                    .append(" first_name = ").append("'" + employee.getFirstName() + "',")
                    .append(" last_name = ").append("'" + employee.getLastName() + "'")
                    .append(" WHERE user_id= ").append("'").append(employee.getUserID()).append("';");

            stmt.executeUpdate(insQuery.toString());


        } catch (SQLException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // close connection

        }
	}
}
