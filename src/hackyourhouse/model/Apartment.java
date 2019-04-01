package hackyourhouse.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Apartment extends Property {
	
	protected ApartmentType Type;
	
	public enum ApartmentType{
		Studio, OneBHK, TwoBHK;
	}
	
	public Apartment(Integer propertyId, Broker broker, String propertyName, byte[] image, Date startDate, Date endDate,
			String street1, String street2, String city, String state, Integer zip, BigDecimal rent,
			BigDecimal brokerFees, BigDecimal securityDeposit, BigDecimal area, Integer totalBaths,
			Boolean heatingIncluded, Boolean petsAllowed, Boolean laundryIncluded, ApartmentType type) {
		super(propertyId, broker, propertyName, image, startDate, endDate, street1, street2, city, state, zip, rent, brokerFees,
				securityDeposit, area, totalBaths, heatingIncluded, petsAllowed, laundryIncluded);
		this.Type = type;
	}

	public ApartmentType getType() {
		return Type;
	}

	public void setType(ApartmentType type) {
		Type = type;
	}
}
