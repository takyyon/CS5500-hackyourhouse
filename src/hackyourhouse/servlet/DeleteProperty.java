package hackyourhouse.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hackyourhouse.dal.PropertyDao;
import hackyourhouse.model.Broker;
import hackyourhouse.model.Property;

@WebServlet("/deleteproperty")
public class DeleteProperty extends HttpServlet{
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
        
        
        String propertyId = req.getParameter("id");
        Property property = null;
        if(propertyId==null) {
        	 messages.put("title", "Invalid UserName");
             messages.put("disableSubmit", "true");
        }else {
        	
			try {
				property = propertyDao.getPropertyById(Integer.parseInt(propertyId));
				System.out.println("delete"+property.getPropertyId());
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try{
        		
	        		Property delProperty = propertyDao.delete(property);
	        		if(delProperty==null) {
	        			messages.put("title", "Successfully deleted " + property.getPropertyName());
			            messages.put("disableSubmit", "true");
	        		}else {
	        			messages.put("title", "Failed to delete " + property.getPropertyName());
			        	messages.put("disableSubmit", "false");
	        		}
	        	}catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
        		
        	}
        messages.put("title", "Deleted Property"); 
        System.out.println("DELETE : --"+((Broker)req.getSession().getAttribute("broker")).getUserName());
        req.getRequestDispatcher("/PropertyDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Property property = null;
        String propertyId = req.getParameter("id");
    
        if(propertyId==null) {
        	 messages.put("title", "Invalid UserName");
             messages.put("disableSubmit", "true");
        }else {
        	
			try {
				property = propertyDao.getPropertyById(Integer.parseInt(propertyId));
				System.out.println("DELETE : --"+((Broker)req.getSession().getAttribute("broker")).getUserName());
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	try{
        		
	        		Property delProperty = propertyDao.delete(property);
	        		if(delProperty==null) {
	        			messages.put("title", "Successfully deleted " + property.getPropertyName());
			            messages.put("disableSubmit", "true");
	        		}else {
	        			messages.put("title", "Failed to delete " + property.getPropertyName());
			        	messages.put("disableSubmit", "false");
	        		}
	        	}catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
        		
        	}
        
        req.getRequestDispatcher("/PropertyDelete.jsp").forward(req, resp);
        }
        
	
}
