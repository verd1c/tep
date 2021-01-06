

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.DoctorDB;
import gr.csd.uoc.cs360.tep.db.EmployeeDB;
import gr.csd.uoc.cs360.tep.db.NurseDB;
import gr.csd.uoc.cs360.tep.db.PatientDB;
import gr.csd.uoc.cs360.tep.model.Doctor;
import gr.csd.uoc.cs360.tep.model.Employee;
import gr.csd.uoc.cs360.tep.model.Nurse;
import gr.csd.uoc.cs360.tep.model.Patient;
import gr.csd.uoc.cs360.tep.model.User;

/**
 * Servlet implementation class user
 */
public class user extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("user_id"));
		User user = null;
		
		Patient patient;
        Doctor doctor;
        Nurse nurse;
        Employee employee;
		if((patient = PatientDB.getPatientByID(id)) != null) {
        	user = patient;
        }else if((doctor = DoctorDB.getDoctor(id)) != null) {
        	user = doctor;
        }else if((nurse = NurseDB.getNurse(id)) != null) {
        	user = nurse;
        }else if((employee = EmployeeDB.getEmployee(id)) != null) {
        	user = employee;
        }
		String res = new Gson().toJson(user);
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
