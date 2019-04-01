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
import hackyourhouse.model.User;

public class CrimeDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CrimeDao instance = null;
	protected CrimeDao() {
		connectionManager = new ConnectionManager();
	}
	public static CrimeDao getInstance() {
		if(instance == null) {
			instance = new CrimeDao();
		}
		return instance;
	}
	
	public Crime create(Crime crime) throws SQLException {
		String insertCrime  = "INSERT INTO Crime(Longitude, Latitude, TotalCrimes, Type) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCrime);
			insertStmt.setBigDecimal(1,  crime.getLongitude());
			insertStmt.setBigDecimal(2, crime.getLatitude());
			insertStmt.setInt(3,  crime.getTotalCrimes());
			insertStmt.setString(4,  crime.getType().name());
			insertStmt.executeUpdate();
			
			return crime ;
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
	
	public List<Crime> getAllCrimes() throws SQLException {
		List<Crime> crimes = new ArrayList<>();
		String allCrime = "SELECT * FROM Crime;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(allCrime);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Crime crime = new Crime(results.getInt("CrimeId"), results.getBigDecimal("Longitude"),
						results.getBigDecimal("Latitude"), results.getInt("TotalCrimes"), 
						Crime.CrimeType.valueOf(results.getString("Type")));
				crimes.add(crime);
			}
			return crimes;
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
	
	
	public void delete(Integer crimeId) throws SQLException {
		String deleteCrime = "DELETE FROM Crime WHERE CrimeId=?; ";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCrime);
			deleteStmt.setInt(1, crimeId);
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
