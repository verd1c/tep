

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.TepDB;
import gr.csd.uoc.cs360.tep.db.UserDB;
import gr.csd.uoc.cs360.tep.model.MedicalTest;

/**
 * Servlet implementation class query
 */
public class query extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public query() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<List<String>> result = new ArrayList<>();
		Statement stmt = null;
        Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            con = TepDB.getConnection();
            stmt = con.createStatement();

            StringBuilder insQuery = new StringBuilder();

            insQuery.append(request.getParameter("query"));

            stmt.execute(insQuery.toString());

            ResultSet res = stmt.getResultSet();
            ResultSetMetaData rsmd = res.getMetaData();
            System.out.println(rsmd.getColumnCount());
            
            List<String> curRes = new ArrayList<>();
            for(int i = 0; i < rsmd.getColumnCount(); i++) {
        		curRes.add(rsmd.getColumnName(i + 1));
        	}
            
            result.add(curRes);
            
            while (res.next() == true) {
            	curRes = new ArrayList<>();
            	
            	for(int i = 0; i < rsmd.getColumnCount(); i++) {
            		curRes.add(res.getString(i + 1));
            	}
                
                result.add(curRes);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            // Log exception
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
		
		String r = new Gson().toJson(result);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(r);
	    response.getWriter().flush();
	    response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
