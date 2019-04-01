package hackyourhouse.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import hackyourhouse.dal.PropertyDao;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

@WebServlet("/findproperties")
public class FindAllProperties extends HttpServlet{
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
        req.setAttribute("messages", messages);
        System.out.println("FIND PROPERTIES"+((User) req.getSession().getAttribute("user")).getUserName());
        List<Property> properties = new ArrayList<Property>();
        req.getSession().setAttribute("buyer", ((User) req.getSession().getAttribute("buyer")));
        String city = req.getParameter("city");
        
        if (city == null || city.trim().isEmpty()) {
            messages.put("success", "Please enter a valid city.");
        }
        try {
        	properties = propertyDao.getAllProperties(city);
        	
        }catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        messages.put("success", "Displaying all the properties in the city : "+city);
        req.setAttribute("properties", properties);
        
        req.getRequestDispatcher("/FindProperties.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Property> properties = new ArrayList<Property>();
        String firstname = ((User) req.getSession().getAttribute("user")).getFirstName();
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        Integer zip = Integer.parseInt(req.getParameter("zip"));
        messages.put("name", firstname);
        messages.put("username", req.getParameter("userName"));
        if (city == null || city.trim().isEmpty()) {
            messages.put("success", "Please enter a valid city.");
        }
        try {
        	properties = propertyDao.getAllProperties();//getAllPropertiesByValue(city, state, zip);
        	System.out.println(properties);
        }catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        messages.put("success", "Displaying all the properties in the city : "+city);
        req.setAttribute("properties", properties);
        req.getSession().setAttribute("buyer", ((User) req.getSession().getAttribute("buyer")));

        req.getRequestDispatcher("/FindProperties.jsp").forward(req, resp);
        
	}
}
