

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gr.csd.uoc.cs360.tep.db.ShiftDB;
import gr.csd.uoc.cs360.tep.model.Shift;
import gr.csd.uoc.cs360.tep.model.User;

/**
 * Servlet implementation class shift
 */
public class shift extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shift() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Shift shift;
		if(request.getParameter("id") != null) {
			shift = ShiftDB.getShift(Integer.parseInt(request.getParameter("id")));
		}else {
			shift = ShiftDB.getShift(ShiftDB.getCurrentShift());
		}
		String res = new Gson().toJson(shift);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
	    response.getWriter().flush();
	    response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids = request.getParameter("user_ids");
		List<String> idsString = Arrays.asList(ids.split(","));
		List<User> users = new ArrayList<>();
		
		for(String id : idsString) {
			User user = new User();
			user.setUserID(Integer.parseInt(id));
			users.add(user);
		}
		
		Shift shift = ShiftDB.updateShift(users);
		String res = new Gson().toJson(shift);
		response.setStatus(HttpServletResponse.SC_OK);
	    response.getWriter().write(res);
	    response.getWriter().flush();
	    response.getWriter().close();
	}

}
