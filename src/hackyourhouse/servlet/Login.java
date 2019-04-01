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

import hackyourhouse.dal.BrokerDao;
import hackyourhouse.dal.BuyerDao;
import hackyourhouse.dal.PropertyDao;
import hackyourhouse.dal.UserDao;
import hackyourhouse.model.Broker;
import hackyourhouse.model.Buyer;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

@WebServlet("/login")
public class Login extends HttpServlet{
	protected UserDao userDao;
	protected BuyerDao buyerDao;
	protected BrokerDao brokerDao;
	protected PropertyDao propertyDao;
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		propertyDao = PropertyDao.getInstance();
		buyerDao = BuyerDao.getInstance();
		brokerDao = BrokerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//        
//        List<Property> properties = new ArrayList<Property>();
//        
//        String city = req.getParameter("city");
//        
//        if (city == null || city.trim().isEmpty()) {
//            messages.put("success", "Please enter a valid city.");
//        }
//        try {
//        	properties = propertyDao.getAllProperties(city);
//        	
//        }catch (SQLException e) {
//			e.printStackTrace();
//			throw new IOException(e);
//        }
//        messages.put("success", "Displaying all the properties in the city : "+city);
//        req.setAttribute("properties", properties);
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("success", "");
		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Property> properties = new ArrayList<Property>();
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
       
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter username");
        }
        if (password == null || password.trim().isEmpty()) {
            messages.put("success", "Please enter password");
        }
        try {
        	User user = userDao.findUser(username, password);
        	req.getSession().setAttribute("user", user);
        	if(user==null) {
        		messages.put("success", "Invalid credentials");
        		RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
        	    rd.forward(req, resp); 
        	}
        	else if(user.getType().equals("BROKER")) {
        		properties = propertyDao.getAllProperties(username);
        		req.getSession().setAttribute("user", user);
        		req.setAttribute("messages", messages);
        		messages.put("name", user.getFirstName());

        		messages.put("username", user.getUserName());
        		req.setAttribute("properties", properties);
        		
        		Broker broker = null;
        		try {
        			broker = brokerDao.getBrokerByUsername(user.getUserName());
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        		req.getSession().setAttribute("broker", broker);
        		RequestDispatcher rd = req.getRequestDispatcher("/BrokerDashboard.jsp");
        	    rd.forward(req, resp);
        	}else {
        		req.getSession().setAttribute("user", user);
        		req.setAttribute("properties", properties);
        		messages.put("name", user.getFirstName());
        		messages.put("username", user.getUserName());
        		
        		Buyer buyer = null;
        		try {
        			buyer = buyerDao.findByUserName(user.getUserName());
        			System.out.println("CITY"+buyer.getCity());
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        		
        		req.getSession().setAttribute("buyer", buyer);
        		RequestDispatcher rd = req.getRequestDispatcher("/FindProperties.jsp");
        	    rd.forward(req, resp);
        	}
        	
        	
        }catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        messages.put("success", "User registered");
        req.setAttribute("properties", properties);
        
         
        
	}
}
