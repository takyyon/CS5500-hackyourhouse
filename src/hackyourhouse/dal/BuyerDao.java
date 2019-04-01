package hackyourhouse.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hackyourhouse.model.Buyer;
import hackyourhouse.model.User;

public class BuyerDao extends UserDao{
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static BuyerDao instance = null;
	protected BuyerDao() {
		connectionManager = new ConnectionManager();
	}
	public static BuyerDao getInstance() {
		if(instance == null) {
			instance = new BuyerDao();
		}
		return instance;
	}
	
	public Buyer create(Buyer user) throws SQLException {
		create(new User(user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getType()));
		String insertUser  = "INSERT INTO Buyer(Username, Street1, Street2, City, State, Zip, Type) VALUES(?, ?, ?, ?, ?, ?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getStreet1());
			insertStmt.setString(3, user.getStreet2());
			insertStmt.setString(4, user.getCity());
			insertStmt.setString(5, user.getState());
			insertStmt.setInt(6, user.getZip());
			insertStmt.setString(7, user.getBuyerType().name());
			
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
	
	public Buyer findByUserName(String username) throws SQLException {
		String userFind = "SELECT Username, Street1, Street2, City, State, Zip, Type FROM Buyer WHERE Username=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UserDao userDao = UserDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(userFind);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			User user = userDao.findUserByName(username);
			if(results.next()) {
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				Integer zip = Integer.parseInt(results.getString("Zip"));
				String type = results.getString("Type");
				Buyer b = new Buyer(username, user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getType(), street1, street2, city, state, zip, Buyer.UserType.valueOf(type));
				return b;
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
	public Buyer updateBuyer(String username, Buyer buyer) throws SQLException {
		User updateduser = new User(username, buyer.getPassword(), buyer.getFirstName(), buyer.getLastName(), buyer.getEmail(), buyer.getType());
		updateUser(username, updateduser);
		String updateBuyer = "UPDATE Buyer SET Street1=?, Street2=?, City=?, State=?, Zip=?, Type=? WHERE Username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBuyer);
			updateStmt.setString(1, buyer.getStreet1());
			updateStmt.setString(2, buyer.getStreet2());
			updateStmt.setString(3, buyer.getCity());
			updateStmt.setString(4, buyer.getState());
			updateStmt.setInt(5, buyer.getZip());
			updateStmt.setString(6, buyer.getBuyerType().name());
			updateStmt.setString(7,  username);
			updateStmt.executeUpdate();
			
			
			return buyer;
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
	
	public void deleteBuyer(String userId) throws SQLException {
		delete(userId);
		String deleteUser = "DELETE FROM Buyer WHERE UserName=?; ";
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
