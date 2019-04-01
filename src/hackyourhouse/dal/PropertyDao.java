package hackyourhouse.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import hackyourhouse.dal.ConnectionManager;
import hackyourhouse.dal.PropertyDao;
import hackyourhouse.model.Broker;
import hackyourhouse.model.Property;


public class PropertyDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static PropertyDao instance = null;
	protected PropertyDao() {
		connectionManager = new ConnectionManager();
	}
	public static PropertyDao getInstance() {
		if(instance == null) {
			instance = new PropertyDao();
		}
		return instance;
	}
	
	public Property create(Property property) throws SQLException {
		String insertProperty  = "INSERT INTO Property(PropertyName, Username, Image, StartDate, EndDate, Street1, Street2, City, State, Zip, Rent, BrokerFees, SecurityDeposit, Area, TotalBaths, HeatingIncluded, PetsAllowed, LaundryIncluded) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertProperty , Statement.RETURN_GENERATED_KEYS);

			
			insertStmt.setString(1, property.getPropertyName());
			insertStmt.setString(2, property.getBroker().getUserName());
			insertStmt.setBytes(3,  property.getImage());
			insertStmt.setTimestamp(4,  new Timestamp(property.getStartDate().getTime()));
			insertStmt.setTimestamp(5,  new Timestamp(property.getEndDate().getTime()));
			insertStmt.setString(6,  property.getStreet1());
			insertStmt.setString(7,  property.getStreet2());
			insertStmt.setString(8,  property.getCity());
			insertStmt.setString(9, property.getState());
			insertStmt.setInt(10, property.getZip());
			insertStmt.setBigDecimal(11, property.getRent());
			insertStmt.setBigDecimal(12, property.getBrokerFees());
			insertStmt.setBigDecimal(13, property.getSecurityDeposit());
			insertStmt.setBigDecimal(14, property.getArea());
			insertStmt.setInt(15,  property.getTotalBaths());
			insertStmt.setBoolean(16,  property.getHeatingIncluded());
			insertStmt.setBoolean(17, property.getPetsAllowed());
			insertStmt.setBoolean(18, property.getLaundryIncluded());
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int PropertyId = -1;
			if(resultKey.next()) {
				PropertyId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			property.setPropertyId(PropertyId);
			return property ;
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
	
	public Property updateCity(Property property, String newCity) throws SQLException {
		String updateProperty = "UPDATE Property SET City=? WHERE PropertyId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateProperty);
			updateStmt.setString(1, newCity);
			updateStmt.setInt(2, property.getPropertyId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			property.setCity(newCity);
			return property;
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
	
	public Property getPropertyById(Integer id) throws SQLException {
		String allProperties = "SELECT PropertyId, UserName, PropertyName, Image, StartDate, EndDate, Street1, Street2, City, State, Zip, Rent, BrokerFees, SecurityDeposit, Area, TotalBaths, HeatingIncluded, PetsAllowed, LaundryIncluded FROM Property WHERE PropertyId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		BrokerDao brokerDao = BrokerDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(allProperties);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			if(results.next()) {
				Integer propertyId = results.getInt("PropertyId");
				String broker = results.getString("UserName");
				String name = results.getString("PropertyName");
				byte[] image = results.getBytes("Image");
				Date sd = results.getDate("StartDate");
				Date ed = results.getDate("EndDate");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String c = results.getString("City");
				String state = results.getString("State");
				Integer zip = results.getInt("Zip");
				BigDecimal rent = results.getBigDecimal("Rent");
				BigDecimal brokerFees = results.getBigDecimal("BrokerFees");
				BigDecimal securityDeposit = results.getBigDecimal("SecurityDeposit");
				BigDecimal area = results.getBigDecimal("Area");
				Integer totalBaths = results.getInt("TotalBaths");
				Boolean heatingIncluded = results.getBoolean("HeatingIncluded");
				Boolean petsAllowed = results.getBoolean("PetsAllowed");
				Boolean laundryIncluded = results.getBoolean("LaundryIncluded");
				Broker b = brokerDao.getBrokerByUsername(broker);
				Property property = new Property(propertyId, b, name, image, sd, ed, street1, street2, c, state, zip, rent, brokerFees,
						securityDeposit, area, totalBaths, heatingIncluded, petsAllowed, laundryIncluded);
				return property;
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
	
	public List<Property> getAllPropertiesByValue(String city, String s, Integer z) throws SQLException{
		List<Property> properties = new ArrayList<Property>();
		String allProperties = "SELECT PropertyId, UserName, PropertyName, Image, StartDate, EndDate, Street1, Street2, City, State, Zip, Rent, BrokerFees, SecurityDeposit, Area, TotalBaths, HeatingIncluded, PetsAllowed, LaundryIncluded FROM Property WHERE City=? AND State=? AND Zip=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		System.out.println("correct");
		BrokerDao brokerDao = BrokerDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(allProperties);
			selectStmt.setString(1, city);
			selectStmt.setString(2, s);
			selectStmt.setInt(3, z);
			results = selectStmt.executeQuery();
			System.out.println(results);
			while(results.next()) {
				Integer propertyId = results.getInt("Propertyid");
				String name = results.getString("PropertyName");
				String broker = results.getString("UserName");
				byte[] image = results.getBytes("Image");
				Date sd = results.getDate("StartDate");
				Date ed = results.getDate("EndDate");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String c = results.getString("City");
				String state = results.getString("State");
				Integer zip = results.getInt("Zip");
				BigDecimal rent = results.getBigDecimal("Rent");
				BigDecimal brokerFees = results.getBigDecimal("BrokerFees");
				BigDecimal securityDeposit = results.getBigDecimal("SecurityDeposit");
				BigDecimal area = results.getBigDecimal("Area");
				Integer totalBaths = results.getInt("TotalBaths");
				Boolean heatingIncluded = results.getBoolean("HeatingIncluded");
				Boolean petsAllowed = results.getBoolean("PetsAllowed");
				Boolean laundryIncluded = results.getBoolean("LaundryIncluded");
				Broker b = brokerDao.getBrokerByUsername(broker);
				Property property = new Property(propertyId, b, name, image, sd, ed, street1, street2, c, state, zip, rent, brokerFees,
						securityDeposit, area, totalBaths, heatingIncluded, petsAllowed, laundryIncluded);
				properties.add(property);
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
		for(Property p:properties) {
			System.out.println("p: "+p.getPropertyName());
		}
		return properties;
	}
	
	public List<Property> getAllProperties(String username) throws SQLException{
		List<Property> properties = new ArrayList<Property>();
		String allProperties = "SELECT PropertyId, UserName, PropertyName, Image, StartDate, EndDate, Street1, Street2, City, State, Zip, Rent, BrokerFees, SecurityDeposit, Area, TotalBaths, HeatingIncluded, PetsAllowed, LaundryIncluded FROM Property WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		BrokerDao brokerDao = BrokerDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(allProperties);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			
			System.out.println(results);
			while(results.next()) {
				Integer propertyId = results.getInt("Propertyid");
				String name = results.getString("PropertyName");
				String broker = results.getString("UserName");
				byte[] image = results.getBytes("Image");
				Date sd = results.getDate("StartDate");
				Date ed = results.getDate("EndDate");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String c = results.getString("City");
				String state = results.getString("State");
				Integer zip = results.getInt("Zip");
				BigDecimal rent = results.getBigDecimal("Rent");
				BigDecimal brokerFees = results.getBigDecimal("BrokerFees");
				BigDecimal securityDeposit = results.getBigDecimal("SecurityDeposit");
				BigDecimal area = results.getBigDecimal("Area");
				Integer totalBaths = results.getInt("TotalBaths");
				Boolean heatingIncluded = results.getBoolean("HeatingIncluded");
				Boolean petsAllowed = results.getBoolean("PetsAllowed");
				Boolean laundryIncluded = results.getBoolean("LaundryIncluded");
				Broker b = brokerDao.getBrokerByUsername(broker);
				Property property = new Property(propertyId, b, name, image, sd, ed, street1, street2, c, state, zip, rent, brokerFees,
						securityDeposit, area, totalBaths, heatingIncluded, petsAllowed, laundryIncluded);
				System.out.println(name);
				properties.add(property);
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
		return properties;
	}
	
	public List<Property> getAllProperties() throws SQLException{
		List<Property> properties = new ArrayList<Property>();
		String allProperties = "SELECT PropertyId, UserName, PropertyName, Image, StartDate, EndDate, Street1, Street2, City, State, Zip, Rent, BrokerFees, SecurityDeposit, Area, TotalBaths, HeatingIncluded, PetsAllowed, LaundryIncluded FROM Property;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		BrokerDao brokerDao = BrokerDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(allProperties);
			results = selectStmt.executeQuery();
			
			System.out.println(results);
			while(results.next()) {
				Integer propertyId = results.getInt("Propertyid");
				String name = results.getString("PropertyName");
				String broker = results.getString("UserName");
				byte[] image = results.getBytes("Image");
				Date sd = results.getDate("StartDate");
				Date ed = results.getDate("EndDate");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String c = results.getString("City");
				String state = results.getString("State");
				Integer zip = results.getInt("Zip");
				BigDecimal rent = results.getBigDecimal("Rent");
				BigDecimal brokerFees = results.getBigDecimal("BrokerFees");
				BigDecimal securityDeposit = results.getBigDecimal("SecurityDeposit");
				BigDecimal area = results.getBigDecimal("Area");
				Integer totalBaths = results.getInt("TotalBaths");
				Boolean heatingIncluded = results.getBoolean("HeatingIncluded");
				Boolean petsAllowed = results.getBoolean("PetsAllowed");
				Boolean laundryIncluded = results.getBoolean("LaundryIncluded");
				Broker b = brokerDao.getBrokerByUsername(broker);
				Property property = new Property(propertyId, b, name, image, sd, ed, street1, street2, c, state, zip, rent, brokerFees,
						securityDeposit, area, totalBaths, heatingIncluded, petsAllowed, laundryIncluded);
				System.out.println(name);
				properties.add(property);
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
		return properties;
	}
	
	public Property delete(Property property) throws SQLException {
		String deleteProperty = "DELETE FROM Property WHERE PropertyId=?; ";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProperty);
			deleteStmt.setInt(1, property.getPropertyId());
			deleteStmt.executeUpdate();
			return null;
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
