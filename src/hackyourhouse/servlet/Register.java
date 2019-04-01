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

import hackyourhouse.dal.PropertyDao;
import hackyourhouse.dal.UserDao;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

@WebServlet("/register")
public class Register extends HttpServlet{
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/Register.jsp").forward(req, resp);
		
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
        String userType = req.getParameter("type");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Please enter username");
        }
        if (password == null || password.trim().isEmpty()) {
            messages.put("success", "Please enter password");
        }
        if (email == null || email.trim().isEmpty()) {
            messages.put("success", "Please enter a email.");
        }
        
        	User user = new User(username, password, firstname, lastname, email, "");
        	//user = userDao.create(user);
        	if(userType.equals("buyer")){
        		req.getSession().setAttribute("user", user);
        		req.setAttribute("user", user);
        		req.setAttribute("password", password);
        		req.setAttribute("firstname", firstname);
        		req.setAttribute("lastname", lastname);
        		req.setAttribute("email", email);
        		RequestDispatcher rd = req.getRequestDispatcher("/RegisterBuyer.jsp");
        	    rd.forward(req, resp); 
        	}else {
        		System.out.println("username"+username);
        		req.getSession().setAttribute("user", user);
        		//req.setAttribute("user", username);
        		req.setAttribute("password", password);
        		req.setAttribute("firstname", firstname);
        		req.setAttribute("lastname", lastname);
        		req.setAttribute("email", email);
        		RequestDispatcher rd = req.getRequestDispatcher("/RegisterBroker.jsp");
        	    rd.forward(req, resp); 
        	}
        
        messages.put("success", "");
        req.setAttribute("properties", properties);
		
        
	}
}
