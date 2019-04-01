package hackyourhouse.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Condo extends Property{
	
	protected Boolean SwimmingPool;
	protected Boolean Gym;
	public Condo(Integer propertyId, Broker broker, String propertyName, byte[] image, Date startDate, Date endDate, String street1,
			String street2, String city, String state, Integer zip, BigDecimal rent, BigDecimal brokerFees,
			BigDecimal securityDeposit, BigDecimal area, Integer totalBaths, Boolean heatingIncluded,
			Boolean petsAllowed, Boolean laundryIncluded, Boolean swimmingPool, Boolean gym) {
		super(propertyId, broker, propertyName, image, startDate, endDate, street1, street2, city, state, zip, rent, brokerFees,
				securityDeposit, area, totalBaths, heatingIncluded, petsAllowed, laundryIncluded);
		this.SwimmingPool = swimmingPool;
		this.Gym = gym;
	}
	
	public Boolean getSwimmingPool() {
		return SwimmingPool;
	}
	public void setSwimmingPool(Boolean swimmingPool) {
		SwimmingPool = swimmingPool;
	}
	public Boolean getGym() {
		return Gym;
	}
	public void setGym(Boolean gym) {
		Gym = gym;
	}
}
