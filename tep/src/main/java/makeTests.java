

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.csd.uoc.cs360.tep.db.MedicalTestDB;
import gr.csd.uoc.cs360.tep.model.MedicalTest;

/**
 * Servlet implementation class makeTests
 */
public class makeTests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public makeTests() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int visitID = Integer.parseInt(request.getParameter("visit_id"));
		
//		List<MedicalTest> tests = MedicalTestDB.getTestsByExamination(visitID);
//		
//		for(MedicalTest test : tests) {
//			MedicalDB.
//		}
		MedicalTestDB.completeTest(visitID);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
