package hackyourhouse.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
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


@WebServlet("/reportCrime")
public class ReportCrime extends HttpServlet{
	
	protected CrimeDao crimeDao;
	
	@Override
	public void init() throws ServletException {
		crimeDao = CrimeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = ((User) req.getSession().getAttribute("user"));
		Buyer b = ((Buyer) req.getSession().getAttribute("buyer"));
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("success", "");
		req.getRequestDispatcher("/ReportCrime.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {    
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        String type = req.getParameter("type");
        String street1 = req.getParameter("street1");
		String street2 = req.getParameter("street2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		Integer zip = Integer.parseInt(req.getParameter("zip"));
		Integer totalCrimes = Integer.parseInt(req.getParameter("totalCrimes"));
		
		BigDecimal longtiude = new BigDecimal(0.00);
		BigDecimal latitude = new BigDecimal(0.00);
		try {
			
			String address = street1 + " " + street2 + ", " + city + ", " + state + " " + zip;
	        String[] coords = Geocoder.getLatLongPositions(address);
	        longtiude = new BigDecimal(Double.parseDouble(coords[0]));
	        latitude = new BigDecimal(Double.parseDouble(coords[1]));
		}catch(Exception e) {
			e.printStackTrace();
			System.err.print("Unable to get longtiude and latitude");
		}
		Crime crime = null;
		try {
			crime = new Crime(longtiude, latitude, totalCrimes, Crime.CrimeType.valueOf(type) );
			crimeDao.create(crime);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		messages.put("success", "Reported successfully");
		req.getRequestDispatcher("/ReportCrime.jsp").forward(req, resp);
	}
}
