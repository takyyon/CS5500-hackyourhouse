package hackyourhouse.servlet;

import java.io.IOException;
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

import hackyourhouse.dal.PropertyDao;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

@WebServlet("/dashboard")
public class BrokerDashboard extends HttpServlet{
	protected PropertyDao propertyDao;
	
	@Override
	public void init() throws ServletException {
		propertyDao = PropertyDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		
        Map<String, String> messages = new HashMap<String, String>();
        messages.put("name", ((User) req.getSession().getAttribute("user")).getFirstName());

        req.setAttribute("messages", messages);
        List<Property> properties = new ArrayList<Property>();
        String username = ((User) req.getSession().getAttribute("user")).getUserName();
        System.out.println("---"+username);
        try {
        	properties = propertyDao.getAllProperties(username);
        	for(Property p:properties) {
        		System.out.println("---"+p.getPropertyName());
        	}
        }catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("properties", properties);
        req.getSession().setAttribute("broker", ((User) req.getSession().getAttribute("broker")));

        req.getRequestDispatcher("/BrokerDashboard.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        messages.put("name", ((User) req.getSession().getAttribute("user")).getFirstName());
        req.setAttribute("messages", messages);
        List<Property> properties = new ArrayList<Property>();
        String username = ((User) req.getSession().getAttribute("user")).getUserName();
        System.out.println("---"+username);
        try {
        	properties = propertyDao.getAllProperties(username);
        	for(Property p:properties) {
        		System.out.println("---"+p.getPropertyName());
        	}
        }catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("properties", properties);
        req.getSession().setAttribute("broker", ((User) req.getSession().getAttribute("broker")));

        //req.setAttribute("name", ((User) req.getSession().getAttribute("user")).getFirstName());
        req.getRequestDispatcher("/BrokerDashboard.jsp").forward(req, resp);
        
	}
}
