package hackyourhouse.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hackyourhouse.dal.BuyerDao;
import hackyourhouse.dal.UserDao;
import hackyourhouse.model.Buyer;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

@WebServlet("/buyer")
public class RegisterBuyer extends HttpServlet{
	protected BuyerDao buyerDao;
	
	@Override
	public void init() throws ServletException {
		buyerDao = BuyerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("TESTGet"+((User) req.getAttribute("user")).getUserName());
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("success", "");
		req.getRequestDispatcher("/RegisterBuyer.jsp").forward(req, resp);
		
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		System.out.println("post here");
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Property> properties = new ArrayList<Property>();
		String username = ((User) req.getSession().getAttribute("user")).getUserName();
		String password = ((User) req.getSession().getAttribute("user")).getPassword();
		String firstname = ((User) req.getSession().getAttribute("user")).getFirstName();
		String lastname = ((User) req.getSession().getAttribute("user")).getLastName();
		String email = ((User) req.getSession().getAttribute("user")).getEmail();
		
		String street1 = req.getParameter("street1");
		String street2 = req.getParameter("street2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		Integer zip = Integer.parseInt(req.getParameter("zip"));
		String type = req.getParameter("type");
		String userType = "BUYER";
		try {
        	Buyer b = new Buyer(username, password, firstname, lastname, email, userType, street1, street2, city, state, zip, Buyer.UserType.valueOf(type));
        	b = buyerDao.create(b);
		 }catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	       }
	    messages.put("success", "User registered. Please log in to continue");
	    req.setAttribute("properties", properties);
		req.getRequestDispatcher("/RegisterBuyer.jsp").forward(req, resp);
	}
}
