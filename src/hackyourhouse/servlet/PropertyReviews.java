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

import hackyourhouse.dal.BrokerDao;
import hackyourhouse.dal.BuyerDao;
import hackyourhouse.dal.PropertyDao;
import hackyourhouse.dal.ReviewDao;
import hackyourhouse.dal.UserDao;
import hackyourhouse.model.Property;
import hackyourhouse.model.Review;
import hackyourhouse.model.User;

@WebServlet("/propertyReviews")
public class PropertyReviews extends HttpServlet{

	ReviewDao reviewDao;
	UserDao userDao;
	PropertyDao propertyDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
		userDao = UserDao.getInstance();
		propertyDao = PropertyDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        int propertyId = Integer.parseInt(req.getParameter("id"));
        String userName = req.getParameter("userName");
        
        User user = null;
        Property property = null;
        List<Review> reviews = new ArrayList<>();
        try {
			user = userDao.findUserByName(userName);
			property = propertyDao.getPropertyById(propertyId);
			reviews = reviewDao.getAllReviewByPropertyId(propertyId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        messages.put("name", user.getFirstName());
        messages.put("username", user.getUserName());
        
        req.setAttribute("user", user);
        req.setAttribute("property", property);
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/PropertyReviews.jsp").forward(req, resp);
	}
	
}
