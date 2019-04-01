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

@WebServlet("/addReview")
public class AddReview extends HttpServlet{

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
			reviews = reviewDao.getAllReviewByUserName(user.getUserName());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        messages.put("name", user.getFirstName());
        messages.put("username", user.getUserName());
        
        req.setAttribute("user", user);
        req.setAttribute("property", property);
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/AddReview.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {    
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String userName = req.getParameter("userName");
        if(req.getParameter("reviewId") != null) {
        		Integer id = Integer.parseInt(req.getParameter("reviewId"));
        		try {
        			reviewDao.delete(id);
        		}catch(Exception e) {
        			e.printStackTrace();
        		}
        		messages.put("success", "Review Successfully Deleted");
        }else {
	        Integer id = Integer.parseInt(req.getParameter("propertyId"));
	        String reviewText = req.getParameter("review");
			Review review = null;
	        try {
	        		review = new Review(reviewText, userName, id);
				reviewDao.create(review);
			}catch(Exception e) {
				e.printStackTrace();
			}
	        messages.put("success", "Review Successfully Added");
        }
		messages.put("username", userName);
		req.getRequestDispatcher("/FindProperties.jsp").forward(req, resp);
	}
}
