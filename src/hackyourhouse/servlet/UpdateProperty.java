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

@WebServlet("/updateproperty")
public class UpdateProperty extends HttpServlet{
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
        System.out.println(propertyId);
        if(propertyId == null) {
        	messages.put("success", "Please enter a valid property.");}
        	else {
            	try {
            		Property property = propertyDao.getPropertyById(Integer.parseInt(propertyId));
            		if(property == null) {
            			messages.put("success", "UserName does not exist.");
            		}
            		req.setAttribute("property", property);
            	} catch (SQLException e) {
    				e.printStackTrace();
    				throw new IOException(e);
    	        }
            }
        req.getRequestDispatcher("/UpdateProperty.jsp").forward(req, resp);
        }
	
        
        @Override
        public void doPost(HttpServletRequest req, HttpServletResponse resp)
        		throws ServletException, IOException {
            // Map for storing messages.
            Map<String, String> messages = new HashMap<String, String>();
            req.setAttribute("messages", messages);

            // Retrieve user and validate.
            String propertyId = req.getParameter("id");
            System.out.println("DEBUG"+propertyId);
                	try {
                		
                		Property property = propertyDao.getPropertyById(Integer.parseInt(propertyId));
                		if(property == null) {
                			messages.put("success", "UserName does not exist.");
                		}else {
                			String newCity = req.getParameter("city");
                			if(newCity==null) {
                				messages.put("success", "Please enter a valid LastName.");
                			}else {
                				property = propertyDao.updateCity(property, newCity);

                	        	messages.put("success", "Successfully updated " + newCity);
                			}
                		}
                		req.setAttribute("property", property);
                	} catch (SQLException e) {
        				e.printStackTrace();
        				throw new IOException(e);
        	        }
                
            
            
            req.getRequestDispatcher("/UpdateProperty.jsp").forward(req, resp);
        }
    }

