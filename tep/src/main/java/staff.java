

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.csd.uoc.cs360.tep.db.DoctorDB;
import gr.csd.uoc.cs360.tep.db.EmployeeDB;
import gr.csd.uoc.cs360.tep.db.NurseDB;
import gr.csd.uoc.cs360.tep.model.Doctor;
import gr.csd.uoc.cs360.tep.model.Employee;
import gr.csd.uoc.cs360.tep.model.Nurse;

/**
 * Servlet implementation class staff
 */
public class staff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public staff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("user_id"));
		
		Doctor doctor;
		Nurse nurse;
		Employee employee;
		if((doctor = DoctorDB.getDoctor(id)) != null) {
			doctor.setFirstName(request.getParameter("firstName"));
			doctor.setLastName(request.getParameter("lastName"));
        	DoctorDB.updateDoctor(doctor);
        }else if((nurse = NurseDB.getNurse(id)) != null) {
        	nurse.setFirstName(request.getParameter("firstName"));
        	nurse.setLastName(request.getParameter("lastName"));
        	NurseDB.updateNurse(nurse);
        }else if((employee = EmployeeDB.getEmployee(id)) != null) {
        	employee.setFirstName(request.getParameter("firstName"));
        	employee.setLastName(request.getParameter("lastName"));
        	EmployeeDB.updateEmployee(employee);
        }
	}

}
