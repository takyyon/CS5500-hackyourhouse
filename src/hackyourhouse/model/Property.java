package hackyourhouse.model;

import java.util.Date;
import java.math.BigDecimal;

public class Property {
	protected Integer PropertyId;
	protected String PropertyName;
	protected User BrokerUser;
	protected byte[] image;
	protected Date StartDate;
	protected Date EndDate;
	protected String Street1;
	protected String Street2;
	protected String City;
	protected String State;
	protected Integer Zip;
	protected BigDecimal Rent;
	protected BigDecimal BrokerFees;
	protected BigDecimal SecurityDeposit;
	protected BigDecimal Area;
	protected Integer TotalBaths;
	protected Boolean HeatingIncluded;
	protected Boolean PetsAllowed;
	protected Boolean LaundryIncluded;
	public Integer getPropertyId() {
		return PropertyId;
	}
	public void setPropertyId(Integer propertyId) {
		PropertyId = propertyId;
	}
	public String getPropertyName() {
		return PropertyName;
	}
	public void setPropertyName(String propertyName) {
		PropertyName = propertyName;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public String getStreet1() {
		return Street1;
	}
	public void setStreet1(String street1) {
		Street1 = street1;
	}
	public String getStreet2() {
		return Street2;
	}
	public void setStreet2(String street2) {
		Street2 = street2;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public Integer getZip() {
		return Zip;
	}
	public void setZip(Integer zip) {
		Zip = zip;
	}
	public BigDecimal getRent() {
		return Rent;
	}
	public void setRent(BigDecimal rent) {
		Rent = rent;
	}
	public BigDecimal getBrokerFees() {
		return BrokerFees;
	}
	public void setBrokerFees(BigDecimal brokerFees) {
		BrokerFees = brokerFees;
	}
	public BigDecimal getSecurityDeposit() {
		return SecurityDeposit;
	}
	public void setSecurityDeposit(BigDecimal securityDeposit) {
		SecurityDeposit = securityDeposit;
	}
	public BigDecimal getArea() {
		return Area;
	}
	public void setArea(BigDecimal area) {
		Area = area;
	}
	public Integer getTotalBaths() {
		return TotalBaths;
	}
	public void setTotalBaths(Integer totalBaths) {
		TotalBaths = totalBaths;
	}
	public Boolean getHeatingIncluded() {
		return HeatingIncluded;
	}
	public void setHeatingIncluded(Boolean heatingIncluded) {
		HeatingIncluded = heatingIncluded;
	}
	public Boolean getPetsAllowed() {
		return PetsAllowed;
	}
	public void setPetsAllowed(Boolean petsAllowed) {
		PetsAllowed = petsAllowed;
	}
	public Boolean getLaundryIncluded() {
		return LaundryIncluded;
	}
	public void setLaundryIncluded(Boolean laundryIncluded) {
		LaundryIncluded = laundryIncluded;
	}
	public Property(Integer propertyId, User broker, String propertyName, byte[] image, Date startDate, Date endDate, String street1,
			String street2, String city, String state, Integer zip, BigDecimal rent, BigDecimal brokerFees,
			BigDecimal securityDeposit, BigDecimal area, Integer totalBaths, Boolean heatingIncluded,
			Boolean petsAllowed, Boolean laundryIncluded) {
		super();
		PropertyId = propertyId;
		BrokerUser = broker;
		PropertyName = propertyName;
		this.image = image;
		StartDate = startDate;
		EndDate = endDate;
		Street1 = street1;
		Street2 = street2;
		City = city;
		State = state;
		Zip = zip;
		Rent = rent;
		BrokerFees = brokerFees;
		SecurityDeposit = securityDeposit;
		Area = area;
		TotalBaths = totalBaths;
		HeatingIncluded = heatingIncluded;
		PetsAllowed = petsAllowed;
		LaundryIncluded = laundryIncluded;
	}
	
	public Property(String propertyName, User broker, byte[] image, Date startDate, Date endDate, String street1, String street2,
			String city, String state, Integer zip, BigDecimal rent, BigDecimal brokerFees, BigDecimal securityDeposit,
			BigDecimal area, Integer totalBaths, Boolean heatingIncluded, Boolean petsAllowed,
			Boolean laundryIncluded) {
		super();
		PropertyName = propertyName;
		BrokerUser = broker;
		this.image = image;
		StartDate = startDate;
		EndDate = endDate;
		Street1 = street1;
		Street2 = street2;
		City = city;
		State = state;
		Zip = zip;
		Rent = rent;
		BrokerFees = brokerFees;
		SecurityDeposit = securityDeposit;
		Area = area;
		TotalBaths = totalBaths;
		HeatingIncluded = heatingIncluded;
		PetsAllowed = petsAllowed;
		LaundryIncluded = laundryIncluded;
	}
	
	public Property(Integer propertyId) {
		super();
		this.PropertyId = propertyId;
	}
	public User getBroker() {
		return BrokerUser;
	}
	public void setBroker(User broker) {
		this.BrokerUser = broker;
	}
	
	
	
	
}
