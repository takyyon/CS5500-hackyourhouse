package hackyourhouse.servlet;

import java.io.IOException;
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

@WebServlet("/updatebuyer")
public class UpdateBuyer extends HttpServlet{
	protected UserDao userDao;
	protected BuyerDao buyerDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		buyerDao = BuyerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = ((User) req.getSession().getAttribute("user"));
		Buyer b = ((Buyer) req.getSession().getAttribute("buyer"));
		System.out.println("bbuyer"+b.getCity());
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("success", "");
		req.getRequestDispatcher("/UpdateBuyer.jsp").forward(req, resp);
		
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {    
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Property> properties = new ArrayList<Property>();
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("lastname");
        
        
		String street1 = req.getParameter("street1");
		String street2 = req.getParameter("street2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		Integer zip = Integer.parseInt(req.getParameter("zip"));
		String type = req.getParameter("type");
		
		Buyer buyer = null;
		try {
        	buyer = new Buyer(username, password, firstname, lastname, email, "BUYER", street1, street2, city, state, zip, Buyer.UserType.valueOf(type));
        	buyer = buyerDao.updateBuyer(username, buyer);
		 }catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	       }
		req.getSession().setAttribute("buyer", buyer);
		req.getSession().setAttribute("user", new User(username, password, firstname, lastname, email, "BUYER"));

		messages.put("success", "Updated successfully");
		req.getRequestDispatcher("/UpdateBuyer.jsp").forward(req, resp);
	}
}
