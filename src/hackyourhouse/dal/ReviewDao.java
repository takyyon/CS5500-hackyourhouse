package hackyourhouse.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hackyourhouse.model.Crime;
import hackyourhouse.model.Property;
import hackyourhouse.model.Review;
import hackyourhouse.model.User;

public class ReviewDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ReviewDao instance = null;
	protected ReviewDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}
	
	public Review create(Review review) throws SQLException {
		String insertReview  = "INSERT INTO Review(Review, UserName, PropertyId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			insertStmt.setString(1, review.getReview());
			insertStmt.setString(2, review.getUserName());
			insertStmt.setInt(3, review.getPropertyId());
			insertStmt.executeUpdate();
			
			return review ;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}

	}
	
	public List<Review> getAllReviewByUserName(String userName) throws SQLException {
		List<Review> reviews = new ArrayList<>();
		String reviewString = "SELECT r.*, p.PropertyName FROM Review AS r INNER JOIN Property AS p ON r.PropertyId = p.PropertyId WHERE r.UserName = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(reviewString);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Review review = new Review(results.getString("Review"), results.getString("UserName"), results.getInt("PropertyId"));
				review.setReviewId(results.getInt("ReviewId"));
				review.setCreated(results.getDate("Created"));
				review.setPropertyName(results.getString("PropertyName"));
				reviews.add(review);
			}
			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
	}
	
	public List<Review> getAllReviewByPropertyId(int propertyId) throws SQLException {
		List<Review> reviews = new ArrayList<>();
		String reviewString = "SELECT * FROM Review WHERE PropertyId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(reviewString);
			selectStmt.setInt(1, propertyId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Review review = new Review(results.getString("Review"), results.getString("UserName"), results.getInt("PropertyId"));
				review.setReviewId(results.getInt("ReviewId"));
				review.setCreated(results.getDate("Created"));
				reviews.add(review);
			}
			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
	}
	
	
	public void delete(int reviewId) throws SQLException {
		String deleteUser = "DELETE FROM Review WHERE ReviewId=?; ";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, reviewId);
			deleteStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
		
	}
	
}
