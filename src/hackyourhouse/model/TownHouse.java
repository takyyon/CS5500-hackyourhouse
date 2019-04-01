package hackyourhouse.model;

import java.math.BigDecimal;
import java.sql.Date;

public class TownHouse extends Property{
	
	protected BigDecimal BackyardArea;
	protected Integer TotalBalconies;
	protected Integer TotalEntrances;
	
	public TownHouse(Integer propertyId, Broker broker, String propertyName, byte[] image, Date startDate, Date endDate,
			String street1, String street2, String city, String state, Integer zip, BigDecimal rent,
			BigDecimal brokerFees, BigDecimal securityDeposit, BigDecimal area, Integer totalBaths,
			Boolean heatingIncluded, Boolean petsAllowed, Boolean laundryIncluded, BigDecimal backyardArea, Integer totalBalconies, 
			Integer totalEntrances) {
		super(propertyId, broker, propertyName, image, startDate, endDate, street1, street2, city, state, zip, rent, brokerFees,
				securityDeposit, area, totalBaths, heatingIncluded, petsAllowed, laundryIncluded);
		this.BackyardArea = backyardArea;
		this.TotalBalconies = totalBalconies;
		this.TotalEntrances = totalEntrances;
	}

	public BigDecimal getBackyardArea() {
		return BackyardArea;
	}

	public void setBackyardArea(BigDecimal backyardArea) {
		BackyardArea = backyardArea;
	}

	public Integer getTotalBalconies() {
		return TotalBalconies;
	}

	public void setTotalBalconies(Integer totalBalconies) {
		TotalBalconies = totalBalconies;
	}

	public Integer getTotalEntrances() {
		return TotalEntrances;
	}

	public void setTotalEntrances(Integer totalEntrances) {
		TotalEntrances = totalEntrances;
	}
	
}
