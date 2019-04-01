package hackyourhouse.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import hackyourhouse.model.Property;
import hackyourhouse.model.User;

public class UserDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UserDao instance = null;
	protected UserDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	public User create(User user) throws SQLException {
		String insertUser  = "INSERT INTO Users(UserName, Password, FirstName, LastName, Email, Type) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2,  user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4,  user.getLastName());
			insertStmt.setString(5,  user.getEmail());
			insertStmt.setString(6,user.getType());
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
	public User findUser(String username, String password) throws SQLException {
		String userFind = "SELECT Username, Password, Firstname, Lastname, Email, Type FROM Users WHERE username=? AND password=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(userFind);
			selectStmt.setString(1, username);
			selectStmt.setString(2, password);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String username1 = results.getString("Username");
				String password1 = results.getString("Password");
				String firstname = results.getString("FirstName");
				String lastname = results.getString("Lastname");
				String email = results.getString("Email");
				String type = results.getString("Type");
				User user = new User(username1, password1, firstname, lastname, email, type);
				return user;
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
	
	public User findUserByName(String username) throws SQLException {
		String userFind = "SELECT Username, Password, Firstname, Lastname, Email, Type FROM Users WHERE username=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(userFind);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String username1 = results.getString("Username");
				String password1 = results.getString("Password");
				String firstname = results.getString("FirstName");
				String lastname = results.getString("Lastname");
				String email = results.getString("Email");
				String type = results.getString("Type");
				User user = new User(username1, password1, firstname, lastname, email, type);
				return user;
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
	public void delete(String userId) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserName=?; ";
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
	
	protected User updateUser(String username, User updateduser) throws SQLException {
		String updateUser = "UPDATE Users SET FirstName=?, LastName=?, Email=? WHERE Username=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, updateduser.getFirstName());
			updateStmt.setString(2, updateduser.getLastName());
			updateStmt.setString(3, updateduser.getEmail());
			updateStmt.setString(4,  username);
			updateStmt.executeUpdate();
			
			
			return updateduser;
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
	
	
}
