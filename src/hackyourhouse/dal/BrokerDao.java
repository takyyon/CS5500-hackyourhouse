package hackyourhouse.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hackyourhouse.model.Broker;
import hackyourhouse.model.Buyer;
import hackyourhouse.model.User;


public class BrokerDao extends UserDao{
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static BrokerDao instance = null;
	protected BrokerDao() {
		connectionManager = new ConnectionManager();
	}
	public static BrokerDao getInstance() {
		if(instance == null) {
			instance = new BrokerDao();
		}
		return instance;
	}
	
	public Broker create(Broker user) throws SQLException {
		System.out.println("Type"+user.getType());
		create(new User(user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getType()));
		String insertUser  = "INSERT INTO Broker(Username, Phone, Rating) VALUES(?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			System.out.println(user.getUserName());
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPhone());
			insertStmt.setBigDecimal(3, user.getRating());
			
			insertStmt.executeUpdate();
			
			return user ;
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
	
	public Broker getBrokerByUsername(String name) throws SQLException {
		String selectCompany = "SELECT username, phone, rating FROM Broker WHERE Username = ?;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UserDao userDao = UserDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCompany);
			selectStmt.setString(1, name);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String username = results.getString("username");
				String phone = results.getString("phone");
				BigDecimal rating = results.getBigDecimal("rating");
				
				User user = userDao.findUserByName(username);
				
				Broker broker = new Broker(user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getType(),phone, rating);
				return broker;
			}
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
		return null;
	}
	
	public Broker updateBroker(String username, Broker broker) throws SQLException {
		User updateduser = new User(username, broker.getPassword(), broker.getFirstName(), broker.getLastName(), broker.getEmail(),broker.getType());
		updateUser(username, updateduser);
		String updateBuyer = "UPDATE Broker SET Phone=?  WHERE Username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBuyer);
			updateStmt.setString(1, broker.getPhone());
			updateStmt.setString(2,  username);
			updateStmt.executeUpdate();
			
			
			return broker;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public void deleteBroker(String userId) throws SQLException {
		delete(userId);
		String deleteUser = "DELETE FROM Broker WHERE UserName=?; ";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, userId);
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
