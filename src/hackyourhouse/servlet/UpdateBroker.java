package hackyourhouse.servlet;

import java.io.IOException;
import java.math.BigDecimal;
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

import hackyourhouse.dal.BrokerDao;
import hackyourhouse.dal.BuyerDao;
import hackyourhouse.dal.UserDao;
import hackyourhouse.model.Broker;
import hackyourhouse.model.Buyer;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

@WebServlet("/updatebroker")
public class UpdateBroker extends HttpServlet{
	protected UserDao userDao;
	protected BrokerDao brokerDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		brokerDao = BrokerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = ((User) req.getSession().getAttribute("user"));
		Broker b = ((Broker) req.getSession().getAttribute("broker"));
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("success", "");
		req.getRequestDispatcher("/UpdateBroker.jsp").forward(req, resp);
		
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
        String email = req.getParameter("email");
        
        
		String phone = req.getParameter("phone");
		
		Broker broker = null;
		try {
        	broker = new Broker(username, password, firstname, lastname, email, "BROKER", phone, new BigDecimal(0.0));
        	broker = brokerDao.updateBroker(username, broker);
		 }catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	       }
		req.getSession().setAttribute("broker", broker);
		req.getSession().setAttribute("user", new User(username, password, firstname, lastname, email, "BROKER"));
		messages.put("success", "Updated successfully");
		req.getRequestDispatcher("/UpdateBroker.jsp").forward(req, resp);
	}
}
