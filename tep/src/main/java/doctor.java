

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.DoctorDB;
import gr.csd.uoc.cs360.tep.model.Doctor;

/**
 * Servlet implementation class doctor
 */
public class doctor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doctor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Doctor doctor = new Doctor();
		
		doctor = DoctorDB.getDoctor(Integer.parseInt(request.getParameter("user_id")));
		
		String res = new Gson().toJson(doctor);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
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
