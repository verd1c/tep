

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.DiagnosesDB;
import gr.csd.uoc.cs360.tep.db.ExaminationDB;
import gr.csd.uoc.cs360.tep.db.MedicalTestDB;
import gr.csd.uoc.cs360.tep.model.Diagnosis;
import gr.csd.uoc.cs360.tep.model.Examination;
import gr.csd.uoc.cs360.tep.model.MedicalTest;

/**
 * Servlet implementation class examination
 */
public class examination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public examination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Examination examination = ExaminationDB.getExamination(Integer.parseInt(request.getParameter("visit_id")));
		
		String res = new Gson().toJson(examination);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
	    response.getWriter().flush();
	    response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create examination
		Examination examination = new Examination();
		examination.setVisitID(Integer.parseInt(request.getParameter("visit_id")));
		examination.setDiagnosis("none");
		examination.setAMKA(Integer.parseInt(request.getParameter("amka")));
		examination.setDoctorID(Integer.parseInt(request.getParameter("doctor_id")));
		
		// Add the diagnosis
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setVisitID(examination.getVisitID());
		diagnosis.setName(request.getParameter("diagnosis"));
		DiagnosesDB.addDiagnosis(diagnosis);
		
		// Check if no tests done
		List<MedicalTest> tests = MedicalTestDB.getTestsByExamination(examination.getVisitID());
		if(tests.isEmpty()) {
			// Add an empty test to keep track of medicine
			MedicalTest test = new MedicalTest();
			test.setVisitID(examination.getVisitID());
			test.setType("none");
			MedicalTestDB.addTest(test);
		}
		
		// Now we can add the examination
		ExaminationDB.addExamination(examination);
		String res = new Gson().toJson(examination);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
	    response.getWriter().flush();
	    response.getWriter().close();
	}

}
