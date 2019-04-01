package hackyourhouse.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hackyourhouse.dal.CrimeDao;
import hackyourhouse.model.Buyer;
import hackyourhouse.model.Crime;
import hackyourhouse.model.User;
import tools.Geocoder;


@WebServlet("/allCrimes")
public class AllCrimes extends HttpServlet{
	
	protected CrimeDao crimeDao;
	
	@Override
	public void init() throws ServletException {
		crimeDao = CrimeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Crime> crimes = new ArrayList<>();
		try {
			crimes = crimeDao.getAllCrimes();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("success", "");
        req.setAttribute("crimes", crimes);
		req.getRequestDispatcher("/AllCrimes.jsp").forward(req, resp);
		
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {    
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Integer id = Integer.parseInt(req.getParameter("crimeId"));
        
		try {
			crimeDao.delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		messages.put("success", "Deleted successfully");
		req.getRequestDispatcher("/AllCrimes.jsp").forward(req, resp);
	}
}
