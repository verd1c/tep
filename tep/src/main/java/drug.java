

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.DrugDB;
import gr.csd.uoc.cs360.tep.db.PrescriptionDB;
import gr.csd.uoc.cs360.tep.model.Drug;
import gr.csd.uoc.cs360.tep.model.Prescription;

/**
 * Servlet implementation class drug
 */
public class drug extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public drug() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Drug> drugs = DrugDB.getDrugsByIllness(request.getParameter("illness"));
		String res = new Gson().toJson(drugs);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
	    response.getWriter().flush();
	    response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prescription prescription = new Prescription();
		prescription.setVisitID(Integer.parseInt(request.getParameter("visit_id")));
		prescription.setDrug(request.getParameter("drug"));
		PrescriptionDB.addPrescription(prescription);
		
	}

}
