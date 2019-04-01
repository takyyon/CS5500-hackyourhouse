package hackyourhouse.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hackyourhouse.dal.PropertyDao;
import hackyourhouse.model.Buyer;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;


@WebServlet("/addproperty")
public class AddProperty  extends HttpServlet{
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
        messages.put("success", "");
        System.out.println(messages.get("success"));
        System.out.println("Adding property for:"+((User) req.getSession().getAttribute("user")).getUserName());

        req.getRequestDispatcher("/AddProperty.jsp").forward(req, resp);
        }
	
        
        @Override
        public void doPost(HttpServletRequest req, HttpServletResponse resp)
        		throws ServletException, IOException {
            // Map for storing messages.
            Map<String, String> messages = new HashMap<String, String>();
            req.setAttribute("messages", messages);
            User u = ((User) req.getSession().getAttribute("user"));
            //System.out.println("Adding property for:"+((User) req.getSession().getAttribute("user")).getUserName());
            String propertyname = req.getParameter("propertyname");
            String street1 = req.getParameter("street1");    
            String street2 = req.getParameter("street2");
            Date startdate = null;
            try {
				startdate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startdate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
            Date enddate = null;
            try {
				enddate = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("enddate"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
     
            String city = req.getParameter("city");
            String state = req.getParameter("state");
            Integer zip = Integer.parseInt(req.getParameter("zip"));
            BigDecimal rent = new BigDecimal(req.getParameter("rent"));
            BigDecimal brokerfees = new BigDecimal(req.getParameter("brokerfees"));
            BigDecimal securitydeposit = new BigDecimal(req.getParameter("securitydeposit"));
            BigDecimal Area = new BigDecimal(req.getParameter("area"));
            Integer TotalBaths = Integer.parseInt(req.getParameter("baths"));
            String heating = req.getParameter("heating");
            Boolean heat;
            if(heating.equals("0")) {
            	heat = true;
            }else {
            	heat = false;
            }
            String petsAllowed = req.getParameter("pets");
            Boolean pets;
            if(petsAllowed.equals("0")) {
            	pets = true;
            }else {
            	pets = false;
            }
            String laundryIncluded = req.getParameter("laundry");
            Boolean laundry;
            if(laundryIncluded.equals("0")) {
            	laundry = true;
            }else {
            	laundry = false;
            }
    		byte[] bytes = new byte[100];
    		Arrays.fill(bytes, (byte) 1);
            try {
            	Property property = new Property(propertyname, u, bytes,startdate, 
            			enddate,street1, street2, city, state, zip, rent, brokerfees, 
            			securitydeposit, Area, TotalBaths, heat, pets, laundry);
            	propertyDao.create(property);
    		 }catch (SQLException e) {
    				e.printStackTrace();
    				throw new IOException(e);
    	       }
            		
            messages.put("success", "Property Added");
            
            req.getRequestDispatcher("/AddProperty.jsp").forward(req, resp);
        }
}
