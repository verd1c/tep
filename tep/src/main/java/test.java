

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.MedicalTestDB;
import gr.csd.uoc.cs360.tep.db.TestDB;
import gr.csd.uoc.cs360.tep.model.MedicalTest;

/**
 * Servlet implementation class test
 */
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<MedicalTest> tests = TestDB.getTestByIllness(request.getParameter("illness"));
		String res = new Gson().toJson(tests);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
	    response.getWriter().flush();
	    response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicalTest test = new MedicalTest();
		test.setVisitID(Integer.parseInt(request.getParameter("visit_id")));
		test.setType(request.getParameter("type"));
		test.setCompleted(false);
		MedicalTestDB.addTest(test);
		
	}

}
