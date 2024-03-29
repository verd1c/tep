

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.PatientDB;
import gr.csd.uoc.cs360.tep.model.Patient;

/**
 * Servlet implementation class patient
 */
public class patient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public patient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Patient patient = null;
		
		patient = PatientDB.getPatientByID(Integer.parseInt(request.getParameter("user_id")));
		
		
		String res = new Gson().toJson(patient);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
	    response.getWriter().flush();
	    response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Patient patient = new Patient();
		patient.setUserID(Integer.parseInt(request.getParameter("user_id")));
		patient.setFirstName(request.getParameter("firstName"));
		patient.setLastName(request.getParameter("lastName"));
		patient.setAddress(request.getParameter("address"));
		patient.setInstitution(request.getParameter("institution"));
		PatientDB.updatePatient(patient);
	}

}
