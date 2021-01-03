

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.PatientDB;
import gr.csd.uoc.cs360.tep.db.ShiftDB;
import gr.csd.uoc.cs360.tep.db.VisitDB;
import gr.csd.uoc.cs360.tep.model.Patient;
import gr.csd.uoc.cs360.tep.model.Visit;

/**
 * Servlet implementation class visit
 */
public class visit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public visit() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Visit> visits = VisitDB.getVisits(Integer.parseInt(request.getParameter("id")));
		
		String res = new Gson().toJson(visits);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
	    response.getWriter().flush();
	    response.getWriter().close();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Check if patient exists
		Patient patient = null;
		patient = PatientDB.getPatient(Integer.parseInt(request.getParameter("amka")));
		
		if(patient == null) {
			Patient newPatient = new Patient();
			newPatient.setAMKA(Integer.parseInt(request.getParameter("amka")));
			newPatient.setFirstName(request.getParameter("firstName"));
			newPatient.setLastName(request.getParameter("lastName"));
			newPatient.setAddress(request.getParameter("address"));
			newPatient.setInstitution(request.getParameter("institution"));
			
			// Add the new patient
			PatientDB.addPatient(newPatient);
			patient = PatientDB.getPatient(Integer.parseInt(request.getParameter("amka")));
		}
		
		// Create the visit
		Visit visit = new Visit();
		
		visit.setAMKA(Integer.parseInt(request.getParameter("amka")));
		visit.setIllness(request.getParameter("illness").toLowerCase());
		visit.setDoctorID(ShiftDB.getShiftDoctor(visit.getIllness().toLowerCase()).getUserID());
		VisitDB.makeVisit(visit);
	}

}
