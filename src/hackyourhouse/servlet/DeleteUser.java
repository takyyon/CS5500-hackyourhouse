package hackyourhouse.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hackyourhouse.dal.BrokerDao;
import hackyourhouse.dal.BuyerDao;
import hackyourhouse.dal.PropertyDao;
import hackyourhouse.dal.UserDao;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

@WebServlet("/deleteuser")
public class DeleteUser extends HttpServlet{
	protected UserDao userDao;
	protected BuyerDao buyerDao;
	protected BrokerDao brokerDao;
	
	@Override
	public void init() throws ServletException {
		userDao = UserDao.getInstance();
		buyerDao = BuyerDao.getInstance();
		brokerDao = BrokerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        System.out.println("deleting...");
        
        String userId = req.getParameter("id");
        User user = null;
        try {
			user = userDao.findUserByName(userId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        try {
        	if(user.getType().equals("BROKER")) {
        		brokerDao.deleteBroker(userId);
        	}else {
        		buyerDao.deleteBuyer(userId);
        	}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        messages.put("success", "Your account has been deleted.");
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	}
	
}
