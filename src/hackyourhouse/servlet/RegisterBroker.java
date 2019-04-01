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

import hackyourhouse.dal.BrokerDao;
import hackyourhouse.dal.UserDao;
import hackyourhouse.model.Broker;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

@WebServlet("/broker")
public class RegisterBroker extends HttpServlet{
	protected BrokerDao brokerDao;
	
	@Override
	public void init() throws ServletException {
		brokerDao = BrokerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("TESTGet"+((User) req.getAttribute("user")).getUserName());
		req.getRequestDispatcher("/RegisterBroker.jsp").forward(req, resp);
		
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Property> properties = new ArrayList<Property>();
		String username = ((User) req.getSession().getAttribute("user")).getUserName();
		String password = ((User) req.getSession().getAttribute("user")).getPassword();
		String firstname = ((User) req.getSession().getAttribute("user")).getFirstName();
		String lastname = ((User) req.getSession().getAttribute("user")).getLastName();
		String email = ((User) req.getSession().getAttribute("user")).getEmail();
		String type = "BROKER";
		String phone = req.getParameter("phone");
		try {
        	Broker b = new Broker(username, password, firstname, lastname, email, type, phone, new BigDecimal(0.0));
        	b = brokerDao.create(b);
		 }catch (SQLException e) {
			 messages.put("duplicate", "Duplicate entry");
	       }
	    messages.put("success", "Broker registered. Please log in to continue");
	    req.setAttribute("properties", properties);
		req.getRequestDispatcher("/RegisterBroker.jsp").forward(req, resp);
		
		
        
	}
}
