package tools;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

import hackyourhouse.dal.BrokerDao;
import hackyourhouse.dal.PropertyDao;
import hackyourhouse.dal.UserDao;
import hackyourhouse.model.Broker;
import hackyourhouse.model.Property;
import hackyourhouse.model.User;

public class Inserter {
	
	
	public static void main(String[] args) throws SQLException {
		PropertyDao propertyDao = PropertyDao.getInstance();
		byte[] bytes = new byte[100];
		Arrays.fill(bytes, (byte) 1);
		
		UserDao userDao = UserDao.getInstance();
		User b = userDao.findUserByName("broker12");
		Property property = new Property("property1", b, bytes, new Date(), new Date(), "s1", "s2", "boston", "ma", 02215, 
				new BigDecimal(2500), new BigDecimal(1500), new BigDecimal(15.0), new BigDecimal(1000), 3, true, false, true);
		property = propertyDao.create(property);
		
		Property property1 = new Property("property2", b, bytes, new Date(), new Date(), "s1", "s2", "rdu", "ma", 02215, 
				new BigDecimal(2500), new BigDecimal(1500), new BigDecimal(15.0), new BigDecimal(1000), 3, true, false, true);
		property1 = propertyDao.create(property1);
		
		Property property2 = new Property("property3", b, bytes, new Date(), new Date(), "s1", "s2", "rdu", "ma", 02215, 
				new BigDecimal(2500), new BigDecimal(1500), new BigDecimal(15.0), new BigDecimal(1000), 3, true, false, true);
		property2 = propertyDao.create(property2);
	}
	
	
}
